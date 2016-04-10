package com.mahull.model.model.inventory;

/**
 * Created by Ugo on 10/04/2016.
 */

import java.util.Arrays;

import static java.lang.String.format;

/**
 * Higher priority Items will be displayed by default
 */
public enum SortingPriority {

    HIGHEST("Highest"), HIGH("High"), NORMAL("Normal"), LOW("Low"), LOWEST("Lowest");

    private final String priority;

    SortingPriority(String priority) {
        this.priority = priority;
    }

    public String getPriorityAsString() {
        return priority;
    }

    public static SortingPriority fromLabel(String priority) {
        return Arrays.asList(SortingPriority.values())
                .stream()
                .filter(sortingPriority -> sortingPriority.priority.equalsIgnoreCase(priority))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        format("%s: As a sorting priority value is not recognised", priority)));
    }
}
