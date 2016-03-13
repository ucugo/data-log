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

    private static final String List_ALL_ITEMS = "From %s as e where e.%s = :%s";

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void save(T entity) {
        entityManager.persist(entity);
    }

    protected T get(T entity, Class<T> clazz) {
        return entityManager.find(clazz, entity.getId());
    }

    protected List<T> findWithStatus(Class<T> clazz, String status) {
        final String query = String.format(List_ALL_ITEMS, clazz.getSimpleName());
        return entityManager.createQuery(query, clazz).setParameter("status", status).getResultList();
    }

    protected T getWithStatus(Class<T> clazz, String status) {
        final String query = String.format(List_ALL_ITEMS, clazz.getSimpleName(), "userName", "status");
        return entityManager.createQuery(query, clazz).setParameter("status", status).getSingleResult();
    }

    protected Boolean contains(T entity) {
        return entityManager.contains(entity);
    }

}
