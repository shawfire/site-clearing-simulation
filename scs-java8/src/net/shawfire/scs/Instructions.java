package net.shawfire.scs;

public class Instructions {

    public static String MovementPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static Command command = new Command();

    public void readInstructions() throws Exception {
        CommandType input;
        do {
            System.out.print(MovementPrompt);
            // Reading data using readLine
            input = command.getCommand();
            System.out.println(input);
        } while (input != CommandType.QUIT);
    }
}
