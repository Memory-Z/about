package com.inz.about.service;

import com.inz.about.model.DiaryInfo;

import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 9:54
 **/
public interface IDiaryInfoService {
    /**
     * 添加日志信息
     *
     * @param diaryInfo 日志信息
     */
    boolean insert(DiaryInfo diaryInfo);

    boolean deleteById(String diaryInfoId);

    boolean update(DiaryInfo diaryInfo);

    DiaryInfo findById(String diaryId);

    int getAllCount();

    List<DiaryInfo> findByUserId(String userId, int start, int pageSize);

    /**
     * 通过用户ID 查询最后一个排序号
     *
     * @param userId 用户ID
     * @return 排序号
     */
    int findLastOrder(String userId);

}
