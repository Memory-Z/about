package com.inz.about.model;

import java.util.Date;

public class TempEmail {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.email_id
     *
     * @mbggenerated
     */
    private String emailId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.key
     *
     * @mbggenerated
     */
    private String key;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.send_time
     *
     * @mbggenerated
     */
    private Date sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.enable
     *
     * @mbggenerated
     */
    private String enable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column temp_email.change_time
     *
     * @mbggenerated
     */
    private Date changeTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.email_id
     *
     * @return the value of temp_email.email_id
     *
     * @mbggenerated
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.email_id
     *
     * @param emailId the value for temp_email.email_id
     *
     * @mbggenerated
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId == null ? null : emailId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.user_id
     *
     * @return the value of temp_email.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.user_id
     *
     * @param userId the value for temp_email.user_id
     *
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.email
     *
     * @return the value of temp_email.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.email
     *
     * @param email the value for temp_email.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.key
     *
     * @return the value of temp_email.key
     *
     * @mbggenerated
     */
    public String getKey() {
        return key;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.key
     *
     * @param key the value for temp_email.key
     *
     * @mbggenerated
     */
    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.send_time
     *
     * @return the value of temp_email.send_time
     *
     * @mbggenerated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.send_time
     *
     * @param sendTime the value for temp_email.send_time
     *
     * @mbggenerated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.enable
     *
     * @return the value of temp_email.enable
     *
     * @mbggenerated
     */
    public String getEnable() {
        return enable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.enable
     *
     * @param enable the value for temp_email.enable
     *
     * @mbggenerated
     */
    public void setEnable(String enable) {
        this.enable = enable == null ? null : enable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column temp_email.change_time
     *
     * @return the value of temp_email.change_time
     *
     * @mbggenerated
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column temp_email.change_time
     *
     * @param changeTime the value for temp_email.change_time
     *
     * @mbggenerated
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}