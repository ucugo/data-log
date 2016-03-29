package com.mahull.admin.controllers.births;

import com.mahull.admin.controllers.BaseController;
import com.mahull.model.model.CraftUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ugo on 28/03/2016.
 */
@Controller
@RequestMapping(value = "/births")
public class Register extends BaseController {

    @ModelAttribute(value = "/login")
    public Model craftUser(Model model) {
        model.addAttribute(new CraftUser());
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String login() {
        return "/births/register";
    }
}
