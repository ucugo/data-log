package com.mahull.model.model.inventory;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.mahull.model.model.inventory.InventoryType.QUANTITY_ITEM;
import static com.mahull.model.model.inventory.InventoryType.UNIQUE_ITEM;
import static com.mahull.model.model.inventory.InventoryType.fromLabel;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 10/04/2016.
 */
public class InventoryTypeTest {

    @Test
    public void willThrowExceptionWhenLabelDoesNotExist() {
        Assertions.assertThatThrownBy(() -> fromLabel("wrong_label"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong_label: As Inventory Type Label Is Not Recognised");
    }

    @Test
    public void willReturnAnInventoryTypeLabel() {

        assertThat(QUANTITY_ITEM.getInventoryTypeAsString()).isEqualTo("Quantity Inventory Item");
        assertThat(UNIQUE_ITEM.getInventoryTypeAsString()).isEqualTo("Unique Inventory Item");
    }

    @Test
    public void willReturnAnInventoryTypeObject() {

        assertThat(fromLabel("Quantity Inventory Item")).isEqualTo(QUANTITY_ITEM);
        assertThat(fromLabel("Unique Inventory Item")).isEqualTo(UNIQUE_ITEM);
    }

}