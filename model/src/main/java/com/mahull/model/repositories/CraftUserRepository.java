package com.mahull.model.repositories;

import com.mahull.model.model.CraftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
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

    public void save(CraftUser craftUser) throws TransactionSystemException {
        Objects.requireNonNull(craftUser);
        getEntityManager().persist(craftUser);
    }

    public CraftUser getWithUserName(String userName) {
        return getEntityManager().createQuery(FIND_USER_WITH_USERNAME, CraftUser.class)
                .setParameter("userName", userName).getSingleResult();
    }

    public CraftUser updateUser(CraftUser craftUser) {
        return getEntityManager().merge(craftUser);
    }
}
