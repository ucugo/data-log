package com.mahul.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.mahul.util.Constants.HOME_VIEW;
import static com.mahul.util.Constants.LOGIN_VIEW;


@Controller
public class LoginConroller {

    @RequestMapping(LOGIN_VIEW)
    public String login() {
        return LOGIN_VIEW;
    }


    @RequestMapping(value = {LOGIN_VIEW}, method = RequestMethod.POST)
    public String logins() {
        return LOGIN_VIEW;
    }

    @RequestMapping(HOME_VIEW)
    public String home() {
        return HOME_VIEW;
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
