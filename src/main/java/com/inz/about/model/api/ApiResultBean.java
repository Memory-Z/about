package com.inz.about.model.api;

import java.io.Serializable;

/**
 * Api 数据传输模板
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/11 13:38
 **/
public class ApiResultBean<T> implements Serializable {
    /**
     * 结果码
     */
    private int code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 模板类型
     */
    private int tempType;
    /**
     * 数据内容
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTempType() {
        return tempType;
    }

    public void setTempType(int tempType) {
        this.tempType = tempType;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResultBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", tempType=" + tempType +
                ", data=" + data +
                '}';
    }
}
