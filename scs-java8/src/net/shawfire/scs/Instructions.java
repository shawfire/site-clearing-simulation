package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Instructions {

    public static String MovementPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static Instruction instruction = new Instruction();

    public void readInstructions() throws Exception {
        String input;
        do {
            System.out.print(MovementPrompt);
            // Reading data using readLine
            input = instruction.getIntruction();
            System.out.println(input);
        } while (!input.equals("q"));
    }
}
