package com.inz.about.service.impl;

import com.inz.about.dao.FileInfoMapper;
import com.inz.about.model.FileInfo;
import com.inz.about.service.IFileInfoService;
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
    public boolean insert(FileInfo fileInfo) {
        int num = fileInfoMapper.insert(fileInfo);
        return num != 0;
    }

    @Override
    public boolean update(FileInfo fileInfo) {
        int num = fileInfoMapper.updateByPrimaryKey(fileInfo);
        return num != 0;
    }

    @Override
    public boolean deleteById(String fileId) {
        int num = fileInfoMapper.deleteByPrimaryKey(fileId);
        return num != 0;
    }

    @Override
    public FileInfo findById(String fileId) {
        return fileInfoMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public List<FileInfo> findByFileType(String fileType, int start, int pageSize) {
        return fileInfoMapper.findByFileType(fileType, start, pageSize);
    }
}
