package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class Command {

    public static String UnexpectedCommandReceivedMsg = "Unexpected command received: \"%s\"";

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static SysInDelegate sysInDelegate = () -> reader.readLine();

    public static CommandPojo getCommand() throws java.io.IOException {

        // Get a line with one valid command
        boolean gettingValidCommand = true;
        String input;
        do {
            Utils.print(Constants.CommandPrompt);
            input = sysInDelegate.readLine();
            if (input.matches(CommandType.ValidCommandLineRegex)) {
                gettingValidCommand = false;
            } else {
                Utils.println(String.format(UnexpectedCommandReceivedMsg, input));
            }
        } while (gettingValidCommand);

        // Allow for spaces before, after and embedded
        String[] fields = input.trim().split(" +");

        // Convert the one valid command to a CommandPojo to be returned
        CommandType commandType = CommandType.fromString(fields[0]);
        Optional<Integer> amount = Optional.empty();
        if (commandType == CommandType.ADVANCE) {
            amount = Optional.of(new Integer(fields[1]));
        }
        return new CommandPojo(commandType, amount);
    }

    protected static void setSysInDelegate(SysInDelegate val) {
        sysInDelegate = val;
    }

}
