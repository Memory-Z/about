package com.inz.about.service;

import com.inz.about.model.TempEmail;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/9 11:17
 **/
public interface ITempEmailService {
    /**
     * 添加临时邮件
     *
     * @param tempEmail 临时邮件
     * @return 是否插入成功
     */
    boolean insertTempEmail(TempEmail tempEmail);

    boolean deleteById(String tempEmailId);

    boolean update(TempEmail tempEmail);

    TempEmail findByEmail(String email);
}
