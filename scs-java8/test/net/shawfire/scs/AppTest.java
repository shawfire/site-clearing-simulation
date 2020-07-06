package net.shawfire.scs;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AppTest {

    StringBuffer outputMessage = new StringBuffer();

    private void assertOutputContainsString(String str) {
        MatcherAssert.assertThat(outputMessage.toString(), CoreMatchers.containsString(str));
    }

    @Before
    public void injectLastSysOutDelegate() {
        App.setSysOutDelegate((val) -> outputMessage.append(val).toString());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void givenTwoInputParameter_shouldThrowException() throws Exception {
        App.main(new String[] { "a", "b" });
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() {

        try {
            App.main(new String[] { "a", "b" });

        } catch (java.lang.IllegalArgumentException e) {
            assertOutputContainsString(App.MustPassFileName);
        }
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertOutputContainsString(String.format(App.SiteMapLabel, fileName));
    }

    @Test
    public void givenAValidMapFile_shouldDisplayMapLabel() throws Exception {
        String fileName = "test-site-map.txt";
        App.main(new String[] { fileName });
        assertOutputContainsString(App.AppHeadingLabel);
    }

}