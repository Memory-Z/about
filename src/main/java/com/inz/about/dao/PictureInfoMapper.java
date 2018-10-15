package com.inz.about.dao;

import com.inz.about.model.PictureInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureInfoMapper {
    int deleteByPrimaryKey(String pictureId);

    int insert(PictureInfo record);

    int insertSelective(PictureInfo record);

    PictureInfo selectByPrimaryKey(String pictureId);

    int updateByPrimaryKeySelective(PictureInfo record);

    int updateByPrimaryKey(PictureInfo record);
}