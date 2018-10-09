package com.inz.about.service.impl;

import com.inz.about.dao.TempEmailMapper;
import com.inz.about.model.TempEmail;
import com.inz.about.service.ITempEmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/9 11:35
 **/
@Service(value = "tempEmailService")
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
}
