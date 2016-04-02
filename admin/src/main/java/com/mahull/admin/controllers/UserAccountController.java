package com.mahull.admin.controllers;

import com.mahull.model.model.CraftUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ugo on 30/03/2016.
 */
@Controller
@RequestMapping(value = "/account/*")
public class UserAccountController extends BaseController {

    @ModelAttribute("craftUser")
    public CraftUser craftUser(HttpServletRequest request) {
        return getCraftUser(request);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String show() {
        return "/account/new-user";
    }

    @RequestMapping( method = RequestMethod.POST)
    public String addNewUser(CraftUser craftUser, BindingResult bindingResult, Model model) {
        return "/account/new-user";
    }
}
