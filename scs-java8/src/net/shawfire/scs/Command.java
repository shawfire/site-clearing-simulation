package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

public class Command {

    public static String CommandPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static String ExpectCommandAmountMsg = "Expected an amount for this command";
    public static String ExpectValidCommandAmountMsg = "Expected a valid amount for this command, not: \"%1$s\"";

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static SysInDelegate sysInDelegate = () -> reader.readLine();

    public static CommandPojo getCommand() throws java.io.IOException {
        String input = sysInDelegate.readLine();
        String[] fields = input.trim().split(" +");
        if (fields.length == 0) {
            //TODO get another commandType as this one is empty
            return null;
        }
        CommandType commandType = CommandType.fromString(fields[0]);
        Optional<Integer> amount = Optional.empty();
        if (commandType == CommandType.ADVANCE) {
            if (fields.length < 2) {
                throw new IllegalArgumentException(ExpectCommandAmountMsg);
            }
            try {
                amount = Optional.of(new Integer(fields[1]));
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        String.format(ExpectValidCommandAmountMsg, fields[1]));
            }
        }
        return new CommandPojo(commandType, amount);
    }

    protected static void setSysInDelegate(SysInDelegate val) {
        sysInDelegate = val;
    }

}
