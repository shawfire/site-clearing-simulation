package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static net.shawfire.scs.Bulldozer.AttemptToDriveOutOfBoundsMessage;
import static org.junit.Assert.*;

public class BulldozerTest {

    private SiteMap siteMap;
    private Bulldozer bulldozer;

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
        siteMap.readFromInputStream("/test-site-map.txt");
    }

    public void checkDirectionPosAndSquareValue(Direction direction, int x, int y, SquareType squareValue) {
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
        Assert.assertEquals(new Integer(0), bulldozer.getCost(ItemType.PAINT_DAMAGE));
        Assert.assertEquals(new Integer(0), bulldozer.getCost(ItemType.FUEL_USAGE));

        // check a valid move by one square
        bulldozer.move(1);
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, SquareType.PLAIN);
        Assert.assertEquals(new Integer(1), bulldozer.getCost(ItemType.FUEL_USAGE));

        // check a valid move by multiple squares
        bulldozer.move(2);
        checkDirectionPosAndSquareValue(Direction.EAST, 2, 0, SquareType.TREE_REMOVAL);
        Assert.assertEquals(new Integer(0), bulldozer.getCost(ItemType.PAINT_DAMAGE));
        Assert.assertEquals(new Integer(4), bulldozer.getCost(ItemType.FUEL_USAGE));

        // test change in direction to SOUTH
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 0, SquareType.TREE_REMOVAL);

        // move south
        bulldozer.move(2);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 2, SquareType.ROCKY);
        Assert.assertEquals(new Integer(7), bulldozer.getCost(ItemType.FUEL_USAGE));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testMoveToInvalidSqare() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        Assert.assertEquals(new Integer(0), bulldozer.getCost(ItemType.PAINT_DAMAGE));
        bulldozer.move(8);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 0, SquareType.PLAIN);
        Assert.assertEquals(new Integer(9), bulldozer.getCost(ItemType.FUEL_USAGE));
        Assert.assertEquals(new Integer(0),
                bulldozer.getCost(ItemType.DESTRUCTION_PROTECTED_TREE));
        try {
            bulldozer.move(1);
        } catch (Exception e) {
            checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 1, SquareType.PRESERVE_TREE);
            Assert.assertEquals(new Integer(10),
                    bulldozer.getCost(ItemType.DESTRUCTION_PROTECTED_TREE));
            Assert.assertEquals(
                    Bulldozer.AttemptAccessProtectedSquareMessage + SquareType.PRESERVE_TREE,
                    e.getMessage());
            throw e;
        }
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingEast() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        try {
            bulldozer.move(11);
        } catch (Exception e) {
            Assert.assertEquals(new Integer(11), bulldozer.getCost(ItemType.FUEL_USAGE));
            Assert.assertEquals(new Integer(2), bulldozer.getCost(ItemType.PAINT_DAMAGE));
            Assert.assertEquals(
                    Bulldozer.AttemptToDriveOutOfBoundsMessage + Direction.EAST,
                    e.getMessage());
            throw e;
        }
    }

    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testMoveBeyondSiteMapBearingSouth() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        bulldozer.move(1);
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, SquareType.PLAIN);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 0, 0, SquareType.PLAIN);
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