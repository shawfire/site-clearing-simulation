package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This is the main App for the site clearing simulation. This App expected a
 * the site map text file as the one parameter. The site map text file is found
 * by looking in the classpath. For the purpose of testing this class the a site
 * map text file (`/test-site-map.txt`) is provided in the `resources` directory
 * which is in the classpath.
 *
 * After validating the arguments; an instance of the SiteMap is created. The
 * SiteMap reads the site map text file provided as the first parameter. An
 * instance of the Bulldozer class is then instantiated with the siteMap created
 * above. The bulldozer accepts commands form the input stream, and executes
 * each command as it is received. Once the simulation is complete the bulldozer
 * displays all the costs it incurred during the simulation.
 */
public class App {
    private String fileName = null;

    // Enter data using BufferReader
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static SysOutDelegate sysOutDelegate = (val) -> Utils.println(val);

    public static void main(String[] args) throws Exception {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(String.format(Constants.ExpectedOneArgGotNMsg, args.length));
        }
        App app = new App(args[0]);
        app.run();
    }

    public App(String fileName) {
        setFileName(fileName);
    }

    private String getFileName() {
        return fileName;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        Utils.println(Constants.MustPassFileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }

    private void run() throws Exception {
        Utils.println(Constants.AppHeadingLabel);
        Utils.println(String.format(Constants.SiteMapLabel, getFileName()));

        // Read siteMap
        SiteMap siteMap = new SiteMap();
        siteMap.readFromInputStream(getFileName());
        Utils.println(siteMap.toString());

        // Create bulldozer
        Bulldozer bulldozer = new Bulldozer(siteMap);
        Utils.println(Constants.InitialBulldozerPositionMsg);

        // Run simulation
        bulldozer.run();

        Utils.println(Constants.ThankYouMsg);
    }

}