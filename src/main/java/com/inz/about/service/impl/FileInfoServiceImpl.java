package com.inz.about.service.impl;

import com.inz.about.dao.FileInfoMapper;
import com.inz.about.model.FileInfo;
import com.inz.about.service.IFileInfoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 10:39
 **/
@Service(value = "fileInfoService")
public class FileInfoServiceImpl implements IFileInfoService {
    @Resource
    private FileInfoMapper fileInfoMapper;

    @Override
    public boolean insert(@NotNull FileInfo fileInfo) {
        int num = fileInfoMapper.insert(fileInfo);
        return num != 0;
    }

    @Override
    public boolean update(@NotNull FileInfo fileInfo) {
        int num = fileInfoMapper.updateByPrimaryKey(fileInfo);
        return num != 0;
    }

    @Override
    public boolean deleteById(@NotNull String fileId) {
        int num = fileInfoMapper.deleteByPrimaryKey(fileId);
        return num != 0;
    }

    @NotNull
    @Override
    public FileInfo findById(@NotNull String fileId) {
        return fileInfoMapper.selectByPrimaryKey(fileId);
    }

    @NotNull
    @Override
    public List<FileInfo> findByFileType(@NotNull String fileType, int start, int pageSize) {
        return fileInfoMapper.findByFileType(fileType, start, pageSize);
    }
}
