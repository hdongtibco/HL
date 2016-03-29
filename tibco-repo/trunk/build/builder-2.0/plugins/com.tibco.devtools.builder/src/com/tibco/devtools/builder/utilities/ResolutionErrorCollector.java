package com.tibco.devtools.builder.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.osgi.service.resolver.BaseDescription;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.osgi.service.resolver.ExportPackageDescription;
import org.eclipse.osgi.service.resolver.ImportPackageSpecification;
import org.eclipse.osgi.service.resolver.ResolverError;
import org.eclipse.osgi.service.resolver.State;
import org.eclipse.osgi.service.resolver.VersionConstraint;
import org.eclipse.osgi.service.resolver.VersionRange;

/**
 * A utility for OSGI resolver state
 * It provide several functions, like get duplicated bundles, get resolver errors, and etc 
 */
public class ResolutionErrorCollector
{
    private static final String INDENT = "    ";
    
    // The State associated with PDE
    private State state;
    // If the exporter of a package cannot be found, the nullPackageRoots will be returned by getPackageRoots
    final PackageRoots nullPackageRoots = new PackageRoots();

    public ResolutionErrorCollector(State state)
    {
        this.state = state;
    }

    /**
     * get duplicated bundles from current PDE environment
     * @return Map<bundle name, duplicated bundles>
     */
    public Map<String, List<BundleDescription>> getDuplicatedBundles()
    {
        return getDuplicatedDescriptions(state.getBundles());
    }

    /**
     * get duplicated export packages from current PDE environment
     * @return Map<package name, duplicated export packages>
     */
    public Map<String, List<ExportPackageDescription>> getDuplicatedExportPackages()
    {
        return getDuplicatedDescriptions(state.getExportedPackages());
    }

    private <D extends BaseDescription> Map<String, List<D>> getDuplicatedDescriptions(D[] descriptions)
    {
        Map<String, List<D>> completeMap = new HashMap<String, List<D>>();
        for (D description : descriptions)
        {
            List<D> duplicatedList = completeMap.get(description.getName());
            if (duplicatedList == null)
            {
                duplicatedList = new ArrayList<D>(1);
                completeMap.put(description.getName(), duplicatedList);
            }
            duplicatedList.add(description);
        }

        Map<String, List<D>> duplicatedMap = new HashMap<String, List<D>>();
        for (List<D> duplicatedList : completeMap.values())
        {
            if (duplicatedList.size() > 1)
            {
                duplicatedMap.put(duplicatedList.get(0).getName(), duplicatedList);
            }
        }
        
        return duplicatedMap;
    }
    
    /**
     * get resolver error for specified bundle
     * @param description bundle description
     * @param visited visited bundles, so we won't repeatedly check the same bundle
     * @param ignore ignore bundles, to specify which bundles shouldn't be nested checked
     * @return formated error message
     */
    public String getResolverError(BundleDescription description, 
            List<BundleDescription> visited, List<BundleDescription> ignore)
    {
        StringBuffer errors = new StringBuffer();
        setResolverErrors(description, errors, 1, visited, ignore);
        return errors.toString();
    }

    /**
     * set formated resolver error messages to the string buffer-errors, it could be recursively called
     */
    private void setResolverErrors(BundleDescription description, 
            StringBuffer errors, int indent, List<BundleDescription> visited, 
            List<BundleDescription> ignore)
    {
        // check whether the bundle is visited
        if (visited.contains(description))
            return;
        visited.add(description);

        ResolverError[] resolverErrors = state.getResolverErrors(description);
        if (resolverErrors != null && resolverErrors.length > 0)
        {
            nextLine(errors, indent);
            errors.append("Bundle ");
            errors.append(description);
            errors.append(" fails to resolve");

            setResolverErrorStackTrace(description, errors, indent + 1, resolverErrors, visited, ignore);
        }
    }

    private static void nextLine(StringBuffer strbuf, int indent)
    {
        strbuf.append('\n');
        for (int i = 0; i < indent; i++)
            strbuf.append(INDENT);
    }

    /**
     * check if the resolver error type is conflict error
     */
    private boolean isConflictError(ResolverError resolverError)
    {
        return resolverError.getType() == ResolverError.IMPORT_PACKAGE_USES_CONFLICT || resolverError.getType() == ResolverError.REQUIRE_BUNDLE_USES_CONFLICT;
    }
    
