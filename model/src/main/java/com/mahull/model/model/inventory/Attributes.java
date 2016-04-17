package com.mahull.model.model.inventory;

import com.mahull.model.model.ModelObject;
import com.mahull.model.model.constraints.NonBlank;

import javax.persistence.Column;

/**
 * Created by Ugo on 10/04/2016.
 * this is mainly for internal use and should not be displayed in any document.
 * Example Shoe color, Sizes
 */
public class Attributes extends ModelObject {

    @NonBlank
    @Column(nullable = false)
    private String name;
    private String displayName;
    private boolean publishToDocuments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isPublishToDocuments() {
        return publishToDocuments;
    }

    public void setPublishToDocuments(boolean publishToDocuments) {
        this.publishToDocuments = publishToDocuments;
    }
}
