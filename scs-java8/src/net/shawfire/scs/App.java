package net.shawfire.scs;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass site map text file argument";
    public static String FileNameLabel = "Entered site file text file name is: ";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);

    public static void main(String[] args) {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            return;
        }

        App app = new App(args[0]);

        sysOutDelegate.println(app.getString());
    }

    public App(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getString() {
        return FileNameLabel + fileName;
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}