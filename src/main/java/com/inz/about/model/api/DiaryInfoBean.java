package com.inz.about.model.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Api 日志信息
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/12 10:39
 **/
public class DiaryInfoBean extends BaseApiBean implements Serializable {

    /**
     * 日志Id
     */
    private String diaryId;
    /**
     * 日志用户Id
     */
    private String diaryUserId;
    /**
     * 日志内容
     */
    private String diaryContent;
    /**
     * 日志图片张数
     */
    private int diaryImageNum;
    /**
     * 日志时天气
     */
    private String diaryWeather;
    /**
     * 日志时地址
     */
    private String diaryAddress;
    /**
     * 日志排序号
     */
    private BigDecimal diaryOrder;
    /**
     * 日志是否为多个
     */
    private boolean diaryIsMoreOne;
    /**
     * 日志时间
     */
    private String createDatetime;
    /**
     * 日志更新时间
     */
    private String updateDatetime;
    /**
     * 日志文件信息
     */
    private List<FileInfoBean> fileInfoList;
    /**
     * 日志图片信息
     */
    private List<PictureInfoBean> pictureInfoList;

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryUserId() {
        return diaryUserId;
    }

    public void setDiaryUserId(String diaryUserId) {
        this.diaryUserId = diaryUserId;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent;
    }

    public int getDiaryImageNum() {
        return diaryImageNum;
    }

    public void setDiaryImageNum(int diaryImageNum) {
        this.diaryImageNum = diaryImageNum;
    }

    public String getDiaryWeather() {
        return diaryWeather;
    }

    public void setDiaryWeather(String diaryWeather) {
        this.diaryWeather = diaryWeather;
    }

    public String getDiaryAddress() {
        return diaryAddress;
    }

    public void setDiaryAddress(String diaryAddress) {
        this.diaryAddress = diaryAddress;
    }

    public BigDecimal getDiaryOrder() {
        return diaryOrder;
    }

    public void setDiaryOrder(BigDecimal diaryOrder) {
        this.diaryOrder = diaryOrder;
    }

    public boolean isDiaryIsMoreOne() {
        return diaryIsMoreOne;
    }

    public void setDiaryIsMoreOne(boolean diaryIsMoreOne) {
        this.diaryIsMoreOne = diaryIsMoreOne;
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

    public List<FileInfoBean> getFileInfoList() {
        return fileInfoList;
    }

    public void setFileInfoList(List<FileInfoBean> fileInfoList) {
        this.fileInfoList = fileInfoList;
    }

    public List<PictureInfoBean> getPictureInfoList() {
        return pictureInfoList;
    }

    public void setPictureInfoList(List<PictureInfoBean> pictureInfoList) {
        this.pictureInfoList = pictureInfoList;
    }

    @Override
    public String toString() {
        return "DiaryInfoBean{" +
                "diaryId='" + diaryId + '\'' +
                ", diaryUserId='" + diaryUserId + '\'' +
                ", diaryContent='" + diaryContent + '\'' +
                ", diaryImageNum=" + diaryImageNum +
                ", diaryWeather='" + diaryWeather + '\'' +
                ", diaryAddress='" + diaryAddress + '\'' +
                ", diaryOrder='" + diaryOrder + '\'' +
                ", diaryIsMoreOne=" + diaryIsMoreOne +
                ", createDatetime='" + createDatetime + '\'' +
                ", updateDatetime='" + updateDatetime + '\'' +
                ", fileInfoList=" + fileInfoList.toString() +
                ", pictureInfoList=" + pictureInfoList.toString() +
                '}';
    }
}
