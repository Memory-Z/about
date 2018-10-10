package com.inz.about.service.impl;

import com.inz.about.dao.DiaryFileMapper;
import com.inz.about.model.DiaryFile;
import com.inz.about.service.IDiaryFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 11:13
 **/
@Service(value = "diaryFileService")
public class DiaryFileServiceImpl implements IDiaryFileService {
    @Resource
    private DiaryFileMapper diaryFileMapper;

    @Override
    public boolean insert(DiaryFile diaryFile) {
        int num = diaryFileMapper.insert(diaryFile);
        return num != 0;
    }

    @Override
    public boolean deleteById(String diaryFileId) {
        int num = diaryFileMapper.deleteByPrimaryKey(diaryFileId);
        return num != 0;
    }

    @Override
    public boolean update(DiaryFile diaryFile) {
        int num = diaryFileMapper.updateByPrimaryKey(diaryFile);
        return num != 0;
    }

    @Override
    public DiaryFile findById(String diaryFileId) {
        return diaryFileMapper.selectByPrimaryKey(diaryFileId);
    }

    @Override
    public List<DiaryFile> findByFileId(String fileId) {
        return diaryFileMapper.findByFileId(fileId);
    }

    @Override
    public List<DiaryFile> findByDiaryId(String diaryId) {
        return diaryFileMapper.findByDiaryId(diaryId);
    }
}
