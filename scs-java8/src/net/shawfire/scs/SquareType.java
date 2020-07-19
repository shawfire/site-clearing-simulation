package net.shawfire.scs;

public enum SquareType {
    PLAIN("o"), ROCKY("r"), TREE_REMOVAL("t"), PRESERVE_TREE("T"), CLEARED("c");

    private String value;

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
