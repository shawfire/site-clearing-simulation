package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import org.mockito.Mockito;

import java.io.PrintStream;


public class AppTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);

    private void assertStdoutContains(String str) {
        Mockito.verify(stdout).println(Mockito.contains(str));
    }

    @Before
    public void injectLastSysOutDelegate() {
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
            assertStdoutContains(App.MustPassFileName);
            Assert.assertEquals(String.format(App.ExpectedOneArgGotNMsg, 2), e.getMessage());
        }
    }

    @Test
    public void givenAValidMapFile_shouldDisplayMapLabel() throws Exception {
        String fileName = "/test-site-map.txt";
        App.main(new String[] { fileName });
        assertStdoutContains(App.AppHeadingLabel);

        String expectedSiteMapDisplay =
                "o o t o o o o o o o\n" +
                "o o o o o o o T o o\n" +
                "r r r o o o o T o o\n" +
                "r r r r o o o o o o\n" +
                "r r r r r t o o o o";
        assertStdoutContains(expectedSiteMapDisplay);
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertStdoutContains(String.format(App.SiteMapLabel, fileName));
    }


}