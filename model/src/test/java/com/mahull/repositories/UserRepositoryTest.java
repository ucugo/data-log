package com.mahull.repositories;

import com.mahull.config.JPAConfigTest;
import com.mahull.model.User;
import com.mahull.security.Role;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 05/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JPAConfigTest.class)
@TestPropertySource(locations = {"/application-test.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String USER_NAME = "username@";
    private static final String SOME_PASSWORD = "password";
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void whenNewUserIsAddedThenItShouldBeStoredInTheDatabase() {
        User user = getUser();

        userRepository.save(user);

        User returnedUser = userRepository.get(User.class, user);
        assertThat(returnedUser).isNotNull();
        assertThat(returnedUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(returnedUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(returnedUser.getUserName()).isEqualTo(USER_NAME);
        validateConstraint(user, 0);
    }

    @Test
    public void whenUserDoesNotExistInTheDatabaseThenNoUserWillBeReturned() {
        User user = getUser();
        User returnedUser = userRepository.get(User.class, user);

        assertThat(returnedUser).isNull();
        validateConstraint(user, 0);
    }

    @Test
    public void shouldThrowExceptionWhenMandatoryFieldIsNotSetDuringSaveOrUpdate() {
        User user = new User();

        Assertions.assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> userRepository.save(user));
        validateConstraint(user, 4);
    }

    @Test
    public void whenAskedToFindUserWithUserNameShouldReturnAUser() {
        userRepository.save(getUser());

        User returnedUser = userRepository.getWithUserName(USER_NAME);

        assertThat(returnedUser).isNotNull();
        assertThat(returnedUser.getUserName()).isEqualTo(USER_NAME);
    }

    @Test
    public void whenUserIsUpdatedThenDatabaseWillBeUpdated() {

        final String newName = "new_name";

        User user = getUser();
        userRepository.save(user);

        User returnedUser = userRepository.get(User.class, user);
        returnedUser.setFirstName(newName);

        User updatedUser = userRepository.updateUser(returnedUser);

        assertThat(updatedUser).isEqualTo(returnedUser);
        assertThat(updatedUser.getFirstName()).isEqualTo(newName);
        validateConstraint(updatedUser, 0);
    }

    private User getUser() {
        User user = new User();
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setUserName(USER_NAME);
        user.setPassword(SOME_PASSWORD);
        user.setRole(Role.USER_ROLE);
        return user;
    }

    private void validateConstraint(User user, int violationSize) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Set<ConstraintViolation<User>> constraintViolations =
                factory.getValidator().validate(user);

        assertThat(constraintViolations.size()).isEqualTo(violationSize);
    }


}