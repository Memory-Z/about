package com.inz.about.service.impl;

import com.inz.about.dao.DiaryInfoMapper;
import com.inz.about.model.DiaryInfo;
import com.inz.about.service.IDiaryInfoService;
import com.inz.about.util.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/10 10:15
 **/
@Service("diaryInfoService")
public class DiaryInfoServiceImpl implements IDiaryInfoService {
    @Resource
    private DiaryInfoMapper diaryInfoMapper;

    @Override
    public boolean insert(DiaryInfo diaryInfo) {
        int num = diaryInfoMapper.insert(diaryInfo);
        return num != 0;
    }

    @Override
    public boolean deleteById(String diaryInfoId) {
        int num = diaryInfoMapper.deleteByPrimaryKey(diaryInfoId);
        return num != 0;
    }

    @Override
    public boolean update(DiaryInfo diaryInfo) {
        int num = diaryInfoMapper.updateByPrimaryKey(diaryInfo);
        return num != 0;
    }

    @Override
    public DiaryInfo findById(String diaryId) {
        return diaryInfoMapper.selectByPrimaryKey(diaryId);
    }

    @Override
    public int getAllCount() {
        return 0;
    }

    @Override
    public List<DiaryInfo> findByUserId(String userId, int start, int pageSize) {
        return diaryInfoMapper.findByUserId(userId, start, pageSize);
    }

    @Override
    public int findLastOrder(String userId) {
        String orderStr = diaryInfoMapper.findLastOrder(userId);
        int order = 0;
        if (!BaseUtil.isEmpty(orderStr)) {
            try {
                order = Integer.parseInt(orderStr);
            } catch (NumberFormatException e) {
                System.out.println("  DiaryInfoService: FindLastOrder: " + e.getMessage());
            }
        }
        return order;
    }
}
