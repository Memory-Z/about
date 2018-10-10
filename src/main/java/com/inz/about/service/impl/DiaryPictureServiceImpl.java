package com.inz.about.service.impl;

import com.inz.about.dao.DiaryPictureMapper;
import com.inz.about.model.DiaryPicture;
import com.inz.about.service.IDiaryPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 15:46
 **/
@Service(value = "diaryPictureService")
public class DiaryPictureServiceImpl implements IDiaryPictureService {
    @Resource
    private DiaryPictureMapper diaryPictureMapper;

    @Override
    public boolean insert(DiaryPicture diaryPicture) {
        int num = diaryPictureMapper.insert(diaryPicture);
        return num != 0;
    }

    @Override
    public boolean deleteById(String diaryPictureId) {
        int num = diaryPictureMapper.deleteByPrimaryKey(diaryPictureId);
        return num != 0;
    }

    @Override
    public boolean update(DiaryPicture diaryPicture) {
        int num = diaryPictureMapper.updateByPrimaryKey(diaryPicture);
        return num != 0;
    }

    @Override
    public DiaryPicture findById(String diaryPictureId) {
        return diaryPictureMapper.selectByPrimaryKey(diaryPictureId);
    }

    @Override
    public List<DiaryPicture> findByDiaryId(String diaryId, int start, int pageSize) {
        List<DiaryPicture> diaryPictures = diaryPictureMapper.findByDiaryId(diaryId, start, pageSize);
        if (diaryPictures != null && diaryPictures.size() > 0) {
            return diaryPictures;
        }
        return null;
    }
}
