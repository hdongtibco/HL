package com.tibco.devtools.buildscripts;

public interface ScriptConstants
{
    // TODO: ideally, the obfuscation-specific constants should be pulled out into
    //       a separate (or two separate) interfaces, in the obfuscation packages

    // TODO: add constants for the remaining ant core tasks

    // note: organized alphabetically, with the simple constants in the first list,
    //       the second list made up of "composed" constants (fileset = file + set)
    static final String S = "s";

    static final String ACCESS = "access";
    static final String ADJUST = "adjust";
    static final String AGGRESSIVELY = "aggressively";
    static final String ALL = "all";
    static final String ALLOW = ALL + "ow";
    static final String ANNOTATIONS = "annotations";
    static final String ANT = "ant";
    static final String APPEND = "append";
    static final String APPLY = "apply";
    static final String ARG = "arg";
    static final String ASSERTIONS = "assertions";
    static final String ASSUME = "assume";
    static final String ATTRIBUTE = "attribute";
    static final String ATTRIBUTES = ATTRIBUTE + S;
    static final String AUTHOR = "author";
    static final String AVAILABLE = "available";

    static final String BASE = "base";
    static final String BATCH = "batch";
    static final String BEST = "best";
    static final String BOOT = "boot";
    static final String BOTTOM = "bottom";
    static final String BREAK = "break";

    static final String CALL = "call";
    static final String CASE = "case";
    static final String CHAIN = "chain";
    static final String CHAR = "char";
    static final String CHECK = "check";
    static final String CLASS = "class";
    static final String COMPATIBLE = "compatible";
    static final String COMPRESSION = "compression";
    static final String CONDITION = "condition";
    static final String CONFIGURATION = "configuration";
    static final String CONFORMITY = "conformity";
    static final String CONSERVE = "conserve";
    static final String CONSTRUCTOR = "constructor";
    static final String COPY = "copy";

    static final String DASH = "-";
    static final String DEFAULT = "default";
    static final String DELETE = "delete";
    static final String DEPENDS = "depends";
    static final String DEPRECATED = "deprecated";
    static final String DESCRIPTION = "description";
    static final String DEST = "dest";
    static final String DICTIONARY = "dictionary";
    static final String DIR = "dir";
    static final String DIRS = DIR + S;
    static final String DOC = "doc";
    static final String DUMP = "dump";

    static final String EAR = "ear";
    static final String ECHO = "echo";
    static final String EFFECTS = "effects";
    static final String ELEMENT = "element";
    static final String EMPTY = "empty";
    static final String ENABLE = "enable";
    static final String ENCODING = "encoding";
    static final String ENV = "env";
    static final String ENVIRONMENT = ENV + "ironment";
    static final String ERROR = "error";
    static final String ES = "es";
    static final String EXCLUDE = "exclude";
    static final String EXCLUDES = EXCLUDE + S;
    static final String EXIT = "exit";
    static final String EXISTS = "exists";
    static final String EXPOSE = "expose";
    static final String EXTENDS = "extends";
    static final String EXTENSION = "extension";
    static final String EXTERNAL = "external";

    static final String FAIL = "fail";
    static final String FAILURE = FAIL + "ure";
    static final String FIELD = "field";
    static final String FIELDS = FIELD + S;
    static final String FILE = "file";
    static final String FILES = FILE + S;
    static final String FILTER = "filter";
    static final String FLATTEN = "flatten";
    static final String FOLLOW = "follow";
    static final String FOOTER = "footer";
    static final String FORK = "fork";
    static final String FORMAT = "format";
    static final String FORMATTER = FORMAT + "ter";
    static final String FRIENDLY = "friendly";

    static final String GRANULARITY = "granularity";
    static final String GROUP = "group";

    static final String HALT = "halt";
    static final String HEADER = "header";
    static final String HELP = "help";

    static final String ID = "id";
    static final String IF = "if";
    static final String IGNORE = "ignore";
    static final String IMPLEMENTS = "implements";
    static final String IMPORT = "import";
    static final String IN = "in";
    static final String INDEX = "index";
    static final String ING = IN + "g";
    static final String INCLUDE = "include";
    static final String INCLUDES = INCLUDE + S;
    static final String INHERIT = "inherit";
    static final String ITERATOR = "iterator";

