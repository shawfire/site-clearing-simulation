package net.shawfire.scs;

/**
 * SquareType enum provides the type that is used to store state of each location on the SiteMap.
 * Whether the square has been CLEARED, is ROCKY, has a removable tree (TREE_REMOVAL),
 * contains a protected tree (PRESERVE_TREE) and or is PLAIN.
 */
public enum SquareType {
    PLAIN("o"), ROCKY("r"), TREE_REMOVAL("t"), PRESERVE_TREE("T"), CLEARED("c");

    private String value;

    public String getValue() {
        return value;
    }

    public boolean equals(String value) {
        return this.value.equals(value);
    }

    SquareType(String value)
    {
        this.value = value;
    }

    public static SquareType fromString(String text) {
        for (SquareType x : SquareType.values()) {
            if (x.equals(text)) {
                return x;
            }
        }
        return null;
    }
}
