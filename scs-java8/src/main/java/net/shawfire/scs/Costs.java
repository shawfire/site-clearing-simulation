package net.shawfire.scs;

import java.util.EnumMap;

/**
 * The Costs class enables incrementing quantities of on ItemType.
 * This class also displays a summary table of ItemType quantities and costs.
 */
public class Costs {
    private EnumMap<ItemType,Integer> quantities = new EnumMap<>(ItemType.class);

    private static String ItemColumnHeading =  "Item";
    private static String QuantityColumnHeading =  "Quantity";
    private static String CostColumnHeading =  "Cost";

    public Costs() {
        for (ItemType itemType : ItemType.values()) {
            quantities.put(itemType, 0);
        }
    }

    /**
     * Increment the cost of the item by the value provided
     */
    public void incItemQuantity(ItemType itemType, int amount) {
        quantities.put(itemType, getQuantity(itemType) + amount);
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

    public Integer getQuantity(ItemType itemType) {
        return quantities.get(itemType);
    }

    public void displayCosts() {
        Utils.println("\n" + Constants.CostsHeading + "\n");
        Utils.print(String.format(ItemType.getRowFormat(), ItemColumnHeading, QuantityColumnHeading, CostColumnHeading));
        Integer totalCost = 0;
        for (ItemType itemType : ItemType.values()) {
            Integer cost = ItemCostLookup.costs.get(itemType) * getQuantity(itemType);
            totalCost += cost;
            Utils.print(String.format(ItemType.getRowFormat(), ItemType.labels.get(itemType), getQuantity(itemType), cost));
        }
        Utils.println(Constants.TotalSeparator);
        Utils.print(String.format(ItemType.getRowFormat(), Constants.TotalLabel, "", totalCost));
    }
}
