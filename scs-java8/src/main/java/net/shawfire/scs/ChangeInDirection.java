package net.shawfire.scs;

/**
 * Enum representing a change in direction (`LEFT` or `RIGHT`) for the bulldozer.
 * These value map to the underlying direction commands in the `CommandType` enum.
 */
public enum ChangeInDirection {
    RIGHT("r"), LEFT("l");

    private String value;

    ChangeInDirection(String value)
    {
        this.value = value;
    }
}