    /**
     * set conflict error messages
     * 
     * @return true when conflict errors are found
     */
    private boolean setConflictErrors(BundleDescription description, 
            StringBuffer errors, int indent, ResolverError[] resolverErrors)
    {
        List<BundleSpecification> conflictBundleList = new ArrayList<BundleSpecification>();
        List<ImportPackageSpecification> conflictPackageList = new ArrayList<ImportPackageSpecification>();
        for (ResolverError resolverError : resolverErrors)
        {
            if (resolverError.getType() == ResolverError.IMPORT_PACKAGE_USES_CONFLICT)
            {
                conflictPackageList.add((ImportPackageSpecification) resolverError.getUnsatisfiedConstraint());
            }
            else if (resolverError.getType() == ResolverError.REQUIRE_BUNDLE_USES_CONFLICT)
            {
                conflictBundleList.add((BundleSpecification) resolverError.getUnsatisfiedConstraint());
            }
        }
        
        List<ConflictError> conflicts = getConflicts(description, conflictBundleList.toArray(new BundleSpecification[conflictBundleList.size()]), 
                conflictPackageList.toArray(new ImportPackageSpecification[conflictPackageList.size()]));
        if (conflicts.size() > 0)
        {
            nextLine(errors, indent);
            errors.append("Conflicting constraints: ");
            
            for (ConflictError conflictError : conflicts)
                errors.append(conflictError.toString(indent + 1));
            
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * nested check the resolver error
     * for example, if bundle A's resolver error is: "Missing Constraint: Require-Bundle: bundle B", 
     *     then the root cause could be: bundle B is not found or
     *                                   bundle B fails to resolve because bundle B's constraint - bundle C is not found
     */
    private void setResolverErrorStackTrace(BundleDescription description, 
            StringBuffer errors, int indent, 
            ResolverError[] resolverErrors, List<BundleDescription> visited,
            List<BundleDescription> ignore)
    {
        boolean checkedConflictError = false;
        for (ResolverError resolverError : resolverErrors)
        {
            if (isConflictError(resolverError))
            {
                if (!checkedConflictError)
                {
                    setConflictErrors(description, errors, indent, resolverErrors);
                    checkedConflictError = true;
                }
                continue;
            }
            
            nextLine(errors, indent);
            errors.append(resolverError);
            
            VersionConstraint unsatisfiedConstraint = resolverError.getUnsatisfiedConstraint();
            
            //check version range. If bundle version range is empty, it may caused by using "version" in "Require-Bundle:" as version range.
            if(unsatisfiedConstraint != null){
              if (unsatisfiedConstraint.getVersionRange().equals(VersionRange.emptyRange)){
                nextLine(errors, indent + 1);
                errors.append("***  'Require-Bundle' " + unsatisfiedConstraint.getName() + " must use 'bundle-version=' instead of 'version=' to declare version range. ");
              } 
            }
            if (unsatisfiedConstraint instanceof ImportPackageSpecification)
            {
                List<ExportPackageDescription> possibleExporters = getPossibleExporters((ImportPackageSpecification) unsatisfiedConstraint);
                //no exporter was found, it is miss import package
                if (possibleExporters.isEmpty())
                {
                    //first, try to find out the unresolved exporter
                    possibleExporters = getUnresolvedExporters((ImportPackageSpecification) unsatisfiedConstraint);
                    //if there's no unresolved exporter for this constraint,
                    //then try to list out all version unmatched exporter for this constraint
                    if (possibleExporters.isEmpty())
                    {
                        List<ExportPackageDescription> unmatchedExporters = getAllExporters(unsatisfiedConstraint.getName());
                        if (!unmatchedExporters.isEmpty())
                        {
                            nextLine(errors, indent + 1);
                            errors.append("Current unmatched exporter for this constraint: ");
                            for (ExportPackageDescription unmatchedExporter: unmatchedExporters)
                            {
                                nextLine(errors, indent + 2);
                                errors.append(unmatchedExporter + " (" + unmatchedExporter.getExporter() + ")");
                            }
                        }
                    }
                }
                
                for (ExportPackageDescription possibleExporter : possibleExporters)
                {
                    BundleDescription unsatisfied = possibleExporter.getExporter();
                    if (unsatisfied != null && !ignore.contains(unsatisfied))
                    {
                        setResolverErrors(unsatisfied, errors, indent + 1, visited, ignore);
                    }
                }
            }
            else if (unsatisfiedConstraint instanceof BundleSpecification)
            {
                List<BundleDescription> possibleSuppliers = getPossibleSuppliers((BundleSpecification) unsatisfiedConstraint);
                //no supplier was found, it is miss bundle, we will try to list out all version unmatched bundles for this constraint
                if (possibleSuppliers.isEmpty())
                {
                    BundleDescription[] suppliers = state.getBundles(unsatisfiedConstraint.getName());
                    if (suppliers.length > 0)
                    {
                        nextLine(errors, indent + 1);
                        errors.append("Current unmatched bundles for this constraint: ");
                        for (BundleDescription supplier: suppliers)
                        {
                            nextLine(errors, indent + 2);
                            errors.append(supplier);
                        }
                    }
                }
                
                for (BundleDescription possibleSupplier : possibleSuppliers)
                {
                    BundleDescription unsatisfied = possibleSupplier;
                    if (!ignore.contains(unsatisfied))
                    {
                        setResolverErrors(unsatisfied, errors, indent + 1, visited, ignore);
                    }
                }
            }
        }
    }

    /**
     * get the export package description of a bundle by package name
     */
    public ExportPackageDescription getExportPackage(BundleDescription bundle, 
            String packageName)
    {
        ExportPackageDescription[] exportPackages = bundle.getExportPackages();
        if (exportPackages != null)
        {
            for (ExportPackageDescription exportPackage : exportPackages)
            {
                if (exportPackage.getName().equals(packageName))
                {
                    return exportPackage;
                }
            }
        }
        return null;
    }

    /**
     * get possible suppliers by bundle name and version range
     */
    public List<BundleDescription> getPossibleSuppliers(BundleSpecification bundleSpecification)
    {
        // if the bundle specification has resolved supplier, then it is the only possible supplier
        if (bundleSpecification.getSupplier() != null)
        {
            List<BundleDescription> possibleSupplierList = new ArrayList<BundleDescription>();
            possibleSupplierList.add((BundleDescription) bundleSpecification.getSupplier());
            return possibleSupplierList;
        }
        else
        {
            return getAllSuppliers(bundleSpecification);
        }
    }

    /**
     * get all suppliers
     */
    public List<BundleDescription> getAllSuppliers(BundleSpecification bundleSpecification)
    {
        List<BundleDescription> possibleSupplierList = new ArrayList<BundleDescription>();
        BundleDescription[] suppliers = state.getBundles(bundleSpecification.getName());
        for (BundleDescription supplier : suppliers)
        {
            if (bundleSpecification.isSatisfiedBy(supplier))
            {
                possibleSupplierList.add(supplier);
            }
        }
        return possibleSupplierList;
    }
    
    /**
     * get possible exporters by import package name and version range
     */
    public List<ExportPackageDescription> getPossibleExporters(ImportPackageSpecification importPackage)
    {
        // if the import package specification has resolved exporter, then it is the only possible exporter
        if (importPackage.getSupplier() != null)
        {
            List<ExportPackageDescription> possibleExporterList = new ArrayList<ExportPackageDescription>();
            possibleExporterList.add((ExportPackageDescription) importPackage.getSupplier());
            return possibleExporterList;
        }
        else
        {
            return getAllExporters(importPackage);
        }
    }

    /**
     * get all exporters
     */
    public List<ExportPackageDescription> getAllExporters(ImportPackageSpecification importPackage)
    {
        List<ExportPackageDescription> possibleExporterList = new ArrayList<ExportPackageDescription>();
        ExportPackageDescription[] exportedPackages = state.getExportedPackages();
        for (ExportPackageDescription exportedPackage : exportedPackages)
        {
            if (importPackage.isSatisfiedBy(exportedPackage))
            {
                possibleExporterList.add(exportedPackage);
            }
        }
        return possibleExporterList;
    }
    
    /**
     * get all exporters by package name
     */
    public List<ExportPackageDescription> getAllExporters(String packageName)
    {
        List<ExportPackageDescription> possibleExporterList = new ArrayList<ExportPackageDescription>();
        ExportPackageDescription[] exportedPackages = state.getExportedPackages();
        for (ExportPackageDescription exportedPackage : exportedPackages)
        {
            if (packageName.equals(exportedPackage.getName()))
            {
                possibleExporterList.add(exportedPackage);
            }
        }

        return possibleExporterList;
    }
    
    /**
     * get unresolved exporters by import package name and version range
     */
    public List<ExportPackageDescription> getUnresolvedExporters(ImportPackageSpecification importPackage)
    {
        List<ExportPackageDescription> unresolvedExporterList = new ArrayList<ExportPackageDescription>();
        BundleDescription[] bundles = state.getBundles();
        for (BundleDescription bundle : bundles)
        {
            if (!bundle.isResolved())
            {
                ExportPackageDescription[] exportedPackages = bundle.getExportPackages();
                for (ExportPackageDescription exportedPackage : exportedPackages)
                {
                    if (importPackage.isSatisfiedBy(exportedPackage))
                    {
                        unresolvedExporterList.add(exportedPackage);
                    }
                }
            }
        }
        return unresolvedExporterList;
    }
    
    /**
     * get conflicts by the specified bundle
     * @return conflict error list
     */
    public List<ConflictError> getConflicts(BundleDescription bundle, BundleSpecification[] requires, ImportPackageSpecification[] imports)
    {
        List<ConflictError> conflicts = new ArrayList<ConflictError>();
        // create a bundle packages map for storing bundle's package roots
        Map<BundleDescription, Map<String, PackageRoots>> bundlePackages = new HashMap<BundleDescription, Map<String, PackageRoots>>();

        if (requires == null || requires.length == 0)
        {
            requires = bundle.getRequiredBundles();
        }

        if (imports == null || imports.length == 0)
        {
            imports = bundle.getImportPackages();
        }
        
        //first, check the required bundles of bundle
        for (BundleSpecification require : requires)
        {
            List<BundleDescription> possibleSuppliers = getPossibleSuppliers(require);
            for (BundleDescription possibleSupplier : possibleSuppliers)
            {
                List<ReferenceElement> referencePath = new ArrayList<ReferenceElement>();
                referencePath.add(new ReferenceElement(bundle,require));
                getConflicts(bundle, possibleSupplier, new ArrayList<BundleDescription>(1), conflicts, bundlePackages, referencePath);
            }
        }

        //check the import packages of bundle
        for (ImportPackageSpecification importPackage : imports)
        {
            List<ExportPackageDescription> possibleExporters = getPossibleExporters(importPackage);
            for (ExportPackageDescription possibleExporter : possibleExporters)
            {
                List<ReferenceElement> referencePath = new ArrayList<ReferenceElement>();
                referencePath.add(new ReferenceElement(bundle,importPackage));
                getConflicts(bundle, possibleExporter, conflicts, bundlePackages, referencePath);
            }
        }

        return conflicts;
    }

    /**
     * get conflicts between the requiring bundle and its required bundle
     * @param requiringBundle the requiring bundle
     * @param matchingBundle the required bundle of the requiring bundle 
     * @param visited visited bundle list
     * @param conflicts conflict error list
     * @param bundlePackages Map<bundle, Map<package name, package roots>>
     * @param referencePath the reference path of matchingBundle
     */
    private void getConflicts(BundleDescription requiringBundle, 
            BundleDescription matchingBundle, List<BundleDescription> visited, 
            List<ConflictError> conflicts, 
            Map<BundleDescription, Map<String, PackageRoots>> bundlePackages, 
            List<ReferenceElement> referencePath)
    {
        if (visited.contains(matchingBundle))
            return;
        visited.add(matchingBundle);
        ExportPackageDescription[] exportPackages = matchingBundle.getExportPackages();
        if (exportPackages != null)
        {
            // check each export package of the matchingBundle
            // since those packages are exported, they are included in the class space of the specified bundle
            // we need to make sure the class space is consistent
            for (ExportPackageDescription exportPackage : exportPackages)
            {
                getConflicts(requiringBundle, exportPackage, conflicts, bundlePackages, referencePath);
            }
        }
    }

    /**
     * get conflicts between the importing bundle and the export package of its dependency
     */
    private void getConflicts(BundleDescription importingBundle, 
            ExportPackageDescription matchingExport, List<ConflictError> conflicts, 
            Map<BundleDescription, Map<String, PackageRoots>> bundlePackages, 
            List<ReferenceElement> referencePath)
    {
        PackageRoots exportingRoots = getPackageRoots(matchingExport.getExporter(), matchingExport.getName(), null, bundlePackages, referencePath);
        // check that the exports uses packages are consistent with existing package roots
        exportingRoots.isConsistentClassSpace(importingBundle, null, conflicts, bundlePackages);
    }

    /**
     * get the root exports of the specified package from the class space of the specified bundle
     * @return PackageRoots
     */
    private PackageRoots getPackageRoots(BundleDescription bundle, 
            String packageName, List<BundleDescription> visited, 
            Map<BundleDescription, Map<String, PackageRoots>> bundlePackages, 
            List<ReferenceElement> referencePath)
    {
        Map<String, PackageRoots> packages = bundlePackages.get(bundle);
        if (packages == null)
        {
            packages = new HashMap<String, PackageRoots>(5);
            bundlePackages.put(bundle, packages);
        }
        // check whether the package roots is cached
        PackageRoots packageRoots = packages.get(packageName);
        if (packageRoots == null)
        {
            packageRoots = createPackageRoots(bundle, packageName, visited == null ? new ArrayList<BundleDescription>(1) : visited, bundlePackages, referencePath);
            packages.put(packageName, packageRoots);
        }
        return packageRoots;
    }

    /**
     * create a new package roots
     * @return PackageRoots
     */
    private PackageRoots createPackageRoots(BundleDescription bundle, 
            String packageName, List<BundleDescription> visited, 
            Map<BundleDescription, Map<String, PackageRoots>> bundlePackages, 
            List<ReferenceElement> referencePath)
    {
        if (visited.contains(bundle))
            return null;
        visited.add(bundle); // prevent endless cycles
        // check imports
        ImportPackageSpecification[] imports = bundle.getImportPackages();
        for (ImportPackageSpecification importPackage : imports)
        {
            if (importPackage.getName().equals(packageName))
            {
                List<PackageRoots> packageRootsList = new ArrayList<PackageRoots>();
                // found import; get the roots from the possible exporters;
                List<ExportPackageDescription> possibleExporters = getPossibleExporters(importPackage);
                for (ExportPackageDescription exportPackage : possibleExporters)
                {
                    // make sure we are not resolved to our own import
                    if (exportPackage.getExporter() != bundle)
                    {
                        List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePath);
                        pathCopy.add(new ReferenceElement(bundle, importPackage));
                        PackageRoots packageRoots = getPackageRoots(exportPackage.getExporter(), packageName, visited, bundlePackages, pathCopy);
                        bindReferenceMap(bundle, exportPackage.getExporter(), packageRoots, pathCopy);
                        packageRootsList.add(packageRoots);
                    }
                }
                // this is all the roots if the package is imported
                if (packageRootsList.size() > 0)
                    return mergePackageRoots(packageRootsList);
                
                break;
            }
        }

        List<PackageRoots> packageRootsList = new ArrayList<PackageRoots>();
        // check roots from required bundles
        BundleSpecification[] requires = bundle.getRequiredBundles();
        if (requires != null)
        {
            for (BundleSpecification require : requires)
            {
                List<BundleDescription> possibleSuppliers = getPossibleSuppliers(require);
                for (BundleDescription supplier : possibleSuppliers)
                {
                    if (getExportPackage(supplier, packageName) != null)
                    {
                        // the required bundle exports the package; get the package roots from it
                        List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePath);
                        pathCopy.add(new ReferenceElement(bundle, require));
                        PackageRoots requiredRoots = getPackageRoots(supplier, packageName, visited, bundlePackages, pathCopy);
                        bindReferenceMap(bundle, supplier, requiredRoots, pathCopy);
                        packageRootsList.add(requiredRoots);
                    }
                    else
                    {
                        // the bundle does not export the package; but it may reexport another bundle that does
                        BundleSpecification[] supplierRequires = supplier.getRequiredBundles();
                        if (supplierRequires != null)
                        {
                            for (BundleSpecification supplierRequire : supplierRequires)
                            {
                                if (supplierRequire.isExported())
                                {
                                    BundleDescription reexported = (BundleDescription) supplierRequire.getSupplier();
                                    if (reexported != null && getExportPackage(reexported, packageName) != null)
                                    {
                                        // the reexported bundle exports the package; get the package roots from it
                                        List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePath);
                                        pathCopy.add(new ReferenceElement(bundle, require));
                                        pathCopy.add(new ReferenceElement(supplier, supplierRequire));
                                        PackageRoots reExportedRoots = getPackageRoots(reexported, packageName, visited, bundlePackages, pathCopy);
                                        bindReferenceMap(bundle, reexported, reExportedRoots, pathCopy);
                                        packageRootsList.add(reExportedRoots);
                                    }// if the reexported bundle exports the package
                                }// if the supplier require is exported
                            }// for each supplier require
                        }// if the supplier requires is not null
                    }// if the supplier does not export the package
                }// for each supplier
            }// for each bundle require
        }// if bundle require is not null
        
