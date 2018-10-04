package com.inz.about.service.impl;

import com.inz.about.dao.IUserInfoDao;
import com.inz.about.model.UserInfo;
import com.inz.about.service.IUserService;
import com.inz.about.util.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 11654
 * @version 1.0.0
 * Create By Zhenglj on 2018/10/2 10:32
 **/
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserInfoDao userInfoDao;

    @Override
    public UserInfo login(String userName, String password) {
        if (!BaseUtil.isEmpty(userName) && !BaseUtil.isEmpty(password)) {
            return userInfoDao.loginByName(userName, password);
        } else {
            return null;
        }
    }

    @Override
    public UserInfo findByUsername(String username) {
        if (!BaseUtil.isEmpty(username)) {
            return userInfoDao.findByUsername(username);
        } else {
            return null;
        }
    }
}
