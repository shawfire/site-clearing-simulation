package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.fail;

public class SiteMapTest {

    private SiteMap siteMap;
    private String[] siteMapArray;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
        siteMapArray = siteMap.readFromInputStream("/test-site-map.txt");
    }

    @Test
    public void givenFileName_whenUsingClasspath_thenFileData() throws IOException {
        // Check the all lines in the test file are as expected
        String[] expectedSiteMap = {
                "ootooooooo",
                "oooooooToo",
                "rrrooooToo",
                "rrrroooooo",
                "rrrrrtoooo"
        };
        for (int i = 0; i < expectedSiteMap.length; i++) {
            Assert.assertEquals(String.format("line %1$s: ", i), siteMapArray[i], expectedSiteMap[i]);
        }
    }

    @Test
    public void testDisplaySiteMap() throws IOException {
        String expectedSiteMapDisplay =
                "o o t o o o o o o o\n" +
                "o o o o o o o T o o\n" +
                "r r r o o o o T o o\n" +
                "r r r r o o o o o o\n" +
                "r r r r r t o o o o";
        Assert.assertEquals(siteMap.toString(),expectedSiteMapDisplay);
    }

    public void checkDirectionPosAndSquareValue(Direction direction, int x, int y, String squareValue) {
        Assert.assertEquals("Value of direction unexpected: ", direction, siteMap.getDirection());
        Assert.assertEquals("Value of x unexpected: ", x, siteMap.getX());
        Assert.assertEquals("Value of y unexpected: ", y, siteMap.getY());
        Assert.assertEquals("Value of square unexpected: ", squareValue, siteMap.getCurrentSquareValue());

    }

    @Test
    public void testValidMoves() {
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        siteMap.move(1);
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, "o");
        siteMap.move(2);
        checkDirectionPosAndSquareValue(Direction.EAST, 2, 0, "t");
    }

    @Test
    public void testMoveToInvalidSqare() {
    }
}
