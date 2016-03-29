package com.mahull.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ugo on 29/03/2016.
 */

@Controller
@RequestMapping(value = "/*")
public class RouteController extends BaseController {

    /**
     * routing controller.
     * @param request servletRequest.
     * @param response servletResponse.
     * @return a string.
     * @throws IOException error.
     */
    @RequestMapping
    public String route(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (request.getRemoteUser() == null) {
            response.sendRedirect(request.getPathInfo());
        }
        throw new IllegalArgumentException();
    }
}
