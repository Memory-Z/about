package com.inz.about.controller;

import com.alibaba.fastjson.JSONObject;
import com.inz.about.model.*;
import com.inz.about.model.api.ApiDiaryInfo;
import com.inz.about.model.api.ApiFileInfo;
import com.inz.about.model.api.ApiTemp1;
import com.inz.about.model.api.ApiUserInfo;
import com.inz.about.service.*;
import com.inz.about.util.BaseUtil;
import com.inz.about.util.Constants;
import com.inz.about.util.ModelUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
     * ApiCode 默认失败
     */
    private int apiCode = Constants.API_CODE.FAILED.getValue();
    /**
     * ApiMessage 默认为空
     */
    private String apiMessage = "";
    private static Logger logger = LogManager.getLogger(ApiController.class.getName());


    private final IUserInfoService userInfoService;
    private final IUserFileService userFileService;
    private final IFileInfoService fileInfoService;
    private final IDiaryInfoService diaryInfoService;
    private final IDiaryFileService diaryFileService;
    private final IDiaryPictureService diaryPictureService;
    private final IPictureInfoService pictureInfoService;

    @Autowired
    public ApiController(IUserInfoService userInfoService, IUserFileService userFileService,
                         IFileInfoService fileInfoService, IDiaryInfoService diaryInfoService,
                         IDiaryFileService diaryFileService, IDiaryPictureService diaryPictureService,
                         IPictureInfoService pictureInfoService) {
        this.userInfoService = userInfoService;
        this.userFileService = userFileService;
        this.fileInfoService = fileInfoService;
        this.diaryInfoService = diaryInfoService;
        this.diaryFileService = diaryFileService;
        this.diaryPictureService = diaryPictureService;
        this.pictureInfoService = pictureInfoService;
    }

    /**
     * 用户登录
     *
     * @param request HttpServletRequest
     * @return JSON
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest request) {
        initApiData();
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        ApiUserInfo apiUserInfo = null;
        if (BaseUtil.isEmpty(userName) && BaseUtil.isEmpty(userEmail)) {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "账号不能为空！";
        } else {
            UserInfo userInfo = userInfoService.login(userName, userEmail, password);
            if (userInfo != null) {
                String userId = userInfo.getUserId();
                UserFile userFile = userFileService.findEnableUserPhotoByUserId(userId);
                String userPhotoUrl = "";
                if (userFile != null) {
                    String fileId = userFile.getFileId();
                    FileInfo fileInfo = fileInfoService.findById(fileId);
                    userPhotoUrl = fileInfo.getFileUrl();
                }
                apiUserInfo = ModelUtil.userInfoToApi(userInfo, userPhotoUrl);
                apiCode = Constants.API_CODE.SUCCESS.getValue();
                apiMessage = "登录成功";
            } else {
                apiCode = Constants.API_CODE.FAILED.getValue();
                apiMessage = "账号或密码不正确";
            }
        }
        return resultJson1(apiUserInfo);
    }

    /**
     * 通过用户 获取日志
     *
     * @param request HttpServletRequest
     * @param userId  用户ID
     * @return JSON
     */
    @RequestMapping(value = "{userId}/diary")
    public String getDiaryByUserId(HttpServletRequest request, @PathVariable("userId") String userId) {
        initApiData();
        String startStr = request.getParameter("start");
        String pageSizeStr = request.getParameter("pageSize");
        int start = 0, pageSize = 10;
        try {
            start = Integer.getInteger(startStr);
            pageSize = Integer.getInteger(pageSizeStr);
        } catch (NumberFormatException e) {
            logger.error("转换获取页号及页面大小，出错", e);
        }
        // 需要返回的数据
        List<ApiDiaryInfo> apiDiaryInfoList = new ArrayList<>();
        // 查询的日志数据
        List<DiaryInfo> diaryInfoList = diaryInfoService.findByUserId(userId, start, pageSize);
        if (diaryInfoList != null && diaryInfoList.size() > 0) {
            for (DiaryInfo diaryInfo : diaryInfoList) {
                ApiDiaryInfo apiDiaryInfo = ModelUtil.diaryInfoToApi(diaryInfo);
                if (apiDiaryInfo != null) {
                    String diaryId = diaryInfo.getDiaryId();
                    String haveImageStr = diaryInfo.getDiaryHaveImage();
                    boolean haveImage = false;
                    // 0： 无 1： 有
                    if ("1".equals(haveImageStr)) {
                        haveImage = true;
                    }
                    int diaryPictureNum = 0;
                    List<PictureInfo> pictureInfoList = new ArrayList<>();
                    if (haveImage) {
                        pictureInfoList = getPictureInfoByDiaryId(diaryId);
                        diaryPictureNum = pictureInfoList.size();
                    }
                    List<FileInfo> fileInfoList = getFileInfoByDiaryId(diaryId);
                    apiDiaryInfo.setDiaryImageNum(diaryPictureNum);
                    apiDiaryInfo.setPictureInfoList(pictureInfoList);
                    apiDiaryInfo.setFileInfoList(fileInfoList);
                    apiDiaryInfoList.add(apiDiaryInfo);
                }
            }
            apiCode = Constants.API_CODE.SUCCESS.getValue();
            apiMessage = "日志信息获取成功";
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            if (start == 0) {
                apiMessage = "当前用户无日志信息";
            } else {
                apiMessage = "用户日志信息已全部获取";
            }
        }
        return resultJson1(apiDiaryInfoList);
    }

    /**
     * 查询某个日志下的文件信息
     *
     * @param userId  用户Id
     * @param diaryId 日志Id
     * @return JSON
     */
    @RequestMapping(value = "{userId}/diary/{diaryId}/file")
    public String getDiaryFileByUserId(@PathVariable("userId") String userId,
                                       @PathVariable("diaryId") String diaryId) {
        initApiData();
        List<ApiFileInfo> apiFileInfoList = new ArrayList<>();
        // 是否相符< 用户Id 和 日志Id 进行 校验>
        boolean isConform = false;
        DiaryInfo diaryInfo = diaryInfoService.findById(diaryId);
        if (diaryInfo != null) {
            String uId = diaryInfo.getDiaryUserId();
            // 相符
            if (userId.equals(uId)) {
                isConform = true;
            }
        }
        if (isConform) {
            List<DiaryFile> diaryFileList = diaryFileService.findByDiaryId(diaryId);
            if (diaryFileList != null && diaryFileList.size() > 0) {
                for (DiaryFile diaryFile : diaryFileList) {
                    String fileId = diaryFile.getFileId();
                    FileInfo fileInfo = fileInfoService.findById(fileId);
                    ApiFileInfo apiFileInfo = ModelUtil.fileInfoToApi(fileInfo);
                    apiFileInfoList.add(apiFileInfo);
                }
                apiCode = Constants.API_CODE.SUCCESS.getValue();
                apiMessage = "日志文件获取成功";
            } else {
                apiCode = Constants.API_CODE.FAILED.getValue();
                apiMessage = "日志文件信息获取失败";
            }
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "当前用户不存在此日志";
        }
        return resultJson1(apiFileInfoList);
    }

    @RequestMapping(value = "{userId}/diary/add")
    public String addDiaryByUserId(HttpServletRequest request, @PathVariable("userId") String userId, MultipartHttpServletRequest fileRequest) {
        initApiData();
        // 日志内容
        String diaryContent = request.getParameter("diaryContent");
        diaryContent = diaryContent.trim();
        // 天气
        String diaryWeather = request.getParameter("diaryWeather");
        // 地址
        String diaryAddress = request.getParameter("diaryAddress");
        // 获取日志图片
        List<MultipartFile> multipartFileList = fileRequest.getFiles("diaryPhoto");
        DiaryInfo diaryInfo = null;
        if (!BaseUtil.isEmpty(diaryContent)) {
            diaryInfo = new DiaryInfo();
            Date createTime = BaseUtil.getSystemDate();
            String diaryId = BaseUtil.getRandomUUID();
            diaryInfo.setDiaryId(diaryId);
            diaryInfo.setDiaryUserId(userId);
            diaryInfo.setDiaryContent(diaryContent);
            diaryInfo.setDiaryAddress(diaryAddress);
            diaryInfo.setDiaryWeather(diaryWeather);
            diaryInfo.setDiaryEnable("1");
            diaryInfo.setDiaryOrder("1");
            diaryInfo.setCreateDatetime(createTime);
            if (multipartFileList.size() > 0) {
                diaryInfo.setDiaryHaveImage("1");

            } else {
                diaryInfo.setDiaryHaveImage("0");
            }
            apiCode = Constants.API_CODE.SUCCESS.getValue();
            apiMessage = "日志发布成功";
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "日志发布失败，日志内容为空";
        }
        return resultJson1(diaryInfo);
    }

    /**
     * 通过日志ID 获取图片信息
     *
     * @param diaryId 日志Id
     * @return 图片信息 List<PictureInfo>
     */
    private List<PictureInfo> getPictureInfoByDiaryId(String diaryId) {
        List<PictureInfo> pictureInfoList = new ArrayList<>();
        List<DiaryPicture> diaryPictureList = diaryPictureService.findByDiaryId(diaryId, 0, 9);
        if (diaryPictureList != null && diaryPictureList.size() > 0) {
            for (DiaryPicture diaryPicture : diaryPictureList) {
                String pictureId = diaryPicture.getPictureId();
                PictureInfo pictureInfo = pictureInfoService.findById(pictureId);
                // 不为空
                if (pictureInfo != null) {
                    pictureInfoList.add(pictureInfo);
                }
            }
        }
        return pictureInfoList;
    }

    /**
     * 通过日志Id 查询文件信息
     *
     * @param diaryId 日志ID
     * @return 文件信息
     */
    private List<FileInfo> getFileInfoByDiaryId(String diaryId) {
        List<FileInfo> fileInfoList = new ArrayList<>();
        List<DiaryFile> diaryFileList = diaryFileService.findByDiaryId(diaryId);
        if (diaryFileList != null && diaryFileList.size() > 0) {
            for (DiaryFile diaryFile : diaryFileList) {
                String fileId = diaryFile.getFileId();
                FileInfo fileInfo = fileInfoService.findById(fileId);
                fileInfoList.add(fileInfo);
            }
        }
        return fileInfoList;
    }

    /**
     * 文件保存
     *
     * @param multipartFileList 文件
     * @param type              文件类型
     * @param filePath          文件地址
     */
    private void saveMultipartFile(List<MultipartFile> multipartFileList, Constants.FILE_OPERATION_MODE type, String filePath) {
        BufferedOutputStream stream;
        int i = 0;
        for (MultipartFile multipartFile : multipartFileList) {
            i++;
            if (multipartFile != null && !multipartFile.isEmpty()) {
                try {
                    byte[] bytes = multipartFile.getBytes();
                    // 获取原文件名
                    String originalFilename = multipartFile.getOriginalFilename();
                    logger.info("originalFilename: " + originalFilename);
                    int extIndexOf = -1;
                    if (originalFilename != null) {
                        extIndexOf = originalFilename.lastIndexOf(".");
                    }
                    // 截取文件扩展名
                    String fileExt;
                    if (extIndexOf != -1) {
                        fileExt = originalFilename.substring(extIndexOf);
                    } else {
                        fileExt = "";
                    }
                    // 文件需要存放的路径
                    filePath = filePath + File.separator + BaseUtil.getRandomUUID() + fileExt;
                    logger.info("文件存放路径： " + filePath);
                    File file = new File(filePath);
                    stream = new BufferedOutputStream(new FileOutputStream(file));
                    stream.write(bytes);
                    stream.close();
                    switch (type) {
                        case FILE:
                            break;
                        case PHOTO:
                            break;
                        case PICTURE:
                            break;
                        default:
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.error("文件获取失败！=：" + i, e);
                }
            } else {
                logger.warn("文件获取失败, =： " + i);
            }
        }
        logger.info("文件处理完成, =： " + i);
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
