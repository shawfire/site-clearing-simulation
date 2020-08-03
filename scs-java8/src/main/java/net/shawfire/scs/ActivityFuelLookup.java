package net.shawfire.scs;

import java.util.EnumMap;

/**
 * ActivityFuelLookup enum class provides a lookup to determine the corresponding fuel usage
 * based on the SquareType in the SiteMap.
 * For example to clear a `PLAIN` square uses `1` fuel unit whereas
 * to clear a `ROCKY` square uses `2` fuel units and so on.
 */
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
