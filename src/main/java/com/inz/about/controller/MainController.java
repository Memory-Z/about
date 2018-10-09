package com.inz.about.controller;

import com.inz.about.model.TempEmail;
import com.inz.about.model.UserInfo;
import com.inz.about.service.ITempEmailService;
import com.inz.about.service.IUserInfoService;
import com.inz.about.util.BaseUtil;
import com.inz.about.util.MailService;
import com.inz.about.util.ThreadPoolProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/4 16:45
 **/
@Controller
@RequestMapping(value = "")
public class MainController {

    private final IUserInfoService userInfoService;
    private final ITempEmailService tempEmailService;

    /**
     * 登录连接地址
     */
    private String loginUrl = "/login.html";
    /**
     * 本地线程池
     */
    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolProxy.getInstance();
    private Calendar calendar = Calendar.getInstance(Locale.CANADA);

    @Autowired
    public MainController(IUserInfoService userInfoService, ITempEmailService tempEmailService) {
        this.userInfoService = userInfoService;
        this.tempEmailService = tempEmailService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String onPageLoad(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getAttribute("userInfo");
        if (userInfo != null) {
            request.setAttribute("userInfo", userInfo);
        }
        return "/index.html";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginOnPageLoad(HttpServletRequest request) {
        request.setAttribute("errorMsg", "");
        return loginUrl;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginOnSubmit(HttpServletRequest request) {

        String signType = request.getParameter("signType");
        if ("in".equalsIgnoreCase(signType)) {
            // 登录
            String userEmail = request.getParameter("inUserEmail");
            String userPassword = request.getParameter("inPassword");
            UserInfo userInfo = userInfoService.login(userEmail, userPassword);
            if (userInfo != null) {
                request.setAttribute("userInfo", userInfo);
                // 重定向至主页
                return "redirect: /index";
            }
            request.setAttribute("errorMsg", "用户邮箱或密码错误！");
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
            boolean flag = userInfoService.register(userInfo);
            if (flag) {
                request.setAttribute("successMsg", "用户注册成功！");
            } else {
                request.setAttribute("errorMsg", "用户注册失败！");
            }
        } else {
            // 失败
            request.setAttribute("errorType", "signType");
            request.setAttribute("errorMsg", "当前操作有误！不存在此操作，请刷新后重试！");
        }
        return loginUrl;
    }

    private void Login() {

    }

    /**
     * 发送邮件
     */
    private void sendEmail() {

    }

    /**
     * 发送邮件线程
     */
    private class SendEmailRunnable implements Runnable {
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
        /**
         * 验证码
         */
        private String verificationCode;

        public SendEmailRunnable(String toEmails, String subject) {
            this.toEmails = toEmails;
            this.subject = subject;
        }

        @Override
        public void run() {
            verificationCode = BaseUtil.getVerifyCode(6);
            boolean flag = MailService.sendSimpleMail(toEmails, subject, content);
            if (flag) {
                String[] emails = toEmails.split(",");
                for (String email : emails) {
                    String tempEmailId = BaseUtil.getRandomUUID();
                    Date createDate = calendar.getTime();
                    TempEmail tempEmail = new TempEmail();
                    tempEmail.setEmailId(tempEmailId);
                    tempEmail.setEmail(email);
                    tempEmail.setKey(verificationCode);
                    tempEmail.setSendTime(createDate);
                    tempEmail.setEnable("1");
                    boolean isSend = tempEmailService.insertTempEmail(tempEmail);
                    if (!isSend) {
                        // 如果未写入成功
                    }
                }
            } else {
                // 邮件发送失败
            }

        }
    }

}
