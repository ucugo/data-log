package com.mahull.model.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Ugo on 05/03/2016.
 */

@MappedSuperclass
public class ModelObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    protected boolean equalsBaseField(ModelObject other) {
        return id == other.id;
    }
}
