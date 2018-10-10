package com.inz.about.service;

import com.inz.about.model.DiaryPicture;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 15:42
 **/
public interface IDiaryPictureService {
    boolean insert(DiaryPicture diaryPicture);

    boolean deleteById(String diaryPictureId);

    boolean update(DiaryPicture diaryPicture);

    DiaryPicture findById(String diaryPictureId);

    List<DiaryPicture> findByDiaryId(String diaryId, int start, int pageSize);
}
