package com.mahull.model.model.inventory;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static com.mahull.model.model.inventory.SortingPriority.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 10/04/2016.
 */
public class SortingPriorityTest {

    @Test
    public void willThrowExceptionWhenLabelDoesNotExist() {
        Assertions.assertThatThrownBy(() -> fromLabel("wrong_label"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("wrong_label: As a sorting priority value is not recognised");
    }

    @Test
    public void willReturnAnInventoryTypeLabel() {

        assertThat(HIGHEST.getPriorityAsString()).isEqualTo("Highest");
        assertThat(HIGH.getPriorityAsString()).isEqualTo("High");
        assertThat(NORMAL.getPriorityAsString()).isEqualTo("Normal");
        assertThat(LOW.getPriorityAsString()).isEqualTo("Low");
        assertThat(LOWEST.getPriorityAsString()).isEqualTo("Lowest");
    }

    @Test
    public void willReturnAnInventoryTypeObject() {

        assertThat(fromLabel("Highest")).isEqualTo(HIGHEST);
        assertThat(fromLabel("High")).isEqualTo(HIGH);
        assertThat(fromLabel("Normal")).isEqualTo(NORMAL);
        assertThat(fromLabel("Low")).isEqualTo(LOW);
        assertThat(fromLabel("Lowest")).isEqualTo(LOWEST);
    }
}