package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

public class CostsTest {

    @Test
    public void getQuantities() {
        Costs costs = new Costs();
        Assert.assertEquals("Unexpected Inumber of ItemType", ItemType.values().length, costs.quantities.size());
        Assert.assertEquals(new Integer(0), costs.quantities.get(ItemType.FUEL_USAGE));
        Assert.assertEquals(new Integer(0), costs.quantities.get(ItemType.COMMUNICATION_OVERHEAD));
    }

    @Test
    public void incCost() {
        Costs costs = new Costs();
        Assert.assertEquals("Unexpected Inumber of ItemType", ItemType.values().length, costs.quantities.size());
        Assert.assertEquals(new Integer(0), costs.quantities.get(ItemType.FUEL_USAGE));
        costs.incItemQuantity(ItemType.FUEL_USAGE, 1);
        Assert.assertEquals(new Integer(1), costs.quantities.get(ItemType.FUEL_USAGE));
    }

    @Test
    public void incFuelUsage() {

    }
}