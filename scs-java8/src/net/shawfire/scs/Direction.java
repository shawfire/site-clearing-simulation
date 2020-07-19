package net.shawfire.scs;

public enum Direction {
    EAST, SOUTH, WEST, NORTH;

    public Direction changeDirection(ChangeInDirection command) {
        int n = Direction.values().length;
        int incrementBy = command == ChangeInDirection.RIGHT ? 1 : -1;
        return Direction.values()[(this.ordinal() + n + incrementBy) % n];
    }

}
