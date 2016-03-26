package com.mahull.model.repositories;

import com.mahull.model.config.JpaConfigTest;
import com.mahull.model.model.CraftUser;
import com.mahull.model.security.Role;
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
@ContextConfiguration(classes = JpaConfigTest.class)
@TestPropertySource(locations = {"/application-test.properties"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class CraftCraftUserRepositoryTest {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String USER_NAME = "username@";
    private static final String SOME_PASSWORD = "password";
    @Autowired
    private CraftUserRepository craftUserRepository;

    @Before
    public void setUp() {

    }

    @Test
    public void whenNewUserIsAddedThenItShouldBeStoredInTheDatabase() {
        CraftUser craftUser = getUser();

        craftUserRepository.save(craftUser);

        CraftUser returnedCraftUser = craftUserRepository.get(CraftUser.class, craftUser);
        assertThat(returnedCraftUser).isNotNull();
        assertThat(returnedCraftUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(returnedCraftUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(returnedCraftUser.getUserName()).isEqualTo(USER_NAME);
        validateConstraint(craftUser, 0);
    }

    @Test
    public void whenUserDoesNotExistInTheDatabaseThenNoUserWillBeReturned() {
        CraftUser craftUser = getUser();
        CraftUser returnedCraftUser = craftUserRepository.get(CraftUser.class, craftUser);

        assertThat(returnedCraftUser).isNull();
        validateConstraint(craftUser, 0);
    }

    @Test
    public void shouldThrowExceptionWhenMandatoryFieldIsNotSetDuringSaveOrUpdate() {
        CraftUser craftUser = new CraftUser();

        Assertions.assertThatExceptionOfType(TransactionSystemException.class).isThrownBy(() -> craftUserRepository.save(craftUser));
        validateConstraint(craftUser, 4);
    }

    @Test
    public void whenAskedToFindUserWithUserNameShouldReturnAUser() {
        craftUserRepository.save(getUser());

        CraftUser returnedCraftUser = craftUserRepository.getWithUserName(USER_NAME);

        assertThat(returnedCraftUser).isNotNull();
        assertThat(returnedCraftUser.getUserName()).isEqualTo(USER_NAME);
    }

    @Test
    public void whenUserIsUpdatedThenDatabaseWillBeUpdated() {

        final String newName = "new_name";

        CraftUser craftUser = getUser();
        craftUserRepository.save(craftUser);

        CraftUser returnedCraftUser = craftUserRepository.get(CraftUser.class, craftUser);
        returnedCraftUser.setFirstName(newName);

        CraftUser updatedCraftUser = craftUserRepository.updateUser(returnedCraftUser);

        assertThat(updatedCraftUser).isEqualTo(returnedCraftUser);
        assertThat(updatedCraftUser.getFirstName()).isEqualTo(newName);
        validateConstraint(updatedCraftUser, 0);
    }

    private CraftUser getUser() {
        CraftUser craftUser = new CraftUser();
        craftUser.setFirstName(FIRST_NAME);
        craftUser.setLastName(LAST_NAME);
        craftUser.setUserName(USER_NAME);
        craftUser.setPassword(SOME_PASSWORD);
        craftUser.setRole(Role.USER_ROLE);
        return craftUser;
    }

    private void validateConstraint(CraftUser craftUser, int violationSize) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        Set<ConstraintViolation<CraftUser>> constraintViolations =
                factory.getValidator().validate(craftUser);

        assertThat(constraintViolations.size()).isEqualTo(violationSize);
    }


}