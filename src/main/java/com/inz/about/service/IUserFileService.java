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
}
