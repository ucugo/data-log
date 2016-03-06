package com.mahull.repositories;

import com.mahull.model.ModelObject;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Ugo on 05/03/2016.
 */
public abstract class Repository<T extends ModelObject> implements BaseRepository<T> {

    private final EntityManager entityManager;

    private static final String List_ALL_ITEMS = "select e from $s e where e.status = :status";

    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T get(T entity, Class<T> clazz) {
        return entityManager.find(clazz, entity.getId());
    }

    @Override
    public List<T> listItems(Class<T> clazz, String status) {
        final String query = String.format(List_ALL_ITEMS, clazz.getSimpleName());
        return entityManager.createQuery(query, clazz).setParameter("status", status).getResultList();
    }

    @Override
    public Boolean contains(T entity) {
        return entityManager.contains(entity);
    }
}
