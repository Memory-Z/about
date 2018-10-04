package com.inz.about.controller;

import com.inz.about.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/4 16:45
 **/
@Controller
@RequestMapping(value = "/")
public class MainController {
    private String loginUrl = "";

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String onPageLoad(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        request.setAttribute("userInfo", userInfo);
        return "/index.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginOnPageLoad(HttpServletRequest request) {
        return "/login.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginOnSubmit(HttpServletRequest request) {
        String userEmail = request.getParameter("userEmail");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("password");
        String signType = request.getParameter("signType");
        return "/login.html";
    }
}
