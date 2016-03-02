package com.mahul.controllers.login;

import com.mahull.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginConroller {

    @Autowired
    public LoginConroller(RestTemplate restTemplate) {
    }

    @RequestMapping(value = "/")
    public String login() {
            return "home";
    }

//    @RequestMapping(value = {"/login.html"}, method = RequestMethod.POST)
//    public String login(@ModelAttribute Login login, Model model) {
//        model.addAttribute("login", login);
//        return "/login.html";
//    }
}
