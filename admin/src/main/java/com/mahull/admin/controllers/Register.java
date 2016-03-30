package com.mahull.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ugo on 28/03/2016.
 */
@Controller
public class Register extends BaseController {

    @RequestMapping(value = "/register")
    public String login() {
        return "register";
    }
}
