package com.inz.about.model;

import java.math.BigDecimal;
import java.util.Date;

public class DiaryInfo {
    private String diaryId;

    private String diaryUserId;

    private String diaryContent;

    private String diaryHaveImage;

    private String diaryWeather;

    private String diaryAddress;

    private BigDecimal diaryOrder;

    private String diaryEnable;

    private Date createDatetime;

    private Date updateDatetime;

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId == null ? null : diaryId.trim();
    }

    public String getDiaryUserId() {
        return diaryUserId;
    }

    public void setDiaryUserId(String diaryUserId) {
        this.diaryUserId = diaryUserId == null ? null : diaryUserId.trim();
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent == null ? null : diaryContent.trim();
    }

    public String getDiaryHaveImage() {
        return diaryHaveImage;
    }

    public void setDiaryHaveImage(String diaryHaveImage) {
        this.diaryHaveImage = diaryHaveImage == null ? null : diaryHaveImage.trim();
    }

    public String getDiaryWeather() {
        return diaryWeather;
    }

    public void setDiaryWeather(String diaryWeather) {
        this.diaryWeather = diaryWeather == null ? null : diaryWeather.trim();
    }

    public String getDiaryAddress() {
        return diaryAddress;
    }

    public void setDiaryAddress(String diaryAddress) {
        this.diaryAddress = diaryAddress == null ? null : diaryAddress.trim();
    }

    public BigDecimal getDiaryOrder() {
        return diaryOrder;
    }

    public void setDiaryOrder(BigDecimal diaryOrder) {
        this.diaryOrder = diaryOrder;
    }

    public String getDiaryEnable() {
        return diaryEnable;
    }

    public void setDiaryEnable(String diaryEnable) {
        this.diaryEnable = diaryEnable == null ? null : diaryEnable.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}