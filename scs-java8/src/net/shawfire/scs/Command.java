package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Command {

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    private static SysInDelegate sysInDelegate = () -> reader.readLine();

    public CommandType getCommand() throws java.io.IOException {
        String input = sysInDelegate.readLine();
        String[] fields = input.trim().split(" +");
        if (fields.length == 0) {
            //TODO get another command as this one is empty
            return null;
        }
        CommandType command = CommandType.fromString(fields[0]);
        if (command == CommandType.ADVANCE) {
            if (fields.length < 2) {
                throw new IllegalArgumentException("Expected an amount for this command");
            }
            try {
                command.setAmount(Integer.valueOf(fields[1]));
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        String.format("Expected a valid amount for this command, not: \"%1$s\"", fields[1]));
            }
        }
        return command;
    }

    protected static void setSysInDelegate(SysInDelegate val) {
        sysInDelegate = val;
    }
}
