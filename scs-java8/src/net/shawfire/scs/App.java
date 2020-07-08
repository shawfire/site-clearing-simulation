package net.shawfire.scs;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass only site map text file argument";
    public static String AppHeadingLabel = "\nWelcome to the site clearing simulator.\n";
    public static String SiteMapLabel = "\nThis is a map of the site (read from file: %1$s):\n";
    public static String ExpectedOneArgGotNMsg = "Expected 1 argument but received: %1d";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);

    public static void main(String[] args) {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format(ExpectedOneArgGotNMsg, args.length));
        }

        App app = new App(args[0]);

        sysOutDelegate.println(app.AppHeadingLabel);
        sysOutDelegate.println(app.getSiteMapHeading());
    }

    public App(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getSiteMapHeading() {
        return String.format(SiteMapLabel, fileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}