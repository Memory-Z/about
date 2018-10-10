package com.inz.about.service;

import com.inz.about.model.PictureInfo;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 15:04
 **/
public interface IPictureInfoService {
    boolean insert(PictureInfo pictureInfo);

    boolean deleteById(String pictureId);

    boolean update(PictureInfo pictureInfo);

    PictureInfo findById(String pictureId);

}
