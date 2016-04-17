package com.mahull.model.model.inventory;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.constraints.NonBlank;
import com.mahull.model.model.profile.CraftUser;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Created by Ugo on 10/04/2016.
 */
@Entity
public class Item extends ModelObject {

    private String description;
    private long quantity;
    private boolean lowInventoryReminder;
    private SortingPriority sortingPriority;

    @ManyToOne(optional = false)
    @NotNull
    private CraftUser craftUser;

    @ManyToOne(optional = false)
    @NotNull
    private Category category;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date purchasedDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private InventoryType inventoryType;

    @NonBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    private double purchasePricePerUnit;

    @NotNull
    private double defaultSellingPrice;

    public CraftUser getCraftUser() {
        return craftUser;
    }

    public void setCraftUser(CraftUser craftUser) {
        this.craftUser = craftUser;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getDefaultSellingPrice() {
        return defaultSellingPrice;
    }

    public void setDefaultSellingPrice(double defaultSellingPrice) {
        this.defaultSellingPrice = defaultSellingPrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isLowInventoryReminder() {
        return lowInventoryReminder;
    }

    public void setLowInventoryReminder(boolean lowInventoryReminder) {
        this.lowInventoryReminder = lowInventoryReminder;
    }

    public SortingPriority getSortingPriority() {
        return sortingPriority;
    }

    public void setSortingPriority(SortingPriority sortingPriority) {
        this.sortingPriority = sortingPriority;
    }
}
