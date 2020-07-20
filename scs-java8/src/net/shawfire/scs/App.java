package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass only site map text file argument";
    public static String AppHeadingLabel = "\nWelcome to the site clearing simulator.";
    public static String SiteMapLabel = "\nThis is a map of the site (read from file: %1$s):\n";
    public static String ExpectedOneArgGotNMsg = "Expected 1 argument but received: %1d";
    public static String InitialBulldozerPositionMsg = "The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.";
    public static String MovementPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static String CommandsEnteredHeading = "The simulation has ended at your request. These are the commands you issued:";
    public static String CostsHeading = "The costs for this land clearing operation were:";
    public static String ItemColumnHeading = "Item";
    public static String QuantityColumnHeading = "Item";
    public static String CostColumnHeading = "Item";
    public static String CommunicationItemLabel = "communication overhead";
    public static String FuelItemLabel = "fuel usage";
    public static String UnclearedSquaresItemLabel = "uncleared squares";
    public static String DestructionItemLabel = "destruction of protected tree";
    public static String PaintDamageItemLabel = "paint damage to bulldozer";
    public static String TotalSeparator = "----";
    public static String TotalLabel = "Total";
    public static String ThankYouMsg = "\nThankyou for using the Aconex site clearing simulator.\n";

    //Enter data using BufferReader
    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static void print(String val) {
        System.out.print(val + "\n");
    }
    private static void println(String val) {
        print(val + "\n");
    }

    private static SysOutDelegate sysOutDelegate = (val) -> println(val);

    public static void main(String[] args) throws Exception {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format(ExpectedOneArgGotNMsg, args.length));
        }
        App app = new App(args[0]);

        System.out.print(app.AppHeadingLabel + "\n");
        println(app.getSiteMapHeading());

        // Read siteMap
        SiteMap siteMap = new SiteMap();
        siteMap.readFromInputStream(app.getFileName());
        println(siteMap.toString());

        // Create bulldozer
        Bulldozer bulldozer = new Bulldozer(siteMap);
        println(app.InitialBulldozerPositionMsg);

        // Read commands
        ArrayList<String> commandList = new ArrayList<>();
        String input;
        do {
            System.out.print(app.MovementPrompt);
            // Reading data using readLine
            input = reader.readLine();
            commandList.add(input);
            println(input);
        } while (!input.equals("q"));

        println(app.CommandsEnteredHeading);

        String[] commandArray = commandList.stream().toArray(String[]::new);
        for (int i = 0; i < commandArray.length; i++) {
            println(commandArray[i]);
            String[] commands = commandArray[i].trim().split(" +");
            for (String command : commands) {
                System.out.println(String.format("match: %1$s", command));
                if (command.matches("^[alrq]?$")) {
                    System.out.println(String.format("string command: %1$s", command));
                } else if (command.matches("^[0-9]+$")) {
                    System.out.println(String.format("integer command: %1$s", command));
                } else {
                    System.out.println(String.format("invalid command: %1$s", command));
                }
            }
        }

        System.out.print(app.ThankYouMsg + "\n");
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
        println(MustPassFileName);
    }

    public String getSiteMapHeading() {
        return String.format(SiteMapLabel, fileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}