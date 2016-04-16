package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static java.util.Objects.requireNonNull;

/**
 * Created by Ugo on 13/04/2016.
 */

@org.springframework.stereotype.Repository
@Transactional
public class CategoryRepository extends Repository {

    @Autowired
    public CategoryRepository(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param category .
     */
    public void save(Category category) {
        requireNonNull(category);

        getEntityManager().persist(category);
    }
}
