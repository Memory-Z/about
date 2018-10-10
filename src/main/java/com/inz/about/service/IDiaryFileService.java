package com.inz.about.service;

import com.inz.about.model.DiaryFile;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 11:04
 **/
public interface IDiaryFileService {
    boolean insert(DiaryFile diaryFile);

    boolean deleteById(String diaryFileId);

    boolean update(DiaryFile diaryFile);

    DiaryFile findById(String diaryFileId);

    List<DiaryFile> findByFileId(String fileId);

    List<DiaryFile> findByDiaryId(String diaryId);
}
