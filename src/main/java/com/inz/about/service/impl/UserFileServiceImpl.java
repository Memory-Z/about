package com.inz.about.service.impl;

import com.inz.about.dao.UserFileMapper;
import com.inz.about.model.UserFile;
import com.inz.about.service.IUserFileService;
import com.inz.about.util.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 16:09
 **/
@Service(value = "userFileService")
public class UserFileServiceImpl implements IUserFileService {
    @Resource
    private UserFileMapper userFileMapper;

    @Override
    public boolean insert(UserFile userFile) {
        int num = userFileMapper.insert(userFile);
        return num != 0;
    }

    @Override
    public boolean deleteById(String userFileId) {
        int num = userFileMapper.deleteByPrimaryKey(userFileId);
        return num != 0;
    }

    @Override
    public boolean update(UserFile userFile) {
        int num = userFileMapper.updateByPrimaryKey(userFile);
        return num != 0;
    }

    @Override
    public UserFile findById(String userFileId) {
        return userFileMapper.selectByPrimaryKey(userFileId);
    }

    @Override
    public List<UserFile> findByUserId(String userId, int start, int pageSize) {
        return userFileMapper.findByUserId(userId, start, pageSize);
    }

    @Override
    public List<UserFile> findByFileId(String fileId, int start, int pageSize) {
        return userFileMapper.findByFileId(fileId, start, pageSize);
    }

    @Override
    public UserFile findEnableUserPhotoByUserId(String userId) {
        return userFileMapper.findEnableUserPhotoByUserId(userId);
    }

    @Override
    public List<UserFile> findListEnableUserPhotoByUserId(String userId) {
        return userFileMapper.findListEnableUserPhotoByUserId(userId);
    }

    @Override
    public List<UserFile> findAllUserPhotoByUserId(String userId) {
        return userFileMapper.findAllUserPhotoByUserId(userId);
    }

    @Override
    public int findLastOrder(String userId) {
        String orderStr = userFileMapper.findLastOrder(userId);
        int order = 0;
        if (!BaseUtil.isEmpty(orderStr)) {
            try {
                order = Integer.parseInt(orderStr);
            } catch (Exception e) {
                System.out.println("==UserFileServiceImpl: " + e.getMessage());
            }
        }
        return order;
    }
}
