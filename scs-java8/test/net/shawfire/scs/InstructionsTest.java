package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class InstructionsTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);

    @Test
    public void testGetInstruction() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("q");
        Instruction.setSysInDelegate(() -> reader.readLine());
        Instruction instruction = new Instruction();
        Assert.assertEquals(instruction.getIntruction(), "q");
    }

    @Test
    public void testReadInstructions() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("q");
        Instruction.setSysInDelegate(() -> reader.readLine());
        Instructions instructions = new Instructions();
        instructions.readInstructions();
        Mockito.verify(stdout).println(Mockito.contains("q"));
    }
}