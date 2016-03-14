package com.mahull.repositories;

import com.mahull.model.ModelObject;

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

    protected T get(Class<T> clazz, T entity) {
        return entityManager.find(clazz, entity.getId());
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
