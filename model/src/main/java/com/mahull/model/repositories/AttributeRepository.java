package com.mahull.model.repositories;

import com.mahull.model.model.inventory.Attribute;
import com.mahull.model.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by Ugo on 19/04/2016.
 */
@org.springframework.stereotype.Repository
@Transactional
public class AttributeRepository extends Repository<Attribute> {

    @Autowired
    public AttributeRepository(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     *
     * @param craftUserId .
     * @return .
     */
    public List<Attribute> findWithCraftUserId(Long craftUserId) {
        return getEntityManager()
                .createQuery(UserQuery.FIND_ATTRIBUTES_WITH_USER_ID, Attribute.class)
                .setParameter("craftUserId", craftUserId)
                .getResultList();

    }
}
