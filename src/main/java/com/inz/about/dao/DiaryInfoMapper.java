package com.inz.about.dao;

import com.inz.about.model.DiaryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryInfoMapper {
    int deleteByPrimaryKey(String diaryId);

    int insert(DiaryInfo record);

    int insertSelective(DiaryInfo record);

    DiaryInfo selectByPrimaryKey(String diaryId);

    int updateByPrimaryKeySelective(DiaryInfo record);

    int updateByPrimaryKey(DiaryInfo record);

    /**
     * 通过用户ID 查询日志信息
     *
     * @param userId   用户ID
     * @param start    开始页数
     * @param pageSize 页面可见数量
     * @return 日志
     */
    List<DiaryInfo> findByUserId(@Param("userId") String userId, @Param("start") int start, @Param("pageSize") int pageSize);

    /**
     * 获取全部日志信息
     *
     * @param start    开始页数
     * @param pageSize 页面可见数量
     * @return 日志
     */
    List<DiaryInfo> findAll(@Param("start") int start, @Param("pageSize") int pageSize);
}