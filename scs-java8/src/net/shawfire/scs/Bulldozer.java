package net.shawfire.scs;

public class Bulldozer {
    public static String UnexpectedDirectionMessage = "Unexpected direction: ";
    public static String AttemptToDriveOutOfBoundsMessage = "Attempt to drive bulldozer out of site bearing: ";
    public static String AttemptAccessProtectedSquareMessage = "Attempt to move bulldozer to protected square type: ";

    private Direction direction = Direction.EAST;
    private int x = -1;
    private int y = 0;
    private SiteMap siteMap;
    private Costs costs = new Costs();

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

    public SiteMap getSiteMap() {
        return siteMap;
    }

    Bulldozer(SiteMap siteMap) {
        this.siteMap = siteMap;
    }

    public void move(int n) {
        int count = 0;
        boolean outOfBounds = false;
        while (count++ < n) {
            switch (getDirection()) {
                case EAST:
                    incX(1);
                    if (getX() >= siteMap.getMaxX()) {
                        outOfBounds = true;
                    }
                    break;
                case WEST:
                    incX(-1);
                    if (getX() < 0) {
                        outOfBounds = true;
                    }
                    break;
                case SOUTH:
                    incY(1);
                    if (getY() >= siteMap.getMaxY()) {
                        outOfBounds = true;
                    }
                    break;
                case NORTH:
                    incY(-1);
                    if (getY() < 0) {
                        outOfBounds = true;
                    }
                    break;
                default:
                    throw new IllegalStateException(UnexpectedDirectionMessage + getDirection());
            }
            if (outOfBounds) {
                throw new IndexOutOfBoundsException(AttemptToDriveOutOfBoundsMessage + getDirection());
            }
            // Add cost and update site map
            costs.incFuelCost(SquareType.fromString(getCurrentSquareValue()));

            if (SquareType.PRESERVE_TREE.equals(getCurrentSquareValue())) {
                throw new IllegalArgumentException(AttemptAccessProtectedSquareMessage + SquareType.PRESERVE_TREE.name());
            }

        }
    }

    public String getCurrentSquareValue() {
        if (getX() < 0 || getY() < 0) {
            return null;
        }
        return siteMap.getCurrentSquareValue(getX(), getY());
    }

    public void changeDirection(ChangeInDirection command) {
        setDirection(getDirection().changeDirection(command));
    }
}
