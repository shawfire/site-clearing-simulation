package net.shawfire.scs;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CommandType is an enum class for Command Types.
 * The essential first character of each command is associated with it's description.
 * This class supports 1) the display of the command's description,
 * 2) Parsing a command to it's equivalent one letter unique prefix,
 * 3) Provides a regular expression that validate if a line contains only one valid command.
 */
public enum CommandType {
    ADVANCE("a"), TURN_LEFT("l"), TURN_RIGHT("r"), QUIT("q");

    // Only mandate the first character of the command to match
    public static String ValidCommandLineRegex = "^[ ]*([aA][a-z]*[ ]+[0-9]+|[lLrRqQ][a-z]*)[ ]*$";

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

    public boolean equals(String value) {
        return this.value.equals(value);
    }

    CommandType(String value)
    {
        this.value = value;
    }

    public static CommandType fromString(String text) {
        for (CommandType x : CommandType.values()) {
            // Compare only the first character of the command ignoring case
            if (x.equals(text.substring(0,1).toLowerCase())) {
                return x;
            }
        }
        throw new IllegalArgumentException(String.format("Expected a command type (not \"%1$s\")"));
    }
}
