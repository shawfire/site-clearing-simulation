package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import java.io.*;


public class AppTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);
    InputStream stdin;

    private void assertStdoutContains(String str) {
        Mockito.verify(stdout, Mockito.atLeastOnce()).print(Mockito.contains(str));
    }

    @Before
    public void beforeEachTest() {
        System.setOut(stdout);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void givenTwoInputParameter_shouldThrowException() throws Exception {
        App.main(new String[] { "a", "b" });
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() throws Exception {

        try {
            App.main(new String[] { "a", "b" });

        } catch (java.lang.IllegalArgumentException e) {
            assertStdoutContains(Constants.MustPassFileName);
            Assert.assertEquals(String.format(Constants.ExpectedOneArgGotNMsg, 2), e.getMessage());
        }
    }

    @Test
    public void givenAValidMapFile_and_validInputCommands() throws Exception {
        String fileName = "/test-input-commands.txt";
        stdin = SiteMap.class.getResourceAsStream(fileName);
        System.setIn(stdin);

        fileName = "/test-site-map.txt";
        App.main(new String[] { fileName });

        // Validate welcome message
        assertStdoutContains(Constants.AppHeadingLabel);

        // Validate siteMap file display
        String expectedSiteMapDisplay =
                "o o t o o o o o o o\n" +
                "o o o o o o o T o o\n" +
                "r r r o o o o T o o\n" +
                "r r r r o o o o o o\n" +
                "r r r r r t o o o o";
        assertStdoutContains(expectedSiteMapDisplay);

        // Validate initial command instructions
        assertStdoutContains(Constants.InitialBulldozerPositionMsg);

        // validate command prompt
        assertStdoutContains(Constants.MovementPrompt);

        // Simulation has ended message
        assertStdoutContains(Constants.SimulationEndedAtYourRequestMsg);
        assertStdoutContains(Constants.CommandsEnteredHeading);

        // Validate list of commands is the same as provide from test file (substituting stdin)
        assertStdoutContains("advance 4, turn right, advance 4, turn left, advance 2, advance 4, turn left, quit");

        // Validate cost heading
        assertStdoutContains(Constants.CostsHeading);

        // Validate cost summary
        assertStdoutContains(Constants.CostColumnHeading);

        assertStdoutContains("Item                           Quantity      Cost");
        assertStdoutContains("communication overhead                7         7");
        assertStdoutContains("fuel usage                           19        19");
        assertStdoutContains("uncleared squares                    34       102");
        assertStdoutContains("destruction of protected tree         0         0");
        assertStdoutContains("paint damage to bulldozer             1         2");
        assertStdoutContains("----");
        assertStdoutContains("Total                                         130");

        // Validate closing message
        assertStdoutContains(Constants.ThankYouMsg);
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void givenInvalidSiteMapFile_shouldReportError() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertStdoutContains(String.format(Constants.SiteMapLabel, fileName));
    }

}