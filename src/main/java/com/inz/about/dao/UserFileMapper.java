package com.inz.about.dao;

import com.inz.about.model.UserFile;

public interface UserFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String maperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int insert(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int insertSelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    UserFile selectByPrimaryKey(String maperId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_file
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserFile record);
}