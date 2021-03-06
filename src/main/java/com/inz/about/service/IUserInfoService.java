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
public interface IUserInfoService {
    boolean insert(UserInfo userInfo);

    boolean deleteById(String userId);

    boolean update(UserInfo userInfo);

    UserInfo findById(String userId);

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

    /**
     * 用户注册
     *
     * @param userInfo 用户信息
     * @return 是否注册成功
     */
    boolean register(UserInfo userInfo);

    /**
     * 用户登录
     *
     * @param username  用户名
     * @param userEmail 邮箱
     * @param password  密码
     * @return 用户信息
     */
    UserInfo login(String username, String userEmail, String password);
}
