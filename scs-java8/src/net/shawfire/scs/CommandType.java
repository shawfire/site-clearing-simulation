package net.shawfire.scs;

public enum CommandType {
    ADVANCE("a"), TURN_LEFT("l"), TURN_RIGHT("r"), QUIT("q");

    private String value;
    private Integer amount;

    public String getValue() {
        return value;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean equals(String value) {
        return this.value.equals(value);
    }

    CommandType(String value)
    {
        this.value = value;
    }
    CommandType(String value, Integer amount)
    {
        this(value);
        this.amount = new Integer(amount);
    }

    public static CommandType fromString(String text) {
        for (CommandType x : CommandType.values()) {
            if (x.equals(text)) {
                return x;
            }
        }
        throw new IllegalArgumentException(String.format("Expected a command type (not \"%1$s\")"));
    }
}
