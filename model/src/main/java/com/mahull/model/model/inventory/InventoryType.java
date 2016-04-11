package com.mahull.model.model.inventory;

import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * Created by Ugo on 10/04/2016.
 */
public enum InventoryType {

    UNIQUE_ITEM("Unique Inventory Item"), QUANTITY_ITEM("Quantity Inventory Item");

    private final String label;

    InventoryType(String label) {
        this.label = label;
    }

    public String getInventoryTypeAsString() {
        return label;
    }

    /**
     *
     * @param label Stringvalue.
     * @return InventoryTYpe object.
     */
    public static InventoryType fromLabel(String label) {
        return asList(InventoryType.values())
                .stream()
                .filter(inventoryType -> inventoryType.label.equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        format("%s: As Inventory Type Label Is Not Recognised", label)));
    }
}
