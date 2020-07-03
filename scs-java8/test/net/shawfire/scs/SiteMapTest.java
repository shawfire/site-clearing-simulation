package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;

public class SiteMapTest {

    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        Class classPath = SiteMapTest.class;
        InputStream inputStream = classPath.getResourceAsStream("/test-site-map.txt");
        ArrayList<String> siteMap = new SiteMap().readFromInputStream(inputStream);

        // Check the first line of the test file is as expected
        Assert.assertEquals(siteMap.get(0), "ootooooooo");
    }

}
