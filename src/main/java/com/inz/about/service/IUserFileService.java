package com.inz.about.service;

import com.inz.about.model.UserFile;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 15:59
 **/
public interface IUserFileService {
    boolean insert(UserFile userFile);

    boolean deleteById(String userFileId);

    boolean update(UserFile userFile);

    UserFile findById(String userFileId);

    List<UserFile> findByUserId(String userId, int start, int pageSize);

    List<UserFile> findByFileId(String fileId, int start, int pageSize);

    /**
     * 通过用户Id 查询可用用户头像
     *
     * @param userId 用户Id
     * @return 用户头像文件
     */
    UserFile findEnableUserPhotoByUserId(String userId);

    /**
     * 通过用户Id 查询全部用户头像
     *
     * @param userId 用户ID
     * @return 用户头像文件
     */
    List<UserFile> findAllUserPhotoByUserId(String userId);

    /**
     * 通过用户ID 查询最后一个排序
     *
     * @param userId 用户ID
     * @return 排序
     */
    int findLastOrder(String userId);
}
