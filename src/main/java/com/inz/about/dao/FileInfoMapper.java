package com.inz.about.dao;

import com.inz.about.model.FileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileInfoMapper {
    int deleteByPrimaryKey(String fileId);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    FileInfo selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    /**
     * 通过文件类型查询
     *
     * @param fileType 文件类型
     * @param start    开始位置
     * @param pageSize 页面大小
     * @return 文件集合
     */
    List<FileInfo> findByFileType(@Param("fileType") String fileType, @Param("start") int start, @Param("pageSize") int pageSize);
}