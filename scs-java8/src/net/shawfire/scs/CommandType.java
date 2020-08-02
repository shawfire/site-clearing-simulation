package net.shawfire.scs;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CommandType {
    ADVANCE("a"), TURN_LEFT("l"), TURN_RIGHT("r"), QUIT("q");

    public static String ValidCommandLineRegex = "^[ ]*(a[ ]+[0-9]+|[lrq])[ ]*$";

    private String value;
    private Integer amount = null;
    private static Map<CommandType, String> descriptionMap = Stream.of(
            new AbstractMap.SimpleImmutableEntry<>(CommandType.ADVANCE, "advance"),
            new AbstractMap.SimpleImmutableEntry<>(CommandType.TURN_LEFT, "turn left"),
            new AbstractMap.SimpleImmutableEntry<>(CommandType.TURN_RIGHT, "turn right"),
            new AbstractMap.SimpleImmutableEntry<>(CommandType.QUIT, "quit"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public String toString() {
        return descriptionMap.get(this);
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

    public static CommandType fromString(String text) {
        for (CommandType x : CommandType.values()) {
            if (x.equals(text)) {
                return x;
            }
        }
        throw new IllegalArgumentException(String.format("Expected a command type (not \"%1$s\")"));
    }
}
