package com.mahul.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;


@Controller
public class LoginConroller {

    @Autowired
    public LoginConroller(RestTemplate restTemplate) {
    }

    @RequestMapping("login")
    public String login() {     return "login";
    }


    @RequestMapping(value = {"login"}, method = RequestMethod.POST)
    public String logins() {
        return "login";
    }

    @RequestMapping("home")
    public String home() {
        return "home";
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }
}
