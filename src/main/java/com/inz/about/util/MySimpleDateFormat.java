package com.inz.about.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单时间格式化工具
 *
 * @author 11654
 * @version 1.0.0
 * Create By Zhenglj on 2018/10/2 10:55
 **/
public class MySimpleDateFormat {

    private String dateFormatStr = "yyyy-MM-dd HH:mm:ss";
    private ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    public MySimpleDateFormat(String dateFormatStr) {
        this.dateFormatStr = dateFormatStr;
    }

    /**
     * 获取当前格式
     *
     * @return 实体
     */
    private DateFormat getDateFormat() {
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(dateFormatStr);
            threadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    /**
     * 解析数据
     *
     * @param dateStr 时间格式
     * @return 时间数据类型
     * @throws ParseException 解析异常
     */
    public Date parse(String dateStr) throws ParseException {
        return getDateFormat().parse(dateStr);
    }

    /**
     * 格式化 时间
     *
     * @param date 时间
     * @return 格式化后的数据
     */
    public String format(Date date) {
        return getDateFormat().format(date);
    }

    public String format(Object obj) {
        return getDateFormat().format(obj);
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return getDateFormat().format(date, toAppendTo, fieldPosition);
    }

    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return getDateFormat().format(obj, toAppendTo, fieldPosition);
    }
}