    static final String JAR = "jar";
    static final String JAVA = "java";
    static final String JUNIT = "junit";
    static final String JVM = "jvm";

    static final String KEEP = "keep";
    static final String KEY = "key";

    static final String LANGUAGE = "language";
    static final String LAST = "last";
    static final String LEGAL = "legal";
    static final String LESS = "less";
    static final String LET = "let";
    static final String LEVEL = "level";
    static final String LIB = "lib";
    static final String LINE = "line";
    static final String LINK = "link";
    static final String LINKS = LINK + S;
    static final String LIST = "list";
    static final String LOAD = "load";
    static final String LOCAL = "local";
    static final String LOCALE = LOCAL + "e";
    static final String LOCATION = "location";
    static final String LOG = "log";

    static final String MAIN = "main";
    static final String MANIFEST = "manifest";
    static final String MAP = "map";
    static final String MAPPER = "mapper";
    static final String MAPPING = MAP + "p" + ING;
    static final String MAPPINGS = MAPPING + S;
    static final String MAX = "max";
    static final String MEMBER = "member";
    static final String MEMBERS = MEMBER + S;
    static final String MEMORY = "memory";
    static final String MESSAGE = "message";
    static final String METHOD = "method";
    static final String METHODS = METHOD + S;
    static final String MIX = "mix";
    static final String MIXED = MIX + "ed";
    static final String MKDIR = "mkdir";
    static final String MODE = "mode";
    static final String MODIFICATIONS = "modifications";
    static final String MODIFIED = "modified";
    static final String MOVE = "move";
    static final String MULTIPLE = "multiple";

    static final String NAME = "name";
    static final String NAMES = NAME + S;
    static final String NAMING = "nam" + ING;
    static final String NATIVE = "native";
    static final String NAVBAR = "navbar";
    static final String NEW = "new";
    static final String NO = "no"; // no means no!  what part of no don't you understand?
    static final String NON = NO + "n";
    static final String NONE = NON + "e";
    static final String NOTE = "note";
    static final String NUMBER = "number";

    static final String OBFUSCATE = "obfuscate";
    static final String OBFUSCATION = "obfuscation";
    static final String ON = "on";
    static final String OPTIMIZE = "optimize";
    static final String OPTIONAL = "optional";
    static final String OUT = "out";
    static final String OUTPUT = "output";
    static final String OVER = "over";
    static final String OVERLOAD = OVER + LOAD;
    static final String OVERWRITE = "overwrite";

    static final String PACKAGE = "package";
    static final String PACKAGES = PACKAGE + S;
    static final String PAIR = "pair";
    static final String PARAM = "param";
    static final String PARAMETER = PARAM + "eter";
    static final String PARAMETERS = PARAMETER + S;
    static final String PATCH = "patch";
    static final String PATH = "path";
    static final String PATTERN = "pattern";
    static final String PEDANTIC = "pedantic";
    static final String PERMISSIONS = "permissions";
    static final String PREFIX = "prefix";
    static final String PRESERVE = "preserve";
    static final String PRINT = "print";
    static final String PRIVATE = "private";
    static final String PROGUARD = "proguard";
    static final String PROGUARD4 = PROGUARD + "4";
    static final String PROJECT = "project";
    static final String PROPERTY = "property";
    static final String PROTECTED = "protected";
    static final String PUBLIC = "public";

    static final String QUIET = "quiet";

    static final String REF = "ref";
    static final String REFS = REF + S;
    static final String RELOAD = "reload";
    static final String RENAME = "rename";
    static final String REPLACE = "replace";
    static final String REPORT = "report";
    static final String RESOURCE = "resource";
    static final String RUNTIME = "runtime";

    static final String SCHEME = "scheme";
    static final String SEEDS = "seeds";
    static final String SENSITIVE = "sensitive";
    static final String SERIAL = "serial";
    static final String SET = "set";
    static final String SHOW = "show";
    static final String SHRINK = "shrink";
    static final String SIDE = "side";
    static final String SIZE = "size";
    static final String SKIP = "skip";
    static final String SLASH = "/";
    static final String SMALL = "small";
    static final String SOURCE = "source";
    static final String SPLAT = "*";
    static final String SPLIT = "split";
    static final String SRC = "src";
    static final String STYLE = "style";
    static final String SUMMARY = "summary";
    static final String SYS = "sys";

