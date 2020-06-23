package com.inz.about.util;

import com.inz.about.model.*;
import com.inz.about.model.api.DiaryInfoBean;
import com.inz.about.model.api.FileInfoBean;
import com.inz.about.model.api.PictureInfoBean;
import com.inz.about.model.api.UserInfoBean;

import java.util.Date;

/**
 * 模块转换工具类
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/12 9:16
 **/
public class ModelUtil {
    /**
     * 转换为ApiUserInfo
     *
     * @param userInfo 用户信息
     * @param photoUrl 用户头像链接
     * @return UserInfoBean
     */
    public static UserInfoBean userInfoToApi(UserInfo userInfo, String photoUrl) {
        if (userInfo == null) {
            return null;
        }
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserId(userInfo.getUserId());
        userInfoBean.setUsername(userInfo.getUsername());
        userInfoBean.setUserEmail(userInfo.getUserEmail());
        Date birthday = userInfo.getUserBirthday();
        String birthdayStr = BaseUtil.getDateStr(birthday);
        userInfoBean.setUserBirthday(birthdayStr);
        userInfoBean.setUserIntro(userInfo.getUserIntro());
        userInfoBean.setUserMemo(userInfo.getUserMemo());
        userInfoBean.setUserPhone(userInfo.getUserPhone());
        String sex = userInfo.getUserSex();
        String sexStr = "男";
        if ("0".equals(sex)) {
            sexStr = "女";
        }
        userInfoBean.setUserSex(sexStr);
        userInfoBean.setUserType(userInfo.getUserType());
        String userPhotoUrl = "";
        if (!"".equals(photoUrl)) {
            userPhotoUrl = photoUrl;
        }
        userInfoBean.setUserPhotoUrl(userPhotoUrl);
        return userInfoBean;
    }

    /**
     * 转换为ApiDiaryInfo
     *
     * @param diaryInfo 日志信息
     * @return DiaryInfoBean
     */
    public static DiaryInfoBean diaryInfoToApi(DiaryInfo diaryInfo) {
        if (diaryInfo == null) {
            return null;
        }
        DiaryInfoBean diaryInfoBean = new DiaryInfoBean();
        diaryInfoBean.setDiaryId(diaryInfo.getDiaryId());
        diaryInfoBean.setDiaryAddress(diaryInfo.getDiaryAddress());
        diaryInfoBean.setDiaryContent(diaryInfo.getDiaryContent());
        diaryInfoBean.setDiaryOrder(diaryInfo.getDiaryOrder());
        boolean isMore = false;
        if ("1".equals(diaryInfo.getDiaryHaveImage())) {
            isMore = true;
        }
        diaryInfoBean.setDiaryIsMoreOne(isMore);
        diaryInfoBean.setDiaryUserId(diaryInfo.getDiaryUserId());
        diaryInfoBean.setDiaryWeather(diaryInfo.getDiaryWeather());
        String createTime = BaseUtil.getDateStr(diaryInfo.getCreateDatetime());
        diaryInfoBean.setCreateDatetime(createTime);
        Date updateTime = diaryInfo.getUpdateDatetime();
        String updateTimeStr = "";
        if (updateTime != null) {
            updateTimeStr = BaseUtil.getDateStr(updateTime);
        }
        diaryInfoBean.setUpdateDatetime(updateTimeStr);
        return diaryInfoBean;
    }

    /**
     * 转换为ApiFileInfo
     *
     * @param fileInfo 文件信息
     * @return FileInfoBean
     */
    public static FileInfoBean fileInfoToApi(FileInfo fileInfo) {
        if (fileInfo == null) {
            return null;
        }
        FileInfoBean fileInfoBean = new FileInfoBean();
        fileInfoBean.setFileId(fileInfo.getFileId());
        boolean isEnable = false;
        if ("1".equals(fileInfo.getFileEnable())) {
            isEnable = true;
        }
        fileInfoBean.setFileEnable(isEnable);
        fileInfoBean.setFileName(fileInfo.getFileName());
        fileInfoBean.setFileSize(fileInfo.getFileSize());
        fileInfoBean.setFileType(fileInfo.getFileType());
        fileInfoBean.setFileUrl(fileInfo.getFileUrl());
        Date createTime = fileInfo.getCreateDatetime();
        String createTimeStr = BaseUtil.getDateStr(createTime);
        fileInfoBean.setCreateDatetime(createTimeStr);
        Date updateTime = fileInfo.getUpdateTime();
        String updateTimeStr = "";
        if (updateTime != null) {
            updateTimeStr = BaseUtil.getDateStr(updateTime);
        }
        fileInfoBean.setUpdateTime(updateTimeStr);
        return fileInfoBean;
    }

    /**
     * 转换为 PictureInfoBean
     *
     * @param pictureInfo 图片信息
     * @return PictureInfoBean
     */
    public static PictureInfoBean pictureInfoToApi(PictureInfo pictureInfo) {
        if (pictureInfo == null) {
            return null;
        }
        PictureInfoBean pictureInfoBean = new PictureInfoBean();
        pictureInfoBean.setPictureId(pictureInfo.getPictureId());
        pictureInfoBean.setPictureEnable(pictureInfo.getPictureEnable());
        pictureInfoBean.setPictureSize(pictureInfo.getPictureSize());
        pictureInfoBean.setPictureType(pictureInfo.getPictureType());
        pictureInfoBean.setPictureUrl(pictureInfo.getPictureUrl());
        Date createDate = pictureInfo.getCreateDatetime();
        String createDateStr = BaseUtil.getDateStr(createDate);
        pictureInfoBean.setCreateDatetime(createDateStr);
        Date updateDate = pictureInfo.getUpdateTime();
        String updateDateStr = "";
        if (updateDate != null) {
            updateDateStr = BaseUtil.getDateStr(updateDate);
        }
        pictureInfoBean.setUpdateDatetime(updateDateStr);
        return pictureInfoBean;
    }
}
