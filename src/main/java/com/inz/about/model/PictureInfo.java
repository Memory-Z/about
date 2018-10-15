package com.inz.about.model;

import java.math.BigDecimal;
import java.util.Date;

public class PictureInfo {
    private String pictureId;

    private String picturePath;

    private String pictureSys;

    private String pictureUrl;

    private BigDecimal pictureSize;

    private String pictureType;

    private String pictureEnable;

    private Date createDatetime;

    private Date updateTime;

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId == null ? null : pictureId.trim();
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath == null ? null : picturePath.trim();
    }

    public String getPictureSys() {
        return pictureSys;
    }

    public void setPictureSys(String pictureSys) {
        this.pictureSys = pictureSys == null ? null : pictureSys.trim();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public BigDecimal getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(BigDecimal pictureSize) {
        this.pictureSize = pictureSize;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType == null ? null : pictureType.trim();
    }

    public String getPictureEnable() {
        return pictureEnable;
    }

    public void setPictureEnable(String pictureEnable) {
        this.pictureEnable = pictureEnable == null ? null : pictureEnable.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}