    static final String TABLE = "table";
    static final String TAG = "tag";
    static final String TARGET = "target";
    static final String TEMP = "temp";
    static final String TEST = "test";
    static final String TIBCO = "TIBCO Software Inc.";
    static final String TIME = "time";
    static final String TITLE = "title";
    static final String TO = "to";
    static final String TRACE = "trace";
    static final String TREE = "tree";
    static final String TYPE = "type";

    static final String UN = "un";
    static final String UNIQUE = UN + "ique";
    static final String URL = "url";
    static final String USAGE = "usage";
    static final String USE = "use";
    static final String UTF8 = "UTF-8";

    static final String VALUE = "value";
    static final String VARIABLE = "variable";
    static final String VERBOSE = "verbose";
    static final String VERSION = "version";
    static final String VISIBLE = "visible";

    static final String WAR = "war";
    static final String WARN = WAR + "n";
    static final String WARNING = WARN + ING;
    static final String WARNINGS = WARNING + S;
    static final String WHYAREYOU = "whyareyou";
    static final String WINDOW = "window";
    static final String WITH = "with";

    static final String YGUARD = "yguard";
    static final String YGUARD2 = YGUARD + "2";

    static final String ZIP = "zip";

    static final String ALLOWACCESSMODIFICATIONS = ALLOW + ACCESS + MODIFICATIONS;
    static final String ANTCALL = ANT + CALL;
    static final String ANTFILE = ANT + FILE;
    static final String APPLYMAPPING = APPLY + MAPPING;
    static final String ASSUMENOSIDEEFFECTS = ASSUME + NO + SIDE + EFFECTS;
    static final String BASEDIR = BASE + DIR;
    static final String BATCHTEST = BATCH + TEST;
    static final String BREAKITERATOR = BREAK + ITERATOR;
    static final String CASESENSITIVE = CASE + SENSITIVE;
    static final String CHARSET = CHAR + SET;
    static final String CLASSES = CLASS + ES;
    static final String CLASSNAME = CLASS + NAME;
    static final String CLASSPATH = CLASS + PATH;
    static final String CLASSPATHREF = CLASSPATH + REF;
    static final String CONSERVEMANIFEST = CONSERVE + MANIFEST;

    static final String DEFAULTEXCLUDES = DEFAULT + EXCLUDES;
    static final String DEFAULTPACKAGE = DEFAULT + PACKAGE;
    static final String DELETEONEXIT = DELETE + ON + EXIT;
    static final String DESTDIR = DEST + DIR;
    static final String DESTFILE = DEST + FILE;
    static final String DIRECTORY = DIR + "ectory";
    static final String DIRSET = DIR + SET;
    static final String DOCENCODING = DOC + ENCODING;
    static final String DOCLET = DOC + LET;
    static final String DOCTITLE = DOC + TITLE;
    static final String EARFILTER = EAR + FILTER;
    static final String ENABLEMULTIPLEMAPPINGS = ENABLE + MULTIPLE + MAPPINGS;
    static final String ERROR_CHECKING = ERROR + DASH + CHECK + ING;
    static final String ERRORPROPERTY = ERROR + PROPERTY;
    static final String EXCLUDEPACKAGE = EXCLUDE + PACKAGE;
    static final String EXCLUDESFILE = EXCLUDES + "file";
    static final String EXPOSE_ATTRIBUTES = EXPOSE + DASH + ATTRIBUTES;
    static final String EXTERNALCLASSES = EXTERNAL + CLASSES;
    static final String FAILONERROR = FAIL + ON + ERROR;
    static final String FAILUREPROPERTY = FAILURE + PROPERTY;
    static final String FILELIST = FILE + LIST;
    static final String FILESET = FILE + SET;
    static final String FILTERCHAIN = FILTER + CHAIN;
    static final String FILTERING = FILTER + ING;
    static final String FILTERSET = FILTER + SET;
    static final String FILTERTRACE = FILTER + TRACE;
    static final String FOLLOWSYMLINKS = FOLLOW + "sym" + LINKS;
    static final String FORKMODE = FORK + MODE;
    static final String GZIP = "g" + ZIP;
    static final String HALTONERROR = HALT + ON + ERROR;
    static final String HALTONFAILURE = HALT + ON + FAILURE;

