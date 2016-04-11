package com.mahull.admin.controllers;

import com.mahull.model.model.profile.CraftUser;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ugo on 09/04/2016.
 */
public class BaseControllerTest {

    @Test
    public void shouldReturnExistingCraftUserFromSession() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME, RequestAttributes.SCOPE_SESSION);
        CraftUser craftUser = new StubBaseController().getCraftUser(request);

        assertThat(!craftUser.isNew());
    }

    @Test
    public void shouldCreateANewCraftUser(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        CraftUser craftUser = new StubBaseController().getCraftUser(request);

        assertThat(craftUser.isNew());
    }

    private final class StubBaseController extends BaseController {

    }

}