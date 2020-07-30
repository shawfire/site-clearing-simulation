package net.shawfire.scs;

import java.util.EnumMap;

public enum ItemType {
    COMMUNICATION_OVERHEAD, FUEL_USAGE, UNCLEARED_SQUARE, DESTRUCTION_PROTECTED_TREE, PAINT_DAMAGE;

    public final static EnumMap<ItemType, String> labels = new EnumMap<>(ItemType.class);
    private static int maxDescriptionLength = 0;
    private static String rowFormat;
    private static void addLabel(ItemType itemType, String itemDescription) {
        labels.put(itemType, itemDescription);
        maxDescriptionLength = Math.max(maxDescriptionLength, itemDescription.length());
    }
    static {
        addLabel(COMMUNICATION_OVERHEAD, "communication overhead");
        addLabel(FUEL_USAGE, "fuel usage");
        addLabel(UNCLEARED_SQUARE, "uncleared squares");
        addLabel(DESTRUCTION_PROTECTED_TREE, "destruction of protected tree");
        addLabel(PAINT_DAMAGE, "paint damage to bulldozer");
        rowFormat = "%-" + maxDescriptionLength + "s  %8s  %8s%n";
    }

    public static String getRowFormat() {
        return rowFormat;
    }

    public static int getMaxDescriptionLength() {
        return maxDescriptionLength;
    }
}
