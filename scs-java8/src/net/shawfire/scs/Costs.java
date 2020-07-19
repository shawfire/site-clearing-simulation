package net.shawfire.scs;

import java.util.EnumMap;

public class Costs {
    EnumMap<ItemType,Integer> quantities = new EnumMap<>(ItemType.class);

    public Costs() {
        for (ItemType itemType : ItemType.values()) {
            quantities.put(itemType, 0);
        }
    }

    public void incCost(ItemType itemType, int amount) {
        quantities.put(itemType, quantities.get(itemType) + amount);
    }

    public void incFuelCost(SquareType squareType) {
        incCost(ItemType.FUEL_USAGE, Activity.costs.get(squareType));
    }

    public EnumMap<ItemType, Integer> getQuantities() {
        return quantities;
    }
}