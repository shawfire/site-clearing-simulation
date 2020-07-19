package net.shawfire.scs;

public enum SquareType {
    PLAIN("o"), ROCKY("r"), TREE_REMOVAL("t"), PRESERVE_TREE("T");

    private String value;

    public boolean equals(String value) {
        return this.value.equals(value);
    }

    SquareType(String value)
    {
        this.value = value;
    }
}
