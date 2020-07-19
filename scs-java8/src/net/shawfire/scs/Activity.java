package net.shawfire.scs;

import java.util.EnumMap;

public class Activity {
    public static EnumMap<SquareType,Integer> costs = new EnumMap<>(SquareType.class);
    static {
        // Assign fuel usage to SquareType
        // By default the activity cost is one
        for (SquareType squareType : SquareType.values()) {
            costs.put(squareType, 1);
        }
        // Assign activity types that are not one.
        costs.put(SquareType.TREE_REMOVAL, 2);
        costs.put(SquareType.ROCKY, 2);
    }
}
