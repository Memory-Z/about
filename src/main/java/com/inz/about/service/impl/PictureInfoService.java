package com.inz.about.service.impl;

import com.inz.about.dao.PictureInfoMapper;
import com.inz.about.model.PictureInfo;
import com.inz.about.service.IPictureInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 15:39
 **/
@Service(value = "pictureInfoService")
public class PictureInfoService implements IPictureInfoService {
    @Resource
    private PictureInfoMapper pictureInfoMapper;

    @Override
    public boolean insert(PictureInfo pictureInfo) {
        int num = pictureInfoMapper.insert(pictureInfo);
        return num != 0;
    }

    @Override
    public boolean deleteById(String pictureId) {
        int num = pictureInfoMapper.deleteByPrimaryKey(pictureId);
        return num != 0;
    }

    @Override
    public boolean update(PictureInfo pictureInfo) {
        int num = pictureInfoMapper.updateByPrimaryKey(pictureInfo);
        return num != 0;
    }

    @Override
    public PictureInfo findById(String pictureId) {
        return pictureInfoMapper.selectByPrimaryKey(pictureId);
    }
}