        PackageRoots result = mergePackageRoots(packageRootsList);
        // check if the bundle exports the package
        ExportPackageDescription export = getExportPackage(bundle, packageName);
        if (export != null)
        {
            // If there's no PackageRoots in the PackageRoots list, we need to create a new specific
            if (result == null) {
                result = new PackageRoots();
            } else if (result == packageRootsList.get(0)) {
                // in this case we cannot share the package roots object; must create one specific for this bundle
                result = new PackageRoots();
                result.merge(packageRootsList.get(0));
            }
            // add this bundles export to the end if it exports the package
            List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePath);
            pathCopy.add(new ReferenceElement(bundle, export));
            Map<BundleDescription, List<ReferenceElement>> bundleReferencePathMap = new HashMap<BundleDescription, List<ReferenceElement>>();
            bundleReferencePathMap.put(bundle, pathCopy);
            result.addRoot(export, bundleReferencePathMap);
        }
        return result == null ? nullPackageRoots : result;
    }

    /**
     * bind the reference path for the reference path map of PackageRoots
     * @see PackageRoots.referencePathMap
     * @param bindBundle the referencer of the package roots
     * @param exporter   the exporter of the package roots
     * @param packageRoots
     * @param referencePath
     */
    private void bindReferenceMap(BundleDescription bindBundle, BundleDescription exporter, 
            PackageRoots packageRoots, List<ReferenceElement> referencePath)
    {
        // bind the reference path for each root in package roots 
        for(Map<BundleDescription, List<ReferenceElement>> bundleReferencePathMap : packageRoots.referencePathMap.values())
        {
            // if the reference path hasn't been bound for this bundle
            if (!bundleReferencePathMap.containsKey(bindBundle))
            {
                // build the reference path based on the exporter reference path
                List<ReferenceElement> exporterReferencePath = bundleReferencePathMap.get(exporter);
                if (exporterReferencePath != null)
                {
                    List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePath);
                    boolean findExporter = false;
                    for (ReferenceElement referenceElement : exporterReferencePath)
                    {
                        // copy the segment which start with the exporter from the exporter reference path to the new reference path
                        // the reason to do this is that some roots may not be directly exported by the exporter, but by its reexported dependencies
                        if (findExporter || referenceElement.bundle == exporter)
                        {
                            findExporter = true;
                            pathCopy.add(referenceElement);
                        }
                    }
                    bundleReferencePathMap.put(bindBundle, pathCopy);
                }

            }
        }
    }
    
    /**
     * merge PackageRoots list to one PackageRoots 
     * @param packageRootsList
     * @return
     */
    private PackageRoots mergePackageRoots(List<PackageRoots> packageRootsList)
    {
        if (packageRootsList.size() > 1)
        {
            // if there's a super set in the PackageRoots list, i.e. it contains all others exporters, 
            // then we should use it as the share PackageRoots
            PackageRoots superSet = packageRootsList.get(0);
            for (PackageRoots packageRoots : packageRootsList)
            {
                if (packageRoots.superSet(superSet))
                {
                    superSet = packageRoots;
                }
                else if (!superSet.superSet(packageRoots))
                {
                    superSet = null;
                    break;
                }
            }
            if (superSet != null)
                return superSet;
            // if there's no super set in the PackageRoots list, then we cannot share the package roots object; 
            // must create one specific for this bundle
            PackageRoots result = new PackageRoots();
            // first merge all the roots from required bundles
            for (PackageRoots packageRoots : packageRootsList)
                result.merge(packageRoots);
            return result;
        }
        else if (packageRootsList.size() == 1)
            // there's only one PackageRoots
            return packageRootsList.get(0);
        return null;
    }
    
    /**
     * A list of root exporters of a package and their reference paths which
     * start from the specified bundle
     * 
     * @see createPackageRoots
     */
    private class PackageRoots
    {
        private List<ExportPackageDescription> roots = new ArrayList<ExportPackageDescription>();
        private Map<ExportPackageDescription, Map<BundleDescription, List<ReferenceElement>>> referencePathMap = new HashMap<ExportPackageDescription, Map<BundleDescription, List<ReferenceElement>>>();
        
        public void addRoot(ExportPackageDescription export, Map<BundleDescription, List<ReferenceElement>> bundleReferencePathMap)
        {
            if (!roots.contains(export))
            {
             // need to do an extra check to make sure we are not adding the same package name from multiple versions of the same bundle
                String exportBSN = export.getExporter().getName();
                if (exportBSN != null)
                {
                    // first one wins
                    for (ExportPackageDescription root : roots)
                        if (exportBSN.equals(root.getExporter().getName()))
                            return;
                }
                roots.add(export);
                referencePathMap.put(export, bundleReferencePathMap);
            }
        }

        public void merge(PackageRoots packageRoots)
        {
            if (packageRoots == null)
                return;
            for (ExportPackageDescription root : packageRoots.roots)
                addRoot(root, packageRoots.referencePathMap.get(root));
        }

        private boolean subSet(List<ExportPackageDescription> superSet, List<ExportPackageDescription> subSet)
        {
            return superSet.containsAll(subSet);
        }

        public boolean superSet(PackageRoots subSet)
        {
            return subSet(roots, subSet.roots);
        }
        
        /**
         * check whether the uses packages of the export package is consistent in the class space of the importing bundle
         */
        public void isConsistentClassSpace(BundleDescription importingBundle, 
                List<PackageRoots> visited, List<ConflictError> results, 
                Map<BundleDescription, Map<String, PackageRoots>> bundlePackages)
        {
            if (visited == null)
                visited = new ArrayList<PackageRoots>(1);
            if (visited.contains(this))
                return;
            visited.add(this);
            
            for (ExportPackageDescription root : roots)
            {
                String[] uses = (String[]) root.getDirective("uses");
                if (uses == null)
                    continue;
                for (String usesPackage : uses)
                {
                    if (usesPackage.equals(root.getName()))
                        continue;
                    List<ReferenceElement> pathCopy = new ArrayList<ReferenceElement>(referencePathMap.get(root).get(root.getExporter()));
                    if (pathCopy.size() >= 1)
                    {
                        // since this reference path will be used by different exporter, so we need to remove the current exporter
                        pathCopy.remove(pathCopy.size() - 1);
                    }
                    // get the package roots of the uses package from current exporter
                    PackageRoots thisUsedRoots = getPackageRoots(root.getExporter(), usesPackage, null, bundlePackages, pathCopy);
                    // get the package roots of the uses package from importing bundle 
                    PackageRoots importingUsedRoots = getPackageRoots(importingBundle, usesPackage, null, bundlePackages, new ArrayList<ReferenceElement>());
                    // if the package roots between current exporter and importing bundle don't have intersection,
                    // that means there're different version of the same class in the class space of importing bundle,
                    // so the conflict error happen
                    if (thisUsedRoots == importingUsedRoots)
                        continue;
                    if (thisUsedRoots != nullPackageRoots && importingUsedRoots != nullPackageRoots)
                    {
                        if (subSet(thisUsedRoots.roots, importingUsedRoots.roots))
                        {
                            thisUsedRoots.roots.removeAll(importingUsedRoots.roots);
                        }
                        else if (subSet(importingUsedRoots.roots, thisUsedRoots.roots))
                        {
                            importingUsedRoots.roots.removeAll(thisUsedRoots.roots);
                        }
                        
                        if (results == null)
                            results = new ArrayList<ConflictError>(1);
                        ConflictError conflictError = new ConflictError(usesPackage, root, thisUsedRoots, importingBundle, importingUsedRoots);
                        if (!results.contains(conflictError))
                            results.add(conflictError);
                    }
                    // need to check the usedRoots consistency for transitive closure
                    thisUsedRoots.isConsistentClassSpace(importingBundle, visited, results, bundlePackages);
                }
            }
        }
    }

    /**
     * A wrapper for the elements of conflict error
     * contains three fields:
     *     1. packageName the name of the conflict package
     *     2. dependencyUsedRoots the conflict package roots of the specified bundle's dependency
     *     3. importingUsedRoots the conflict package roots of the specified bundle
     */
    private static class ConflictError
    {
        private String packageName;
        private ExportPackageDescription exportPackage;
        private PackageRoots dependencyUsedRoots;
        private PackageRoots importingUsedRoots;
        private BundleDescription importingBundle;
        
        public ConflictError(String packageName, 
                ExportPackageDescription exportPackage, PackageRoots implicitUsedRoots, 
                BundleDescription importingBundle, PackageRoots explicitUsedRoots)
        {
            this.packageName = packageName;
            this.exportPackage = exportPackage;
            this.dependencyUsedRoots = implicitUsedRoots;
            this.importingUsedRoots = explicitUsedRoots;
            this.importingBundle = importingBundle;
        }

        public boolean equals(Object object)
        {
            if (object instanceof ConflictError)
            {
                ConflictError another = (ConflictError) object;
                if (another.packageName.equals(packageName) 
                        && another.dependencyUsedRoots.referencePathMap.equals(dependencyUsedRoots.referencePathMap) 
                        && another.importingUsedRoots.referencePathMap.equals(importingUsedRoots.referencePathMap))
                    return true;
            }
            return false;
        }
        
        public String toString()
        {
            return toString(0);
        }
        
        public String toString(int indent)
        {
            StringBuffer errors = new StringBuffer();
            nextLine(errors, indent);
            errors.append("Conflicting package?: ");
            errors.append(packageName);
            formatErrorMessage(errors, indent + 1, importingBundle, importingUsedRoots);
            formatErrorMessage(errors, indent + 1, exportPackage.getExporter(), dependencyUsedRoots);
            return errors.toString();
        }
        
        private void formatErrorMessage(StringBuffer errors, int indent, 
                BundleDescription bundle, PackageRoots packageRoots)
        {
            for (ExportPackageDescription root : packageRoots.roots)
            {
                List<ReferenceElement> referencePath = packageRoots.referencePathMap.get(root).get(bundle);
                if (referencePath.size() >= 2)
                {
                    ReferenceElement conflictReference = referencePath.get(referencePath.size() - 2);
                    nextLine(errors, indent);
                    if (conflictReference.isViaBundle())
                        errors.append("via Require-Bundle: ");
                    else
                        errors.append("via Import-Package: ");
                    errors.append(referencePath.get(referencePath.size() - 1));
                    for (int i = referencePath.size() - 2; i >= 0; i--)
                    {
                        nextLine(errors, indent + 1);
                        errors.append(referencePath.get(i));
                    }
                }
            }
        }
    }
    
    /**
     * A wrapper for the elements of bundle's reference
     * contains three fields:
     *     1. bundle the bundle
     *     2. constraint the constraint of the bundle, import package or require bundle
     *     3. exportPackage the export package of the bundle
     * If the exportPackage is not null, that means it is the end of a reference path - we finally find the root exporter
     */
    private static class ReferenceElement
    {
        private BundleDescription bundle;
        private VersionConstraint constraint;
        private ExportPackageDescription exportPackage;
        
        public ReferenceElement(BundleDescription bundle, VersionConstraint constraint)
        {
            this.bundle = bundle;
            this.constraint = constraint;
        }
        
        public ReferenceElement(BundleDescription bundle, ExportPackageDescription exportPackage)
        {
            this.bundle = bundle;
            this.exportPackage = exportPackage;
        }
        
        public boolean isViaBundle()
        {
            return constraint instanceof BundleSpecification;
        }
        
        public String toString()
        {
            StringBuffer strbuf = new StringBuffer();
            strbuf.append(bundle);
            if (exportPackage == null)
            {
                strbuf.append(" --has-- ");
                strbuf.append(constraint);
            }
            else
            {
                strbuf.append(' ');
                strbuf.append(exportPackage);
            }
            return strbuf.toString();
        }
    }
}
