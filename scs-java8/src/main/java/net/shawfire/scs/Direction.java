package net.shawfire.scs;

/**
 * Direction enum provides a mechanism to change the direction left or right
 * the four different direction orientations namely: `EAST`, `SOUTH`, `WEST`, and `NORTH`
 */
public enum Direction {
    EAST, SOUTH, WEST, NORTH;

    public Direction changeDirection(ChangeInDirection command) {
        int n = Direction.values().length;
        int incrementBy = command == ChangeInDirection.RIGHT ? 1 : -1;
        return Direction.values()[(this.ordinal() + n + incrementBy) % n];
    }

}
