package com.inz.about.controller;

import com.alibaba.fastjson.JSONObject;
import com.inz.about.model.UserInfo;
import com.inz.about.model.api.ApiTemp1;
import com.inz.about.model.api.ApiUserInfo;
import com.inz.about.service.IUserInfoService;
import com.inz.about.util.BaseUtil;
import com.inz.about.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Api 接口
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/11 10:13
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/api/", produces = {"html/text;charset=utf-8;", "application/json;"})
public class ApiController {
    /**
     * Api模板一
     */
    private ApiTemp1 apiTemp1;
    /**
     * ApiCode 默认失败
     */
    private int apiCode = Constants.API_CODE.FAILED.getValue();
    /**
     * ApiMessage 默认为空
     */
    private String apiMessage = "";


    private final IUserInfoService userInfoService;

    @Autowired
    public ApiController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(value = "login")
    public String login(HttpServletRequest request) {
        initApiData();
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        if (BaseUtil.isEmpty(userName) && BaseUtil.isEmpty(userEmail)) {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "账号不能为空！";
        } else {
            UserInfo userInfo = userInfoService.login(userName, userEmail, password);
        }
        ApiUserInfo apiUserInfo = new ApiUserInfo();
        return resultJson1(apiUserInfo);
    }

    /**
     * 初始化 Api返回数据
     */
    private void initApiData() {
        apiCode = Constants.API_CODE.FAILED.getValue();
        apiMessage = "";
    }

    /**
     * 获取JSON结果
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 结果
     */
    private <T> String resultJson1(T data) {
        ApiTemp1<T> apiTemp1 = new ApiTemp1<>();
        apiTemp1.setCode(apiCode);
        apiTemp1.setMessage(apiMessage);
        apiTemp1.setTempType(1);
        apiTemp1.setData(data);
        JSONObject resultJson = (JSONObject) JSONObject.toJSON(apiTemp1);
        return resultJson.toJSONString();
    }
}
