package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import com.mahull.model.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

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
     * @param craftUserId .
     * @return .
     */
    public List<Category> getWithCraftUserId(Long craftUserId) {
        return getEntityManager()
                .createQuery(UserQuery.FIND_CATEGORIES_WITH_CRAFT_USER_ID, Category.class)
                .setParameter("craftUserId", craftUserId)
                .getResultList();
    }
}
