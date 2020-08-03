package net.shawfire.scs;

import java.util.Optional;

/**
 * As the `Advance` command also has a corresponding amount the `CommandPojo` is used
 * which supports an `Optional` `amount`.
 * This class enables a Command to be compared, displayed or stored in a Map lookup.
 */
public class CommandPojo {
    private CommandType commandType;
    private Optional<Integer> amount = Optional.empty();

    public CommandPojo(CommandType commandType) {
        this.commandType = commandType;
    }
    public CommandPojo(CommandType commandType, Optional<Integer> amount) {
        this(commandType);
        this.amount = amount;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Optional<Integer> getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return commandType + (amount.isPresent() ? " " + amount.get() : "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandType) {
            return this.getCommandType().equals(obj);
        }
        if (obj instanceof CommandPojo) {
            CommandPojo commandPojo = (CommandPojo)obj;
            return this.getCommandType().equals(commandPojo.getCommandType()) &&
                    this.getAmount().equals(commandPojo.getAmount());
        }
        return super.equals(obj);
    }
}
