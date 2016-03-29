package com.tibco.devtools.builder.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FeatureClassPathClosure
{
    public FeatureClassPathClosure(List<BundleInfo> bundles)
    {
        // give me null, get what you deserve
        for (BundleInfo bundle : bundles)
        {
            BundleClassPathClosure bundle_closure = new BundleClassPathClosure(bundle, bundles);
            m_closure.addAll(bundle_closure.getClasspath());
        }
    }

    public List<String> getClasspath()
    {
        List<String> results = new ArrayList<String>(m_closure.size());
        results.addAll(m_closure);
        return results;
    }

    private Set<String> m_closure = new HashSet<String>();
}
