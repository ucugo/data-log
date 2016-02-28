package com.mahul.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ApplicationConroller {

    private RestTemplate restTemplate;

    @Autowired
    public ApplicationConroller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = {"/"})
    public String method() {

        String result = restTemplate.getForObject("http://localhost:8080/test", String.class);
        return result;
    }
}
