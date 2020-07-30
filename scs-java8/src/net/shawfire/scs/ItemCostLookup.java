package net.shawfire.scs;

import java.util.EnumMap;

public class ItemCostLookup {
    public static EnumMap<ItemType,Integer> costs = new EnumMap<>(ItemType.class);
    static {
        // Assign costs to Item Types
        costs.put(ItemType.COMMUNICATION_OVERHEAD, 1);
        costs.put(ItemType.FUEL_USAGE, 1);
        costs.put(ItemType.DESTRUCTION_PROTECTED_TREE, 10);
        costs.put(ItemType.PAINT_DAMAGE, 2);
        costs.put(ItemType.UNCLEARED_SQUARE, 3);
    }
}
