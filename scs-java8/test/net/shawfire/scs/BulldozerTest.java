package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BulldozerTest {

    private SiteMap siteMap;
    private Bulldozer bulldozer;
    private static Integer INTEGER_ZERO = new Integer(0);
    private static Integer INTEGER_ONE = new Integer(1);

    @Before
    public void setUp() throws Exception {
        siteMap = new SiteMap();
        siteMap.readFromInputStream("/test-site-map.txt");
    }

    public void checkDirectionAndSquareValue(Direction direction, int x, int y, SquareType squareValue) {
        Assert.assertEquals("Value of direction unexpected: ", direction, bulldozer.getDirection());
        Assert.assertEquals("Value of square unexpected: ", squareValue, bulldozer.getSquareValue(x, y));
    }

    public void checkDirectionPosAndSquareValue(Direction direction, int x, int y, SquareType squareValue) {
        Assert.assertEquals("Value of direction unexpected: ", direction, bulldozer.getDirection());
        Assert.assertEquals("Value of x unexpected: ", x, bulldozer.getX());
        Assert.assertEquals("Value of y unexpected: ", y, bulldozer.getY());
        Assert.assertEquals("Value of square unexpected: ", squareValue, bulldozer.getCurrentSquareValue());
    }

    public void checkItemQuantity(ItemType itemType, Integer expectedAmount) {
        Assert.assertEquals(
                String.format("Unexpected Item [%1$s] cost: ", itemType),
                expectedAmount, bulldozer.getQuantity(itemType));
    }

    @Test
    public void testValidMoves() {

        // check initial values
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        checkItemQuantity(ItemType.COMMUNICATION_OVERHEAD, INTEGER_ZERO);
        checkItemQuantity(ItemType.PAINT_DAMAGE, INTEGER_ZERO);
        checkItemQuantity(ItemType.FUEL_USAGE, INTEGER_ZERO);

        // check a valid move by one square
        checkDirectionAndSquareValue(Direction.EAST, 0, 0, SquareType.PLAIN);
        bulldozer.move(1);
        checkItemQuantity(ItemType.COMMUNICATION_OVERHEAD, INTEGER_ONE);
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, SquareType.CLEARED);
        checkItemQuantity(ItemType.FUEL_USAGE, INTEGER_ONE);

        // check a valid move by multiple squares
        checkDirectionAndSquareValue(Direction.EAST, 1, 0, SquareType.PLAIN);
        checkDirectionAndSquareValue(Direction.EAST, 2, 0, SquareType.TREE_REMOVAL);
        bulldozer.move(2);
        checkDirectionAndSquareValue(Direction.EAST, 1, 0, SquareType.CLEARED);
        checkDirectionPosAndSquareValue(Direction.EAST, 2, 0, SquareType.CLEARED);
        checkItemQuantity(ItemType.PAINT_DAMAGE, INTEGER_ZERO);
        checkItemQuantity(ItemType.FUEL_USAGE, new Integer(4));

        // test change in direction to SOUTH
        checkItemQuantity(ItemType.COMMUNICATION_OVERHEAD, new Integer(2));
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkItemQuantity(ItemType.COMMUNICATION_OVERHEAD, new Integer(3));
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 0, SquareType.CLEARED);

        // move south
        checkDirectionAndSquareValue(Direction.SOUTH, 2, 2, SquareType.ROCKY);
        bulldozer.move(2);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 2, 2, SquareType.CLEARED);
        checkItemQuantity(ItemType.FUEL_USAGE, new Integer(7));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testMoveToInvalidSqare() {
        // Move beyond the east border
        bulldozer = new Bulldozer(siteMap);
        checkDirectionPosAndSquareValue(Direction.EAST, -1, 0, null);
        checkItemQuantity(ItemType.PAINT_DAMAGE, INTEGER_ZERO);
        checkDirectionAndSquareValue(Direction.EAST, 7, 0, SquareType.PLAIN);
        bulldozer.move(8);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 0, SquareType.CLEARED);
        checkItemQuantity(ItemType.FUEL_USAGE, new Integer(9));
        checkItemQuantity(ItemType.DESTRUCTION_PROTECTED_TREE, INTEGER_ZERO);
        try {
            bulldozer.move(1);
        } catch (Exception e) {
            checkDirectionPosAndSquareValue(Direction.SOUTH, 7, 1, SquareType.PRESERVE_TREE);
            checkItemQuantity(ItemType.DESTRUCTION_PROTECTED_TREE, new Integer(1));
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
            checkItemQuantity(ItemType.FUEL_USAGE, new Integer(11));
            checkItemQuantity(ItemType.PAINT_DAMAGE, new Integer(1));
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
        checkDirectionPosAndSquareValue(Direction.EAST, 0, 0, SquareType.CLEARED);
        bulldozer.changeDirection(ChangeInDirection.RIGHT);
        checkDirectionPosAndSquareValue(Direction.SOUTH, 0, 0, SquareType.CLEARED);
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