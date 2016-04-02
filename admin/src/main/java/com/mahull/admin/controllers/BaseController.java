package com.mahull.admin.controllers;

import com.mahull.model.model.CraftUser;
import com.mahull.model.repositories.CraftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ugo on 15/03/2016.
 */
public abstract class BaseController {

    @Autowired
    protected CraftUserRepository craftUserRepository;

    protected CraftUser getCraftUser(HttpServletRequest request) {

        CraftUser craftUser = (CraftUser)request.getAttribute(CraftUser.REQUEST_SCOPED_ATTRIBUTE_NAME);
        return craftUser == null ? new CraftUser() : craftUser;
    }
}
