package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.mockito.Mockito.when;

public class CommandTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);

    @Before
    public void beforeEachTest() {
        System.setOut(stdout);
    }

    @Test
    public void testGetCommand() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("a 4").thenReturn("l").thenReturn("r").thenReturn("q");
        Command.setSysInDelegate(() -> reader.readLine());
        Command command = new Command();
        CommandPojo aCommand;
        Assert.assertEquals((aCommand = command.getCommand()).getCommandType(), CommandType.ADVANCE);
        Assert.assertEquals(aCommand.getAmount().get(), new Integer(4));
        Assert.assertEquals(command.getCommand().getCommandType(), CommandType.TURN_LEFT);
        Assert.assertEquals(command.getCommand().getCommandType(), CommandType.TURN_RIGHT);
        Assert.assertEquals(command.getCommand(), CommandType.QUIT);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testGetInvalidCommand() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("x");
        Command.setSysInDelegate(() -> reader.readLine());
        Command command = new Command();
        command.getCommand();
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testGetCommandWithNoAmount() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("a");
        Command.setSysInDelegate(() -> reader.readLine());
        Command command = new Command();
        CommandPojo aCommand;
        Assert.assertEquals((aCommand = command.getCommand()), CommandType.ADVANCE);
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void testGetCommandWithInvalidAmount() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("a five");
        Command.setSysInDelegate(() -> reader.readLine());
        Command command = new Command();
        CommandPojo aCommand;
        Assert.assertEquals((aCommand = command.getCommand()), CommandType.ADVANCE);
    }

    //TODO fix this or remove
    @Test
    public void testReadInstructions() throws Exception {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        when(reader.readLine()).thenReturn("q");
        Command.setSysInDelegate(() -> reader.readLine());
        Commands commands = new Commands();
        commands.readCommands();
//        Mockito.verify(stdout).println(Mockito.contains(CommandType.QUIT.toString()));
    }
}