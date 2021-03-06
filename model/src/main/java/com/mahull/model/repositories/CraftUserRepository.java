package com.mahull.model.repositories;

import com.mahull.model.model.profile.CraftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.mahull.model.query.UserQuery.FIND_USER_WITH_USERNAME;

/**
 * Created by Ugo on 05/03/2016.
 */
@org.springframework.stereotype.Repository
@Transactional
public class CraftUserRepository extends Repository<CraftUser> {

    @Autowired
    public CraftUserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public CraftUser getWithUserName(String userName) {
        return getEntityManager().createQuery(FIND_USER_WITH_USERNAME, CraftUser.class)
                .setParameter("userName", userName).getSingleResult();
    }

    public CraftUser getWithId(long id) {
        return get(CraftUser.class, id);
    }

    public CraftUser updateUser(CraftUser craftUser) {
        return getEntityManager().merge(craftUser);
    }
}
