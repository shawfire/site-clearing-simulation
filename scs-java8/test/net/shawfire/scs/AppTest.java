package net.shawfire.scs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.fail;

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
        String fileName = "/test-input-commands.txt";
        stdin = SiteMap.class.getResourceAsStream(fileName);
        System.setIn(stdin);
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
            assertStdoutContains(App.MustPassFileName);
            Assert.assertEquals(String.format(App.ExpectedOneArgGotNMsg, 2), e.getMessage());
        }
    }

    @Test
    public void givenAValidMapFile_and_validInputCommands() throws Exception {
        String fileName = "/test-site-map.txt";
        App.main(new String[] { fileName });

        // Validate welcome message
        assertStdoutContains(App.AppHeadingLabel);

        // Validate siteMap file display
        String expectedSiteMapDisplay =
                "o o t o o o o o o o\n" +
                "o o o o o o o T o o\n" +
                "r r r o o o o T o o\n" +
                "r r r r o o o o o o\n" +
                "r r r r r t o o o o";
        assertStdoutContains(expectedSiteMapDisplay);

        // Validate initial command instructions
        assertStdoutContains(App.InitialBulldozerPositionMsg);

        // validate command prompt
        assertStdoutContains(App.MovementPrompt);

        // Simulation has ended message
        assertStdoutContains(App.CommandsEnteredHeading);

        // Validate list of commands is the same as provide from test file (substituting stdin)

        // Validate cost heading

        // Validate cost summary

        // Validate closing message
        assertStdoutContains(App.ThankYouMsg);
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void givenInvalidSiteMapFile_shouldReportError() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertStdoutContains(String.format(App.SiteMapLabel, fileName));
    }

}