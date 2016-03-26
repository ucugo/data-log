package com.mahull.admin.controllers.login;

import com.mahull.admin.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginConroller {

    @RequestMapping(Constants.LOGIN_VIEW)
    public String login() {
        return Constants.LOGIN_VIEW;
    }


    @RequestMapping(value = {Constants.LOGIN_VIEW}, method = RequestMethod.POST)
    public String logins() {
        return Constants.LOGIN_VIEW;
    }

    @RequestMapping(Constants.HOME_VIEW)
    public String home() {
        return Constants.HOME_VIEW;
    }

    /**
     * Mapping to hello view.
     * @param request accepts HttpServletRequest argument
     * @return a model and view object.
     */
    @RequestMapping("hello")
    public ModelAndView hello(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();

        map.put("httpServletRequest", request);
        return new ModelAndView("hello", map) ;
    }
}
