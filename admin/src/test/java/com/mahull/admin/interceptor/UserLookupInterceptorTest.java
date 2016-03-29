package com.mahull.admin.interceptor;

import com.mahull.model.model.CraftUser;
import com.mahull.model.repositories.CraftUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


/**
 * Created by Ugo on 16/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserLookupInterceptorTest {


    private MockHttpServletResponse httpServletResponse;
    private MockHttpServletRequest httpServletRequest;

    @Mock
    private CraftUserRepository userRepository;
    @Mock
    Object object;

    @InjectMocks
    private UserLookupInterceptor userLookupInterceptor;


    @Before
    public void init() throws Exception {
        this.httpServletRequest = new MockHttpServletRequest();
        this.userLookupInterceptor = new UserLookupInterceptor(userRepository);
    }

    @Test
    public void CraftUserObjectIsAddedToRequestScopeWhenPreHandleExecutes() throws Exception {

        httpServletRequest.setRemoteUser("remote_user");
        when(userRepository.getWithUserName(anyString())).thenReturn(new CraftUser());


        this.userLookupInterceptor.preHandle(httpServletRequest, httpServletResponse, object);

        assertThat(httpServletRequest.getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
        assertThat(httpServletRequest.getSession().getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
    }

    @Test
    public void CraftUserObjectIsNotAddedToSessionScopeWhenUserIsNotFoundInDatabase() throws Exception {
        httpServletRequest.setRemoteUser("remote_user");
        httpServletRequest.getSession().setAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME, null);
        when(userRepository.getWithUserName(anyString())).thenReturn(null);


        this.userLookupInterceptor.preHandle(httpServletRequest, httpServletResponse, object);

        assertThat(httpServletRequest.getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
        assertThat(httpServletRequest.getSession().getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNull();
    }

    @Test
    public void NewCraftUserObjectIsReturnedWhenRemoteUserIsNullAndSessionScopedObjectIsNull() throws Exception{

        this.userLookupInterceptor.preHandle(httpServletRequest, httpServletResponse, object);

        assertThat(httpServletRequest.getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
        assertThat(httpServletRequest.getSession().getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNull();
    }

    @Test
      public void CraftUserObjectIsReturnedWhenAlreadyPresentInSessionScope() throws Exception {
        httpServletRequest.setRemoteUser("remote_user");
        httpServletRequest.getSession().setAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME, new CraftUser());
        when(userRepository.getWithUserName(anyString())).thenReturn(null);


        this.userLookupInterceptor.preHandle(httpServletRequest, httpServletResponse, object);

        assertThat(httpServletRequest.getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
        assertThat(httpServletRequest.getSession().getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME)).isNotNull();
    }
}