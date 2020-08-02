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
    Command command = new Command();
    CommandPojo aCommand;
    BufferedReader reader = Mockito.mock(BufferedReader.class);

    private void assertStdoutContains(String str) {
        Mockito.verify(stdout, Mockito.atLeastOnce()).print(Mockito.contains(str));
    }

    @Before
    public void beforeEachTest() {
        System.setOut(stdout);
        Command.setSysInDelegate(() -> reader.readLine());
    }

    @Test
    public void testGetCommand() throws Exception {
        when(reader.readLine()).thenReturn("a 4").thenReturn("l").thenReturn("r").thenReturn("q");
        Command.setSysInDelegate(() -> reader.readLine());
        Assert.assertEquals((aCommand = command.getCommand()).getCommandType(), CommandType.ADVANCE);
        Assert.assertEquals(aCommand.getAmount().get(), new Integer(4));
        Assert.assertEquals(command.getCommand().getCommandType(), CommandType.TURN_LEFT);
        Assert.assertEquals(command.getCommand(), CommandType.TURN_RIGHT);
        Assert.assertEquals(command.getCommand(), CommandType.QUIT);
    }

    @Test
    public void testGetInvalidCommand() throws Exception {
        when(reader.readLine()).thenReturn("x").thenReturn("").thenReturn("a 5a")
                .thenReturn("Z").thenReturn("q 6").thenReturn("q");
        Assert.assertEquals((aCommand = command.getCommand()), CommandType.QUIT);
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "x"));
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, ""));
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "a 5a"));
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "Z"));
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "q 6"));
    }

    @Test
    public void testGetCommandWithNoAmount() throws Exception {
        when(reader.readLine()).thenReturn("a").thenReturn("q");
        Command.setSysInDelegate(() -> reader.readLine());
        Assert.assertEquals((aCommand = command.getCommand()), CommandType.QUIT);
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "a"));
    }

    @Test
    public void testGetCommandWithInvalidAmount() throws Exception {
        when(reader.readLine()).thenReturn("a five").thenReturn("q");
        Command.setSysInDelegate(() -> reader.readLine());
        Assert.assertEquals((aCommand = command.getCommand()), CommandType.QUIT);
        assertStdoutContains(String.format(Command.UnexpectedCommandReceivedMsg, "a five"));
    }

}