package com.mahull.repositories;

import com.mahull.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;

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

    @Modifying(clearAutomatically = true)
    public User get(User user, Class clazz) {
        Objects.requireNonNull(user);
        return super.get(user, User.class);
    }

    public void save(User user) {
        Objects.requireNonNull(user);
        super.save(user);
    }
}
