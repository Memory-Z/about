package com.inz.about.util;

import com.inz.about.model.*;
import com.inz.about.model.api.ApiDiaryInfo;
import com.inz.about.model.api.ApiFileInfo;
import com.inz.about.model.api.ApiPictureInfo;
import com.inz.about.model.api.ApiUserInfo;

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
     * @return ApiUserInfo
     */
    public static ApiUserInfo userInfoToApi(UserInfo userInfo, String photoUrl) {
        if (userInfo == null) {
            return null;
        }
        ApiUserInfo apiUserInfo = new ApiUserInfo();
        apiUserInfo.setUserId(userInfo.getUserId());
        apiUserInfo.setUsername(userInfo.getUsername());
        apiUserInfo.setUserEmail(userInfo.getUserEmail());
        Date birthday = userInfo.getUserBirthday();
        String birthdayStr = BaseUtil.getDateStr(birthday);
        apiUserInfo.setUserBirthday(birthdayStr);
        apiUserInfo.setUserIntro(userInfo.getUserIntro());
        apiUserInfo.setUserMemo(userInfo.getUserMemo());
        apiUserInfo.setUserPhone(userInfo.getUserPhone());
        String sex = userInfo.getUserSex();
        String sexStr = "男";
        if ("0".equals(sex)) {
            sexStr = "女";
        }
        apiUserInfo.setUserSex(sexStr);
        apiUserInfo.setUserType(userInfo.getUserType());
        String userPhotoUrl = "";
        if (!"".equals(photoUrl)) {
            userPhotoUrl = photoUrl;
        }
        apiUserInfo.setUserPhotoUrl(userPhotoUrl);
        return apiUserInfo;
    }

    /**
     * 转换为ApiDiaryInfo
     *
     * @param diaryInfo 日志信息
     * @return ApiDiaryInfo
     */
    public static ApiDiaryInfo diaryInfoToApi(DiaryInfo diaryInfo) {
        if (diaryInfo == null) {
            return null;
        }
        ApiDiaryInfo apiDiaryInfo = new ApiDiaryInfo();
        apiDiaryInfo.setDiaryId(diaryInfo.getDiaryId());
        apiDiaryInfo.setDiaryAddress(diaryInfo.getDiaryAddress());
        apiDiaryInfo.setDiaryContent(diaryInfo.getDiaryContent());
        apiDiaryInfo.setDiaryOrder(diaryInfo.getDiaryOrder());
        boolean isMore = false;
        if ("1".equals(diaryInfo.getDiaryHaveImage())) {
            isMore = true;
        }
        apiDiaryInfo.setDiaryIsMoreOne(isMore);
        apiDiaryInfo.setDiaryUserId(diaryInfo.getDiaryUserId());
        apiDiaryInfo.setDiaryWeather(diaryInfo.getDiaryWeather());
        String createTime = BaseUtil.getDateStr(diaryInfo.getCreateDatetime());
        apiDiaryInfo.setCreateDatetime(createTime);
        Date updateTime = diaryInfo.getUpdateDatetime();
        String updateTimeStr = "";
        if (updateTime != null) {
            updateTimeStr = BaseUtil.getDateStr(updateTime);
        }
        apiDiaryInfo.setUpdateDatetime(updateTimeStr);
        return apiDiaryInfo;
    }

    /**
     * 转换为ApiFileInfo
     *
     * @param fileInfo 文件信息
     * @return ApiFileInfo
     */
    public static ApiFileInfo fileInfoToApi(FileInfo fileInfo) {
        if (fileInfo == null) {
            return null;
        }
        ApiFileInfo apiFileInfo = new ApiFileInfo();
        apiFileInfo.setFileId(fileInfo.getFileId());
        boolean isEnable = false;
        if ("1".equals(fileInfo.getFileEnable())) {
            isEnable = true;
        }
        apiFileInfo.setFileEnable(isEnable);
        apiFileInfo.setFileName(fileInfo.getFileName());
        apiFileInfo.setFileSize(fileInfo.getFileSize());
        apiFileInfo.setFileType(fileInfo.getFileType());
        apiFileInfo.setFileUrl(fileInfo.getFileUrl());
        Date createTime = fileInfo.getCreateDatetime();
        String createTimeStr = BaseUtil.getDateStr(createTime);
        apiFileInfo.setCreateDatetime(createTimeStr);
        Date updateTime = fileInfo.getUpdateTime();
        String updateTimeStr = "";
        if (updateTime != null) {
            updateTimeStr = BaseUtil.getDateStr(updateTime);
        }
        apiFileInfo.setUpdateTime(updateTimeStr);
        return apiFileInfo;
    }

    /**
     * 转换为 ApiPictureInfo
     *
     * @param pictureInfo 图片信息
     * @return ApiPictureInfo
     */
    public static ApiPictureInfo pictureInfoToApi(PictureInfo pictureInfo) {
        if (pictureInfo == null) {
            return null;
        }
        ApiPictureInfo apiPictureInfo = new ApiPictureInfo();
        apiPictureInfo.setPictureId(pictureInfo.getPictureId());
        apiPictureInfo.setPictureEnable(pictureInfo.getPictureEnable());
        apiPictureInfo.setPictureSize(pictureInfo.getPictureSize());
        apiPictureInfo.setPictureType(pictureInfo.getPictureType());
        apiPictureInfo.setPictureUrl(pictureInfo.getPictureUrl());
        Date createDate = pictureInfo.getCreateDatetime();
        String createDateStr = BaseUtil.getDateStr(createDate);
        apiPictureInfo.setCreateDatetime(createDateStr);
        Date updateDate = pictureInfo.getUpdateTime();
        String updateDateStr = "";
        if (updateDate != null) {
            updateDateStr = BaseUtil.getDateStr(updateDate);
        }
        apiPictureInfo.setUpdateDatetime(updateDateStr);
        return apiPictureInfo;
    }
}
