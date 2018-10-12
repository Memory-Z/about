package com.inz.about.model.api;

import java.io.Serializable;

/**
 * Api 图片信息
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/12 11:48
 **/
public class ApiPictureInfo implements Serializable {
    /**
     * 图片ID
     */
    private String pictureId;
    /**
     * 图片链接
     */
    private String pictureUrl;
    /**
     * 图片大小
     */
    private Double pictureSize;
    /**
     * 图片类型
     */
    private String pictureType;
    /**
     * 图片是否可用
     */
    private String pictureEnable;
    /**
     * 图片创建时间
     */
    private String createDatetime;
    /**
     * 图片更新时间
     */
    private String updateDatetime;

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Double getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(Double pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPictureEnable() {
        return pictureEnable;
    }

    public void setPictureEnable(String pictureEnable) {
        this.pictureEnable = pictureEnable;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    @Override
    public String toString() {
        return "ApiPictureInfo{" +
                "pictureId='" + pictureId + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", pictureSize=" + pictureSize +
                ", pictureType='" + pictureType + '\'' +
                ", pictureEnable='" + pictureEnable + '\'' +
                ", createDatetime='" + createDatetime + '\'' +
                ", updateDatetime='" + updateDatetime + '\'' +
                '}';
    }
}
