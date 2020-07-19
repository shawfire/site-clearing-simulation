package net.shawfire.scs;

import java.util.EnumMap;

public enum ItemType {
    COMMUNICATION_OVERHEAD, FUEL_USAGE, UNCLEARED_SQUARE, DESTRUCTION_PROTECTED_TREE, PAINT_DAMAGE;

    public static EnumMap<ItemType, String> labels = new EnumMap<>(ItemType.class);
    static {
        labels.put(COMMUNICATION_OVERHEAD, "communication overhead");
        labels.put(FUEL_USAGE, "fuel usage");
        labels.put(UNCLEARED_SQUARE, "uncleared squares");
        labels.put(DESTRUCTION_PROTECTED_TREE, "destruction of protected tree");
        labels.put(PAINT_DAMAGE, "paint damage to bulldozer");
    }
}
