package com.mahull.repositories;

import com.mahull.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * Created by Ugo on 05/03/2016.
 */
@org.springframework.stereotype.Repository
public class UserRepository extends Repository<User> {

    @Autowired
    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public User get(User user, Class<User> clazz) {
        Objects.requireNonNull(user);
        return super.get(user, clazz);
    }

    public void save(User user) {
        Objects.requireNonNull(user);
        super.save(user);
    }
}
