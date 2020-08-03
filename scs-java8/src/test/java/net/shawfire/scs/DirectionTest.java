package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {

    @Test
    public void getValue() {
        Direction direction = Direction.EAST;
        Assert.assertEquals(Direction.EAST, direction);
    }

    @Test
    public void changeDirection() {
        // initial
        Direction direction = Direction.EAST;
        Assert.assertEquals(Direction.EAST, direction);

        // move right one quadrant
        direction = direction.changeDirection(ChangeInDirection.RIGHT);
        Assert.assertEquals(Direction.SOUTH, direction);

        // move left one quadrant
        direction = direction.changeDirection(ChangeInDirection.LEFT);
        Assert.assertEquals(Direction.EAST, direction);

        // move right full circle
        direction = direction.changeDirection(ChangeInDirection.RIGHT);
        Assert.assertEquals(Direction.SOUTH, direction);
        direction = direction.changeDirection(ChangeInDirection.RIGHT);
        Assert.assertEquals(Direction.WEST, direction);
        direction = direction.changeDirection(ChangeInDirection.RIGHT);
        Assert.assertEquals(Direction.NORTH, direction);
        direction = direction.changeDirection(ChangeInDirection.RIGHT);
        Assert.assertEquals(Direction.EAST, direction);

        // move left full circle
        direction = direction.changeDirection(ChangeInDirection.LEFT);
        Assert.assertEquals(Direction.NORTH, direction);
        direction = direction.changeDirection(ChangeInDirection.LEFT);
        Assert.assertEquals(Direction.WEST, direction);
        direction = direction.changeDirection(ChangeInDirection.LEFT);
        Assert.assertEquals(Direction.SOUTH, direction);
        direction = direction.changeDirection(ChangeInDirection.LEFT);
        Assert.assertEquals(Direction.EAST, direction);
    }
}