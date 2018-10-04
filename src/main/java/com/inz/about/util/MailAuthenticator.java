package com.inz.about.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮箱认证
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/2 21:58
 **/
public class MailAuthenticator extends Authenticator {


    private String userName;
    private String password;

    public MailAuthenticator(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }


}

