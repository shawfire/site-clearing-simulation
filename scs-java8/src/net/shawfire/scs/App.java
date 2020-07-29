package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    private String fileName = null;

    //Enter data using BufferReader
    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static SysOutDelegate sysOutDelegate = (val) -> Utils.println(val);

    public static void main(String[] args) throws Exception {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format(Constants.ExpectedOneArgGotNMsg, args.length));
        }
        App app = new App(args[0]);
        app.run();
    }

    public App(String fileName) {
        setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        Utils.println(Constants.MustPassFileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }

    public void run() throws Exception {
        Utils.println(Constants.AppHeadingLabel);
        Utils.println(String.format(Constants.SiteMapLabel, getFileName()));

        // Read siteMap
        SiteMap siteMap = new SiteMap();
        siteMap.readFromInputStream(getFileName());
        Utils.print(siteMap.toString());

        // Create bulldozer
        Bulldozer bulldozer = new Bulldozer(siteMap);
        Utils.println(Constants.InitialBulldozerPositionMsg);

        // Run simulation
        bulldozer.run();

        System.out.print(Constants.ThankYouMsg + "\n");
    }

}