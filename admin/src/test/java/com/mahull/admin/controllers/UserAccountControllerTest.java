package com.mahull.admin.controllers;

import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.repositories.CraftUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static com.mahull.admin.util.Constants.ADD_NEW_USER_INFO_FORM;
import static com.mahull.admin.util.Constants.ADD_NEW_USER_INFO_SUCCESS;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ugo on 07/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserAccountControllerTest {

    private UserAccountController userAccountController;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private CraftUser craftUser;

    @Mock
    private CraftUserRepository craftUserRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Before
    public void setUp() throws Exception {
        userAccountController = new UserAccountController(craftUserRepository, encoder);
    }

    @Test
    public void whenExistingUserIsUpdatedSuccessfully() {

        when(bindingResult.hasErrors()).thenReturn(false);
        when(craftUserRepository.getWithUserName(anyString())).thenReturn(craftUser);
        when(craftUser.isNew()).thenReturn(false);

        String response = userAccountController.addNewUser(craftUser, bindingResult);

        verify(craftUserRepository, times(1)).updateUser(craftUser);
        assertThat(response).isEqualTo(ADD_NEW_USER_INFO_SUCCESS);
    }

    @Test
    public void whenNewUserIsAddedSuccessfully() {

        when(bindingResult.hasErrors()).thenReturn(false);
        when(craftUserRepository.getWithUserName(anyString())).thenReturn(null);
        when(craftUser.isNew()).thenReturn(true);
        when(craftUser.getPassword()).thenReturn("any_password");

        String response = userAccountController.addNewUser(craftUser, bindingResult);

        verify(craftUserRepository, times(1)).save(craftUser);
        assertThat(response).isEqualTo(ADD_NEW_USER_INFO_SUCCESS);
    }

    @Test
    public void whenBindingResultContainsErrors() {

        when(bindingResult.hasErrors()).thenReturn(true);

        String response = userAccountController.addNewUser(craftUser, bindingResult);

        verify(craftUserRepository, times(0)).save(craftUser);
        verify(craftUserRepository, times(0)).updateUser(craftUser);
        assertThat(response).isEqualTo(ADD_NEW_USER_INFO_FORM);
    }

    @Test
    public void shouldContainModelAttributeWhenViewIsRendered() {

        Model model = Mockito.mock(Model.class);
        String viewName = userAccountController.show(model);

        assertThat(viewName).isEqualTo(ADD_NEW_USER_INFO_FORM);
    }
}