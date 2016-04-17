package com.mahull.model.model.inventory;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.constraints.NonBlank;
import com.mahull.model.model.profile.CraftUser;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Created by Ugo on 10/04/2016.
 *
 */
@Entity
public class Category extends ModelObject {

    private String description;
    private double defaultPriceMarkup;
    private String customField;

    @NotNull
    @ManyToOne(targetEntity = CraftUser.class)
    private CraftUser craftUser;

    @NonBlank
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    @OneToMany(mappedBy = "category", targetEntity = Item.class)
    private List<Item> items;

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

    public double getDefaultPriceMarkup() {
        return defaultPriceMarkup;
    }

    public void setDefaultPriceMarkup(double defaultPriceMarkup) {
        this.defaultPriceMarkup = defaultPriceMarkup;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public CraftUser getCraftUser() {
        return craftUser;
    }

    public void setCraftUser(CraftUser craftUser) {
        this.craftUser = craftUser;
    }
}