    static final String IGNOREWARNINGS = IGNORE + WARNINGS;
    static final String ILLEGAL = "il" + LEGAL;
    static final String INCLUDEANTRUNTIME = INCLUDE + ANT + RUNTIME;
    static final String INCLUDEEMPTYDIRS = INCLUDE + EMPTY + DIRS;
    static final String INCLUDENOSOURCEPACKAGES = INCLUDE + NO + SOURCE + PACKAGES;
    static final String INCLUDESFILE = INCLUDES + FILE;
    static final String INHERITALL = INHERIT + ALL;
    static final String INHERITREFS = INHERIT + REFS;
    static final String INJAR = IN + JAR;
    static final String INOUTPAIR = IN + OUT + PAIR;
    static final String INOUTPAIRS = INOUTPAIR + S;
    static final String INVISIBLE = IN + VISIBLE;
    static final String JARFILTER = JAR + FILTER;
    static final String JAVAC = JAVA + "c";
    static final String JAVADOC = JAVA + DOC;
    static final String JUNITREPORT = JUNIT + REPORT;
    static final String JVMARG = JVM + ARG;
    static final String KEEPATTRIBUTE = KEEP + ATTRIBUTE;
    static final String KEEPCLASSESWITHMEMBERNAMES = KEEP + CLASSES + WITH + MEMBER + NAMES;
    static final String KEEPCLASSESWITHMEMBERS = KEEP + CLASSES + WITH + MEMBERS;
    static final String KEEPCLASSMEMBERNAMES = KEEP + CLASS + MEMBER + NAMES;
    static final String KEEPCLASSMEMBERS = KEEP + CLASS + MEMBERS;
    static final String KEEPNAMES = KEEP + NAMES;

    static final String LANGUAGE_CONFORMITY = LANGUAGE + DASH + CONFORMITY;
    static final String LASTMODIFIED = LAST + "modified";
    static final String LIBRARY = LIB + "rary";
    static final String LIBRARYJAR = LIBRARY + JAR;
    static final String LINENUMBERTABLE = LINE + NUMBER + TABLE;
    static final String LINKSOURCE = LINK + SOURCE;
    static final String LOCALVARIABLETABLE = LOCAL + VARIABLE + TABLE;
    static final String LOCALVARIABLETYPETABLE = LOCAL + VARIABLE + TYPE + TABLE;
    static final String LOGFILE = LOG + FILE;
    static final String MAINCLASS = MAIN + CLASS;
    static final String MAXMEMORY = MAX + MEMORY;
    static final String NAMING_SCHEME = NAMING + DASH + SCHEME;
    static final String NEWENVIRONMENT = NEW + ENVIRONMENT;
    static final String NODEPRECATED = NO + DEPRECATED;
    static final String NODEPRECATEDLIST = NODEPRECATED + LIST;
    static final String NOHELP = NO + HELP;
    static final String NOINDEX = NO + INDEX;
    static final String NONAVBAR = NO + NAVBAR;
    static final String NOTREE = NO + TREE;

    static final String OBFUSCATIONDICTIONARY = OBFUSCATION + DICTIONARY;
    static final String OBFUSCATION_PREFIX = OBFUSCATION + DASH + PREFIX;
    static final String OUTFILE = OUT + FILE;
    static final String OUTJAR = OUT + JAR;
    static final String OUTPUTENCODING = OUTPUT + ENCODING;
    static final String OVERLOADAGGRESSIVELY = OVERLOAD + AGGRESSIVELY;
    static final String PACKAGELIST = PACKAGE + LIST;
    static final String PACKAGENAMES = PACKAGE + NAMES;
    static final String PACKAGESET = PACKAGE + SET;
    static final String PATHELEMENT = PATH + ELEMENT;
    static final String PATHREF = PATH + REF;
    static final String PATTERNSET = PATTERN + SET;
    static final String PRESERVELASTMODIFIED = PRESERVE + LAST + MODIFIED;
    static final String PRINTMAPPING = PRINT + MAPPING;
    static final String PRINTSEEDS = PRINT + SEEDS;
    static final String PRINTSUMMARY = PRINT + SUMMARY;
    static final String PRINTUSAGE = PRINT + USAGE;
    static final String PROGUARDCONFIGURATION = PROGUARD + CONFIGURATION;
    static final String PROGUARDCONFIGURATION4 = PROGUARDCONFIGURATION + "4";
    static final String PROPERTYSET = PROPERTY + SET;

