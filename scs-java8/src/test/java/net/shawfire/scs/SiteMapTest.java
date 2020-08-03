package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class SiteMapTest {

    private SiteMap siteMap;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
        siteMap.readFromInputStream("/test-site-map.txt");
    }

    @Test
    public void testDisplaySiteMap() throws IOException {
        String expectedSiteMapDisplay =
                "o o t o o o o o o o\n" +
                "o o o o o o o T o o\n" +
                "r r r o o o o T o o\n" +
                "r r r r o o o o o o\n" +
                "r r r r r t o o o o\n";
        Assert.assertEquals("Unexpected siteMap: ", siteMap.toString(),expectedSiteMapDisplay);
    }
}
