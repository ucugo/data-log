package com.mahull.repositories;

import com.mahull.model.ModelObject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Ugo on 05/03/2016.
 */
public abstract class Repository<T extends ModelObject>  {

    @PersistenceContext
    private final EntityManager entityManager;

    private static final String List_ALL_ITEMS = "select e from $s e where e.status = :status";

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void save(T entity) {
        entityManager.persist(entity);
    }

    protected T get(T entity, Class<T> clazz) {
        return entityManager.find(clazz, entity.getId());
    }

    protected List<T> listItems(Class<T> clazz, String status) {
        final String query = String.format(List_ALL_ITEMS, clazz.getSimpleName());
        return entityManager.createQuery(query, clazz).setParameter("status", status).getResultList();
    }

    protected Boolean contains(T entity) {
        return entityManager.contains(entity);
    }
}