    static final String REFERENCE = REF + "erence";
    static final String REFID = REF + ID;
    static final String RELOADING = RELOAD + ING;
    static final String RENAMESOURCEFILEATTRIBUTE = RENAME + SOURCE + FILE + ATTRIBUTE;
    static final String RUNTIMEINVISIBLEANNOTATIONS = RUNTIME + INVISIBLE + ANNOTATIONS;
    static final String RUNTIMEINVISIBLEPARAMETERANNOTATIONS = RUNTIME + INVISIBLE + PARAMETER + ANNOTATIONS;
    static final String RUNTIMEVISIBLEANNOTATIONS = RUNTIME + VISIBLE + ANNOTATIONS;
    static final String RUNTIMEVISIBLEPARAMETERANNOTATIONS = RUNTIME + VISIBLE + PARAMETER + ANNOTATIONS;
    static final String SERIALWARN = SERIAL + WARN;
    static final String SHOWOUTPUT = SHOW + OUTPUT;
    static final String SKIPNONPUBLICLIBRARYCLASSES = SKIP + NON + PUBLIC + LIBRARY + CLASSES;
    static final String SKIPNONPUBLICLIBRARYCLASSMEMBERS = SKIP + NON + PUBLIC + LIBRARY + CLASS + MEMBERS;
    static final String SOURCEFILE = SOURCE + FILE;
    static final String SOURCEFILES = SOURCE + FILES;
    static final String SOURCEPATH = SOURCE + PATH;
    static final String SOURCEPATHREF = SOURCEPATH + REF;
    static final String SPLATSPLAT = SPLAT + SPLAT;
    static final String SPLATSPLATSLASHSPLAT = SPLATSPLAT + SLASH + SPLAT;
    static final String SPLITINDEX = SPLIT + INDEX;
    static final String STYLEDIR = STYLE + DIR;
    static final String SYSPROPERTY = SYS + PROPERTY;
    static final String SYSPROPERTYSET = SYSPROPERTY + SET;

    static final String TAGLET = TAG + LET;
    static final String TEMPDIR = TEMP + DIR;
    static final String TIMEOUT = TIME + OUT;
    static final String TODIR = TO + DIR;
    static final String TOFILE = TO + FILE;
    static final String TOREFID = TO + REFID;
    static final String UNLESS = UN + LESS;
    static final String UNZIP = UN + ZIP;
    static final String USEEXTERNALFILE = USE + EXTERNAL + FILE;
    static final String USEFILE = USE + FILE;
    static final String USEMIXEDCASECLASSNAMES = USE + MIXED + CASE + CLASS + NAMES;
    static final String USEUNIQUECLASSMEMBERNAMES = USE + UNIQUE + CLASS + MEMBER + NAMES;
    static final String WARFILTER = WAR + FILTER;
    static final String WHYAREYOUKEEPING = WHYAREYOU + KEEP + ING;
    static final String WINDOWTITLE = WINDOW + TITLE;
    static final String ZIPFILTER = ZIP + FILTER;
    static final String ZIPGROUPFILESET = ZIP + GROUP + FILESET;

    static final String BOOTCLASSPATH = BOOT + CLASSPATH;
    static final String BOOTCLASSPATHREF = BOOT + CLASSPATHREF;

    // silly variant case
    static final String CLASSNAMESTRINGS = "ClassNameStrings";
    static final String REPLACECLASSNAMESTRINGS = REPLACE + CLASSNAMESTRINGS;
    static final String REPLACECONTENT = REPLACE + "Content";
    static final String REPLACENAME = REPLACE + "Name";
    static final String REPLACEPATH = REPLACE + "Path";

    static final int INDENT_SIZE = 4;


}
