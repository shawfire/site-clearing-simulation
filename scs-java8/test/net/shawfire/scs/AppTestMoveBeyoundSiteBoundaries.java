package net.shawfire.scs;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.PrintStream;


public class AppTestMoveBeyoundSiteBoundaries {

    PrintStream stdout = Mockito.mock(PrintStream.class);
    InputStream stdin;

    private void assertStdoutContains(String str) {
        Mockito.verify(stdout, Mockito.atLeastOnce()).print(Mockito.contains(str));
    }

    @Before
    public void beforeEachTest() {
        System.setOut(stdout);
    }

    @Ignore("This test works in isolation")
    @Test
    public void testRemoveProtectedTree() throws Exception {
        String fileName = "/test-input-commands-mbsb.txt";
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

        assertStdoutContains("Simulation ended prematurely. Reason - Attempt to drive bulldozer out of site bearing: EAST");

        // Simulation has ended message
        assertStdoutContains(Constants.CommandsEnteredHeading);

        // Validate list of commands is the same as provide from test file (substituting stdin)
        assertStdoutContains("advance 11");

        // Validate cost heading
        assertStdoutContains(Constants.CostsHeading);

        // Validate cost summary
        assertStdoutContains(Constants.CostColumnHeading);

        assertStdoutContains("Item                           Quantity      Cost");
        assertStdoutContains("communication overhead                1         1");
        assertStdoutContains("fuel usage                           11        11");
        assertStdoutContains("uncleared squares                    38       114");
        assertStdoutContains("destruction of protected tree         0         0");
        assertStdoutContains("paint damage to bulldozer             1         2");

        // Validate closing message
        assertStdoutContains(Constants.ThankYouMsg);
    }

}