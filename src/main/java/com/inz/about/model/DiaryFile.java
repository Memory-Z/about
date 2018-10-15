package com.inz.about.model;

import java.math.BigDecimal;
import java.util.Date;

public class DiaryFile {
    private String mapperId;

    private String diaryId;

    private String fileId;

    private BigDecimal mapperOrder;

    private String mapperMemo;

    private String enable;

    private Date createDatetime;

    private Date updateDatetime;

    public String getMapperId() {
        return mapperId;
    }

    public void setMapperId(String mapperId) {
        this.mapperId = mapperId == null ? null : mapperId.trim();
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId == null ? null : diaryId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public BigDecimal getMapperOrder() {
        return mapperOrder;
    }

    public void setMapperOrder(BigDecimal mapperOrder) {
        this.mapperOrder = mapperOrder;
    }

    public String getMapperMemo() {
        return mapperMemo;
    }

    public void setMapperMemo(String mapperMemo) {
        this.mapperMemo = mapperMemo == null ? null : mapperMemo.trim();
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
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