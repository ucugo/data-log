package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Category;
import com.mahull.model.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

import static com.mahull.model.query.UserQuery.FIND_CATEGORIES_WITH_CRAFT_USER_ID;
import static com.mahull.model.query.UserQuery.FIND_CATEGORIES_WITH_CRAFT_USER_ID_AND_CATEGORY_NAME;

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
                .createQuery(FIND_CATEGORIES_WITH_CRAFT_USER_ID, Category.class)
                .setParameter("craftUserId", craftUserId)
                .getResultList();
    }

    /**
     *
     * @param craftUserId .
     * @param categoryName .
     * @return .
     */
    public Category getWithCraftUserIdAndCategoryName(Long craftUserId, String categoryName) {
        return getEntityManager()
                .createQuery(FIND_CATEGORIES_WITH_CRAFT_USER_ID_AND_CATEGORY_NAME, Category.class)
                .setParameter("craftUserId", craftUserId)
                .setParameter("categoryName", categoryName)
                .getSingleResult();
    }
}
