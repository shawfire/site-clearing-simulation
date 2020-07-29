package net.shawfire.scs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bulldozer {
    public static String UnexpectedDirectionMessage = "Unexpected direction: ";
    public static String AttemptToDriveOutOfBoundsMessage = "Attempt to drive bulldozer out of site bearing: ";
    public static String AttemptAccessProtectedSquareMessage = "Attempt to move bulldozer to protected square type: ";
    public static String CommandPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";

    private Direction direction = Direction.EAST;
    private int x = -1;
    private int y = 0;
    private SiteMap siteMap;
    private Costs costs = new Costs();
    private ArrayList<CommandPojo> commandList = new ArrayList<>();

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void incX(int n) {
        this.x += n;
    }

    public void incY(int n) {
        this.y += n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Costs getCosts() {
        return costs;
    }

    public Integer getCost(ItemType itemType) {
        return getCosts().quantities.get(itemType);
    }

    public SiteMap getSiteMap() {
        return siteMap;
    }

    Bulldozer(SiteMap siteMap) {
        this.siteMap = siteMap;
    }

    private void checkOutOfBounds() {
        if (getX() >= siteMap.getMaxX() || getX() < 0 || getY() >= siteMap.getMaxY() || getY() < 0) {
            throw new IndexOutOfBoundsException(AttemptToDriveOutOfBoundsMessage + getDirection());
        }
    }

    public void move(int n) {
        costs.incCost(ItemType.COMMUNICATION_OVERHEAD);
        int count = 0;
        while (count++ < n) {
            switch (getDirection()) {
                case EAST: incX(1); break;
                case WEST: incX(-1); break;
                case SOUTH: incY(1); break;
                case NORTH: incY(-1); break;
                default:
                    throw new IllegalStateException(UnexpectedDirectionMessage + getDirection());
            }
            checkOutOfBounds();

            if (count < n && SquareType.TREE_REMOVAL == getCurrentSquareValue()) {
                costs.incItemCost(ItemType.PAINT_DAMAGE, getCurrentSquareValue());
            }

            if (SquareType.PRESERVE_TREE == getCurrentSquareValue()) {
                costs.incItemCost(ItemType.DESTRUCTION_PROTECTED_TREE, getCurrentSquareValue());
                throw new IllegalArgumentException(AttemptAccessProtectedSquareMessage + SquareType.PRESERVE_TREE);
            }

            // Add cost and update site map
            costs.incItemCost(ItemType.FUEL_USAGE, getCurrentSquareValue());
            getSiteMap().clearSquare(getX(), getY());
        }
    }

    public SquareType getSquareValue(int x, int y) {
        if (x < 0 || y < 0) {
            return null;
        }
        return siteMap.getSquareValue(x, y);
    }

    public SquareType getCurrentSquareValue() {
        if (getX() < 0 || getY() < 0) {
            return null;
        }
        return siteMap.getSquareValue(getX(), getY());
    }

    public void changeDirection(ChangeInDirection command) {
        costs.incCost(ItemType.COMMUNICATION_OVERHEAD);
        setDirection(getDirection().changeDirection(command));
    }

    // Run simulation
    public void run() throws IOException {
        CommandPojo command;
        do {
            Utils.print(CommandPrompt);
            // Reading data using readLine
            command = Command.getCommand();
            if (command == null) {
                continue;
            }
            commandList.add(command);
        } while (!command.getCommandType().equals("q"));

        printCommandList();
    }

    public void printCommandList() {
        Utils.println("\n" + Constants.CommandsEnteredHeading + "\n");
        StringBuilder sb = new StringBuilder();
        boolean notFirstLine = false;
        for (CommandPojo command : commandList) {
            if (notFirstLine) {
                sb.append(", ");
            } else {
                notFirstLine = true;
            }
            sb.append(command.toString());
        }
        Utils.println(sb.toString());
    }
}
