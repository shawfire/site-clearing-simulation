package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulldozerTest {

    private SiteMap siteMap;
    private Bulldozer bulldozer;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
        siteMap.readFromInputStream("/test-site-map.txt");
    }

    public void checkDirectionPosAndSquareValue(Direction direction, int x, int y, String squareValue) {
        Assert.assertEquals("Value of direction unexpected: ", direction, bulldozer.getDirection());
        Assert.assertEquals("Value of x unexpected: ", x, bulldozer.getX());
        Assert.assertEquals("Value of y unexpected: ", y, bulldozer.getY());
        Assert.assertEquals("Value of square unexpected: ", squareValue, bulldozer.getCurrentSquareValue());
    }

    @Test
    public void testValidMoves() {

        // check initial values
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);

        // check a valid move by one square
        bulldozer.move(1);
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, "o");
        Assert.assertEquals(new Integer(1), bulldozer.getCosts().quantities.get(ItemType.FUEL_USAGE));

        // check a valid move by multiple squares
        bulldozer.move(2);
        checkDirectionPosAndSquareValue(Direction.EAST, 2, 0, "t");
        Assert.assertEquals(new Integer(4), bulldozer.getCosts().quantities.get(ItemType.FUEL_USAGE));

        // test change in direction to SOUTH
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 0, "t");

        // move south
        bulldozer.move(2);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 2, "r");
        Assert.assertEquals(new Integer(7), bulldozer.getCosts().quantities.get(ItemType.FUEL_USAGE));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testMoveToInvalidSqare() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        bulldozer.move(8);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 0, "o");
        Assert.assertEquals(new Integer(9), bulldozer.getCosts().quantities.get(ItemType.FUEL_USAGE));
        bulldozer.move(1);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 1, "T");
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingEast() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        bulldozer.move(11);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingSouth() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, -1, 0, null);
        bulldozer.move(5);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingWest() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.WEST, -1, 0, null);
        bulldozer.move(1);
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingNorth() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        bulldozer.changeDirection(ChangeInDirection.LEFT);
        checkDirectionPosAndSquareValue(Direction.NORTH, -1, 0, null);
        bulldozer.move(1);
    }
}