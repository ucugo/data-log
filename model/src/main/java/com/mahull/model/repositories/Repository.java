package com.mahull.model.repositories;

import com.mahull.model.model.ModelObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Ugo on 05/03/2016.
 */
public class Repository<T extends ModelObject>  {

    @PersistenceContext
    private final EntityManager entityManager;

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected T get(Class<T> clazz, long id) {
        return entityManager.find(clazz, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
