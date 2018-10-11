package com.inz.about.service.impl;

import com.inz.about.dao.UserInfoMapper;
import com.inz.about.model.UserInfo;
import com.inz.about.service.IUserInfoService;
import com.inz.about.util.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 11654
 * @version 1.0.0
 * Create By Zhenglj on 2018/10/2 10:32
 **/
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean insert(UserInfo userInfo) {
        int num = userInfoMapper.insert(userInfo);
        return num == 0;
    }

    @Override
    public boolean deleteById(String userId) {
        int num = userInfoMapper.deleteByPrimaryKey(userId);
        return num != 0;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        int num = userInfoMapper.updateByPrimaryKey(userInfo);
        return num != 0;
    }

    @Override
    public UserInfo findById(String userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo login(String userName, String password) {
        if (!BaseUtil.isEmpty(userName) && !BaseUtil.isEmpty(password)) {
            // MD5 加密
            password = BaseUtil.encryptMd5(password);
            return userInfoMapper.loginByName(userName, password);
        } else {
            return null;
        }
    }

    @Override
    public UserInfo findByUsername(String username) {
        if (!BaseUtil.isEmpty(username)) {
            return userInfoMapper.findByUsername(username);
        } else {
            return null;
        }
    }

    @Override
    public boolean register(UserInfo userInfo) {
        if (userInfo == null) {
            return false;
        }
        String password = userInfo.getPassword();
        // MD5 加密
        password = BaseUtil.encryptMd5(password);
        userInfo.setPassword(password);
        int num = userInfoMapper.insert(userInfo);
        return num > 0;
    }

    @Override
    public UserInfo login(String username, String userEmail, String password) {
        if (password == null) {
            return null;
        }
        // MD5 加密
        password = BaseUtil.encryptMd5(password);
        return userInfoMapper.login(username, userEmail, password);
    }
}
