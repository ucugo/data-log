package com.mahull.repositories;

import com.mahull.config.JPAConfigTest;
import com.mahull.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Ugo on 05/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfigTest.class)
@TestPropertySource(locations = {"/application-test.properties"})
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;
    @Before
    public void setUp() {

    }

    @Test
    public void whenNewUserIsAddedThenItShouldBeStoredInTheDatabase() {
        User user = new User();
        user.setFirstName("Barry");
        user.setLastName("Ugo");
        user.setUserName("username");
        user.setId(20l);
        userRepository.save(user);

        User returnedUser = userRepository.get(user, User.class);

        Assertions.assertThat(returnedUser).isNotNull();

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//
//        Set<ConstraintViolation<User>> constraintViolations =
//                factory.getValidator().validate(user);
//
//        Assertions.assertThat(constraintViolations.size()).isEqualTo(0);
    }

}