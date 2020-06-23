package com.inz.about.util;

import com.alibaba.fastjson.JSONObject;
import com.inz.about.model.api.ApiResultBean;
import com.inz.about.model.api.BaseApiBean;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2019/05/28 13:45
 **/
public class ApiUtil {

    /**
     * 初始化 ApiResultBean 模板
     *
     * @return ApiResultBean
     */
    public static <T> ApiResultBean<T> initApiTemp1Data() {
        ApiResultBean<T> apiResultBean = new ApiResultBean<>();
        apiResultBean.setCode(Constants.API_CODE.FAILED.getValue());
        apiResultBean.setMessage("");
        apiResultBean.setTempType(1);
        return apiResultBean;
    }

    /**
     * 根据 Temp1 获取JSON结果
     *
     * @param apiResultBean ApiResultBean 模板
     * @return 结果
     */
    public static String resultJson4Temp1(ApiResultBean apiResultBean) {
        JSONObject resultJson = (JSONObject) JSONObject.toJSON(apiResultBean);
        return resultJson.toJSONString();
    }

}
