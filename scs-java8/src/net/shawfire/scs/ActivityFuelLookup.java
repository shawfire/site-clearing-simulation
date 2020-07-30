package net.shawfire.scs;

import java.util.EnumMap;

public class ActivityFuelLookup {
    public static EnumMap<SquareType,Integer> costs = new EnumMap<>(SquareType.class);
    static {
        // Assign fuel usage to SquareType
        costs.put(SquareType.PLAIN, 1);
        costs.put(SquareType.CLEARED, 1);
        costs.put(SquareType.ROCKY, 2);
        costs.put(SquareType.TREE_REMOVAL, 2);
        costs.put(SquareType.PRESERVE_TREE, 2);
    }
}
