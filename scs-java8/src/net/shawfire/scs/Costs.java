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
    public void incItemQuantity(ItemType itemType, int amount) {
        quantities.put(itemType, quantities.get(itemType) + amount);
    }

    /**
     * Increment the cost of the item by a default value of one
     */
    public void incItemQuantity(ItemType itemType) {
        incItemQuantity(itemType, 1);
    }

    public void incItemFuelQuantity(SquareType squareType) {
        // Increment the item based on the ActivityFuelLookup Cost
        incItemQuantity(ItemType.FUEL_USAGE, ActivityFuelLookup.costs.get(squareType));
    }

    public EnumMap<ItemType, Integer> getQuantities() {
        return quantities;
    }

    public void displayCosts() {
        Utils.println("\n" + Constants.CostsHeading + "\n");
        Utils.print(String.format(ItemType.getRowFormat(), "Item", "Quantity", "Cost"));
        for (ItemType itemType : ItemType.values()) {
            Integer cost = ItemCostLookup.costs.get(itemType) * quantities.get(itemType);
            Utils.print(String.format(ItemType.getRowFormat(), ItemType.labels.get(itemType), quantities.get(itemType), cost));
        }
    }
}
