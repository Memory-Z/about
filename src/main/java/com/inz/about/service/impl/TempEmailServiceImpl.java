package com.inz.about.service.impl;

import com.inz.about.dao.TempEmailMapper;
import com.inz.about.model.TempEmail;
import com.inz.about.service.ITempEmailService;
import com.inz.about.util.BaseUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/9 11:35
 **/
@Service("tempEmailService")
public class TempEmailServiceImpl implements ITempEmailService {
    @Resource
    private TempEmailMapper tempEmailMapper;

    @Override
    public boolean insertTempEmail(TempEmail tempEmail) {
        int num = 0;
        if (tempEmail != null) {
            num = tempEmailMapper.insert(tempEmail);
        }
        return num == 1;
    }

    @Override
    public boolean deleteById(String tempEmailId) {
        int num = tempEmailMapper.deleteByPrimaryKey(tempEmailId);
        return num != 0;
    }

    @Override
    public boolean update(TempEmail tempEmail) {
        int num = 0;
        if (tempEmail != null) {
            num = tempEmailMapper.updateByPrimaryKey(tempEmail);
        }
        return num != 0;
    }

    @Override
    public TempEmail findByEmail(String email) {
        if (!BaseUtil.isEmpty(email)) {
            return tempEmailMapper.findByEmail(email);
        }
        return null;
    }
}
