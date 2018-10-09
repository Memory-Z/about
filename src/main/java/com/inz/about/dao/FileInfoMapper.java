package com.inz.about.dao;

import com.inz.about.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    int insert(FileInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    int insertSelective(FileInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    FileInfo selectByPrimaryKey(String fileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(FileInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(FileInfo record);
}