package com.mahull.model.repositories;

import com.mahull.model.model.ModelObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.requireNonNull;

/**
 * Created by Ugo on 05/03/2016.
 */
public class Repository<T extends ModelObject>  {

    @PersistenceContext
    private final EntityManager entityManager;

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected T get(Class<T> clazz, Long id) {
        requireNonNull(id);
        return entityManager.find(clazz, id);
    }

    /**
     *
     * @param entity .
     */
    public void save(T entity) {
        requireNonNull(entity);

        if (entity.isNew()) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
