package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SiteMapTest {

    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        Class classPath = SiteMapTest.class;
        InputStream inputStream = classPath.getResourceAsStream("/test-site-map.txt");
        String[] siteMap = new SiteMap().readFromInputStream(inputStream);

        // Check the all lines in the test file are as expected
        String[] expectedSiteMap = {
                "ootooooooo",
                "oooooooToo",
                "rrrooooToo",
                "rrrroooooo",
                "rrrrrtoooo"
        };
        for (int i = 0; i < expectedSiteMap.length; i++) {
            Assert.assertEquals(String.format("line %1$s: ", i), siteMap[i], expectedSiteMap[i]);
        }
    }

}
