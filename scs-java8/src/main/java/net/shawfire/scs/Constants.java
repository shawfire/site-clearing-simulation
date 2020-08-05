package net.shawfire.scs;

/**
 * Application constants: Messages, Prompts and Labels.
 */
public class Constants {
    public static final String MustPassFileName = "Must pass only site map text file argument";
    public static final String AppHeadingLabel = "\nWelcome to the site clearing simulator.\n";
    public static final String SiteMapLabel = "This is a map of the site (read from file: %s):\n";
    public static final String ExpectedOneArgGotNMsg = "Expected 1 argument but received: %1d";
    public static final String InitialBulldozerPositionMsg = "The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.\n";
    public static final String MovementPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static final String CommandsEnteredHeading = "These are the commands you issued:";
    public static final String SimulationEndedAtYourRequestMsg = "\nThe simulation has ended at your request. ";
    public static final String CostsHeading = "The costs for this land clearing operation were:";
    public static final String CostColumnHeading = "Item";
    public static final String TotalSeparator = "----";
    public static final String TotalLabel = "Total";
    public static final String ThankYouMsg = "\nThankyou for using the Aconex site clearing simulator.\n";
    public static final String CommandPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
}
