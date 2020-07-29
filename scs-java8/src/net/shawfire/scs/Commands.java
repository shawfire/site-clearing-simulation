package net.shawfire.scs;

import java.util.ArrayList;

public class Commands {

    public static String CommandPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";

    ArrayList<CommandPojo> commandList = new ArrayList<CommandPojo>();

    public ArrayList<CommandPojo> getCommandList() {
        return commandList;
    }

    public void readCommands() throws Exception {
        CommandPojo input;
        do {
            System.out.print(CommandPrompt);
            // Reading data using readLine
            input = Command.getCommand();
            if (input != null) {
                commandList.add(input);
            }
        } while (input.getCommandType() != CommandType.QUIT);
    }



    public String toString() {
        return "";
    }
}
