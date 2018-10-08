package com.inz.about.controller;

import com.inz.about.model.UserInfo;
import com.inz.about.service.IUserInfoService;
import com.inz.about.util.BaseUtil;
import com.inz.about.util.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/4 16:45
 **/
@Controller
@RequestMapping(value = "")
public class MainController {

    private final IUserInfoService userInfoService;

    private String loginUrl = "/login.html";
    private Calendar calendar = Calendar.getInstance(Locale.CANADA);

    @Autowired
    public MainController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * /index 首页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String onPageLoad(HttpServletRequest request) {
        UserInfo userInfo = new UserInfo();
        request.setAttribute("userInfo", userInfo);
        return "/index.html";
    }

    /**
     * 进入登录界面 login
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginOnPageLoad(HttpServletRequest request) {
        request.setAttribute("errorMsg", "");
        return loginUrl;
    }

    /**
     * 登录界面提交信息 login
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginOnSubmit(HttpServletRequest request) {

        String signType = request.getParameter("signType");
        if ("in".equalsIgnoreCase(signType)) {
            // 登录
            String userEmail = request.getParameter("inUserEmail");
            String userPassword = request.getParameter("inPassword");
            UserInfo userInfo = userInfoService.login(userEmail, userPassword);
        } else if ("up".equalsIgnoreCase(signType)) {
            // 注册
            String userEmail = request.getParameter("upUserEmail");
            String userName = request.getParameter("upUserName");
            String userPassword = request.getParameter("upPassword");
            String userPassword2 = request.getParameter("upPassword2");
            if ("".equals(userName)) {
                request.setAttribute("errorMsg", "用户名不能为空！");
                return loginUrl;
            }
            if ("".equals(userEmail)) {
                request.setAttribute("errorMsg", "用户邮箱不能为空！");
                return loginUrl;
            }
            if (!userPassword.equals(userPassword2)) {
                request.setAttribute("errorMsg", "两次输入的密码不一致！");
                return loginUrl;
            }
            UserInfo userInfo = new UserInfo();
            String userId = BaseUtil.getRandomUUID();
            userInfo.setUserId(userId);
            userInfo.setUsername(userName);
            userInfo.setUserEmail(userEmail);
            userInfo.setPassword(userPassword);
            Date createTime = calendar.getTime();
            userInfo.setCreateDatetime(createTime);
            userInfoService.register(userInfo);
        } else {
            // 失败
            request.setAttribute("errorType", "signType");
            return loginUrl;
        }
        System.out.println("--" + signType);
        return loginUrl;
    }


    /**
     * 发送邮件
     */
    private void sendEmail() {

    }

    /**
     * 发送邮件线程
     */
    private class SendEmailThread extends Thread {
        /**
         * 发送对象，多个用‘,’分隔
         */
        private String toEmails;
        /**
         * 邮件主题
         */
        private String subject;
        /**
         * 邮件内容
         */
        private String content;

        public SendEmailThread(String toEmails, String subject, String content) {
            this.toEmails = toEmails;
            this.subject = subject;
            this.content = content;
        }

        @Override
        public void run() {
            super.run();
            boolean flag = MailService.sendSimpleMail(toEmails, subject, content);
        }
    }
}
