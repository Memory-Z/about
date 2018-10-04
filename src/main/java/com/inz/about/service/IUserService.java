package com.inz.about.service;

import com.inz.about.model.UserInfo;
import org.springframework.stereotype.Service;

/**
 * 用户 Service 接口
 *
 * @author 11654
 * @version 1.0.0
 * Create By Zhenglj on 2018/10/2 09:08
 **/
public interface IUserService {
    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserInfo login(String userName, String password);

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findByUsername(String username);
}
