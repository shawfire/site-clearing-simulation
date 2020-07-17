package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class AppTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);
//    InputStream stdin = Mockito.mock(InputStreamReader.class);
    BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);

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

    // BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    // Mockito.when(bufferedReader.readLine()).thenReturn("line1", "line2", "line3");

    // when(bufferedReader.readLine()).thenReturn(line).thenReturn(null);
//    @Mock InputStream mockStream;
//    @Mock InputStreamReader mockInputStreamReader;
//    @Mock BufferedReader mockReader;
    @Test
    public void givenQuitFlag_quitApp() throws Exception  {
//        InputStream inputStream = Mockito.mock(InputStream.class);
//        InputStreamReader streamReader = Mockito.mock(InputStreamReader.class);
//        PrintStream stdout = Mockito.mock(PrintStream.class);
        when(bufferedReader.readLine()).thenReturn("q");

        String fileName = "/test-site-map.txt";
        App.main(new String[] { fileName });
        assertStdoutContains(App.AppHeadingLabel);

//        when(mockReader.readLine()).thenReturn("q").thenReturn("q\n");
//        Mockito.when(bufferedReader.readLine()).thenReturn("q", "q\n");

        assertStdoutContains(App.ThankYouMsg);
    }

}