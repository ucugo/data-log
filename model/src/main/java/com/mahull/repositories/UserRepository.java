package com.mahull.repositories;

import com.mahull.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;

import static com.mahull.query.UserQuery.FIND_USER_WITH_USERNAME;

/**
 * Created by Ugo on 05/03/2016.
 */
@org.springframework.stereotype.Repository
@Transactional
public class UserRepository extends Repository<User> {

    @Autowired
    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public void save(User user) {
        Objects.requireNonNull(user);
        getEntityManager().persist(user);
    }

    public User getWithUserName(String userName) {
        return getEntityManager().createQuery(FIND_USER_WITH_USERNAME, User.class)
                .setParameter("userName", userName).getSingleResult();
    }
}
