package com.mahull.model.model.inventory;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.profile.CraftUser;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ugo on 10/04/2016.
 */
@Entity
public class Item extends ModelObject {

    @ManyToMany(mappedBy = "item", targetEntity = CraftUser.class, cascade = CascadeType.ALL)
    private CraftUser craftUser;

    @ManyToMany(mappedBy = "item", targetEntity = Category.class, cascade = CascadeType.ALL)
    private Category category;

    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchasedDate;

    @Enumerated(EnumType.STRING)
    private InventoryType inventoryType;

    private String description;
    private double purchasePricePerUnit;
    private double defaultSeelingPrice;
    private long quantity;
    private boolean lowInventoryReminder;
    private SortingPriority sortingPriority;
}
