package com.mahull.model.repositories;

import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.util.TestCase;
import com.mahull.model.util.UnitTestsAnnotations;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 05/03/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@UnitTestsAnnotations
public class CraftCraftUserRepositoryTest extends TestCase {

    @Test
    public void whenNewUserIsAddedThenItShouldBeStoredInTheDatabase() {
        CraftUser craftUser = getCraftUser(USER_NAME);

        CraftUser returnedCraftUser = craftUserRepository.get(CraftUser.class, craftUser.getId());
        assertThat(returnedCraftUser).isNotNull();
        assertThat(returnedCraftUser.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(returnedCraftUser.getLastName()).isEqualTo(LAST_NAME);
        assertThat(returnedCraftUser.getUserName()).isEqualTo(USER_NAME);
        assertThat(returnedCraftUser.getPassword()).isEqualTo(SOME_PASSWORD);
        assertThat(returnedCraftUser.getRole()).isEqualTo(User_ROLE);
        assertThat(returnedCraftUser.getCreatedAt()).isNotNull();
        assertThat(returnedCraftUser.getUpdatedAt()).isNotNull();
        validateConstraint(craftUser, 0);
    }

    @Test
    public void shouldThrowNullpointerExceptionWhenUserIdIsNull() {
        CraftUser craftUser = new CraftUser();

        Assertions.assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> craftUserRepository.get(CraftUser.class, null));
        validateConstraint(craftUser, 5);
    }

    @Test
    public void whenAskedToFindUserWithUserNameShouldReturnAUser() {

        getCraftUser(USER_NAME);
        CraftUser returnedCraftUser = craftUserRepository.getWithUserName(USER_NAME);

        assertThat(returnedCraftUser).isNotNull();
        assertThat(returnedCraftUser.getUserName()).isEqualTo(USER_NAME);
    }

    @Test
    public void shouldReturnTrueForIsNewWhenUserIdIsNull() {
        CraftUser craftUser = new CraftUser();

        assertThat(craftUser.isNew()).isTrue();
    }

    @Test
    public void shouldReturnAValidUserWhenCalledToGetWithId() {
        CraftUser craftUser = getCraftUser(USER_NAME);

        assertThat(craftUserRepository.getWithId(craftUser.getId())).isNotNull();
    }

    @Test
    public void whenUserIsUpdatedThenDatabaseWillBeUpdated() {

        final String newName = "new_name";

        CraftUser craftUser = getCraftUser(USER_NAME);

        CraftUser returnedCraftUser = craftUserRepository.get(CraftUser.class, craftUser.getId());
        returnedCraftUser.setFirstName(newName);

        CraftUser updatedCraftUser = craftUserRepository.updateUser(returnedCraftUser);

        assertThat(updatedCraftUser.getId()).isEqualTo(returnedCraftUser.getId());
        assertThat(updatedCraftUser.getFirstName()).isEqualTo(newName);
        validateConstraint(updatedCraftUser, 0);
    }
}