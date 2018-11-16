package com.inz.about.dao;

import com.inz.about.model.TempEmail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempEmailMapper {
    int deleteByPrimaryKey(String emailId);

    int insert(TempEmail record);

    int insertSelective(TempEmail record);

    TempEmail selectByPrimaryKey(String emailId);

    int updateByPrimaryKeySelective(TempEmail record);

    int updateByPrimaryKey(TempEmail record);

    /**
     * 通过 邮箱获取临时邮箱
     *
     * @param email 邮箱号
     * @return 临时邮箱
     */
    TempEmail findByEmail(String email);
}