package com.mahul.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginConroller {

    private RestTemplate restTemplate;

    @Autowired
    public LoginConroller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = {"/mmmm"}, method = RequestMethod.POST)
    public String login(HttpServletRequest request) {

        String result = restTemplate.getForObject("http://localhost:8080/test", String.class);
        return result;
    }
}
