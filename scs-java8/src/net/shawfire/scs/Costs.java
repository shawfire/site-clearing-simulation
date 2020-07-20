package net.shawfire.scs;

import java.util.EnumMap;

public class Costs {
    EnumMap<ItemType,Integer> quantities = new EnumMap<>(ItemType.class);

    public Costs() {
        for (ItemType itemType : ItemType.values()) {
            quantities.put(itemType, 0);
        }
    }

    /**
     * Increment the cost of the item by the value provided
     */
    public void incCost(ItemType itemType, int amount) {
        quantities.put(itemType, quantities.get(itemType) + amount);
    }

    /**
     * Increment the cost of the item by a default value of one
     */
    public void incCost(ItemType itemType) {
        incCost(itemType, 1);
    }

    public void incItemCost(ItemType itemType, SquareType squareType) {
        incCost(itemType, Activity.costs.get(squareType));
    }

    public EnumMap<ItemType, Integer> getQuantities() {
        return quantities;
    }
}
