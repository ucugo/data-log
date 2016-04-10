package com.mahull.admin.interceptor;

import com.mahull.model.model.profile.CraftUser;
import com.mahull.model.repositories.CraftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ugo on 15/03/2016.
 */
@Service
public class UserLookupInterceptor implements HandlerInterceptor {

    private CraftUserRepository craftUserRepository;

    @Autowired
    public UserLookupInterceptor(CraftUserRepository craftUserRepository) {
        this.craftUserRepository = craftUserRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String user = request.getRemoteUser();

        CraftUser craftUser = loadCraftUser(request, user);
        request.setAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME, craftUser);

        return true;
    }

    private CraftUser loadCraftUser(HttpServletRequest request, String userName) {

        if (userName == null) {
            return new CraftUser();
        }

        CraftUser craftUser = (CraftUser)request.getSession().getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME);

        if (craftUser != null) {
            return craftUser;
        }

        craftUser = craftUserRepository.getWithUserName(userName);

        if (craftUser == null) {
            return new CraftUser();
        }

        request.getSession().setAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME, craftUser);

        return craftUser;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                           Object object, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object object, Exception exception) throws Exception {

    }
}
