package com.inz.about.dao;

import com.inz.about.model.DiaryInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiaryInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String diaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    int insert(DiaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    int insertSelective(DiaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    DiaryInfo selectByPrimaryKey(String diaryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DiaryInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table diary_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DiaryInfo record);
}