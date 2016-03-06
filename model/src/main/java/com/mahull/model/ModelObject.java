package com.mahull.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Ugo on 05/03/2016.
 */

@MappedSuperclass
public class ModelObject implements Serializable {

    @Id
    @Generated(GenerationTime.ALWAYS)
    private long id;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
