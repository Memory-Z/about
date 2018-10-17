package com.inz.about.controller;

import com.alibaba.fastjson.JSONObject;
import com.inz.about.model.*;
import com.inz.about.model.api.*;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Api 接口
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/11 10:13
 **/
@Controller
@ResponseBody
@RequestMapping(value = "/api/", produces = {"html/text;charset=utf-8;", "application/json;"}, method = RequestMethod.POST)
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
     * 用户注册：register
     *
     * @param request HttpServletRequest
     * @return JSON
     */
    @RequestMapping(value = "register")
    public String register(HttpServletRequest request) {
        initApiData();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        boolean isRegister = false;
        if (BaseUtil.isEmpty(userName) || BaseUtil.isEmpty(password)) {
            apiCode = Constants.API_CODE.WARN.getValue();
            apiMessage = "传入的参数不完整";
        } else {
            // 是否存在同一用户名
            boolean haveUserInfo = false;
            if (!BaseUtil.isEmpty(userName)) {
                UserInfo userInfo = userInfoService.findByUsername(userName);
                if (userInfo != null) {
                    haveUserInfo = true;
                }
            }
            if (!haveUserInfo) {
                String userId = BaseUtil.getRandomUUID();
                Date createDate = BaseUtil.getSystemDate();
                UserInfo userInfo = new UserInfo();
                userInfo.setUserId(userId);
                userInfo.setUsername(userName);
                userInfo.setPassword(password);
                userInfo.setUserEnable("1");
                // 0 普通用户；1 管理员
                userInfo.setUserType("0");
                userInfo.setUserSex("1");
                Date birthdayDate = BaseUtil.getSystemDate();
                try {
                    birthdayDate = BaseUtil.getDate("1990-01-01 00:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                userInfo.setUserBirthday(birthdayDate);
                userInfo.setCreateDatetime(createDate);
                isRegister = userInfoService.register(userInfo);
                if (isRegister) {
                    apiCode = Constants.API_CODE.SUCCESS.getValue();
                    apiMessage = "用户注册成功";
                } else {
                    apiCode = Constants.API_CODE.FAILED.getValue();
                    apiMessage = "用户注册失败";
                }
            } else {
                apiCode = Constants.API_CODE.FAILED.getValue();
                apiMessage = "用户名已存在";
            }
        }
        return resultJson1(isRegister);
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
     * 设置用户 头像
     *
     * @param fileRequest MultipartHttpServletRequest
     * @param userId      用户ID
     * @return JSON
     */
    @RequestMapping(value = "{userId}/photo")
    public String updateUserPhoto(MultipartHttpServletRequest fileRequest, @PathVariable("userId") String userId) {
        initApiData();
        // 是否更新成功
        boolean isUpdate = false;
        MultipartFile multipartFile = fileRequest.getFile("userPhoto");
        if (!BaseUtil.isEmpty(userId)) {
            UserInfo userInfo = userInfoService.findById(userId);
            if (userInfo != null && multipartFile != null) {
                String filePath = Constants.getBaseFilePath() + "photo" + File.separator;
                Map<String, Object> map = getFileInfo(multipartFile, filePath);
                String realName = (String) map.get("fileRealName");
                File file = (File) map.get("file");
                if (file != null) {
                    boolean flag = saveFileToSql(file, Constants.FILE_OPERATION_MODE.PHOTO, userId, "", realName);
                    if (flag) {
                        isUpdate = true;
                        logger.info("图片文件 保存成功");
                    } else {
                        logger.info("图片文件 保存失败");
                    }
                } else {
                    logger.info("文件保存失败");
                }
            }
        }
        if (isUpdate) {
            apiCode = Constants.API_CODE.SUCCESS.getValue();
            apiMessage = "用户头像更新完成";
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "用户头像更新失败";
        }
        return resultJson1(isUpdate);
    }

    /**
     * 用户信息更新
     *
     * @param request HttpServletRequest
     * @param userId  用户ID
     * @return JSON
     */
    @RequestMapping(value = "{userId}/update")
    public String updateUser(HttpServletRequest request, @PathVariable("userId") String userId) {
        initApiData();
        String userEmail = request.getParameter("userEmail");
        String userSex = request.getParameter("userSex");
        String createDateStr = request.getParameter("createDate");
        String userIntro = request.getParameter("userIntro");
        String userMemo = request.getParameter("userMemo");
        String userPhone = request.getParameter("userPhone");
        ApiUserInfo apiUserInfo = null;
        UserInfo userInfo = userInfoService.findById(userId);
        if (!BaseUtil.isEmpty(userEmail)) {
            userInfo.setUserEmail(userEmail);
        }
        if (!BaseUtil.isEmpty(userSex)) {
            userInfo.setUserSex(userSex);
        }
        if (!BaseUtil.isEmpty(createDateStr)) {
            try {
                Date birthdayDate = BaseUtil.getDate("");
                userInfo.setUserBirthday(birthdayDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (!BaseUtil.isEmpty(userIntro)) {
            userInfo.setUserIntro(userIntro);
        }
        if (!BaseUtil.isEmpty(userMemo)) {
            userInfo.setUserMemo(userMemo);
        }
        if (!BaseUtil.isEmpty(userPhone)) {
            userInfo.setUserPhone(userPhone);
        }
        Date updateTime = BaseUtil.getSystemDate();
        userInfo.setUpdateDatetime(updateTime);
        boolean flag = userInfoService.update(userInfo);
        if (flag) {
            UserFile userFile = userFileService.findEnableUserPhotoByUserId(userId);
            String userPhotoUrl = "";
            if (userFile != null) {
                String fileId = userFile.getFileId();
                FileInfo fileInfo = fileInfoService.findById(fileId);
                userPhotoUrl = fileInfo.getFileUrl();
            }
            apiUserInfo = ModelUtil.userInfoToApi(userInfo, userPhotoUrl);
            apiCode = Constants.API_CODE.SUCCESS.getValue();
            apiMessage = "用户信息更新成功";
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "用户信息更新失败";
        }
        return resultJson1(apiUserInfo);
    }

    @RequestMapping(value = "{userId}/password")
    public String updateUserPassword(HttpServletRequest request, @PathVariable("userId") String userId) {
        initApiData();
        boolean isUpdate = false;
        UserInfo userInfo = userInfoService.findById(userId);
        if (userInfo != null) {

        } else {

        }
        return resultJson1(null);
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
        if (!BaseUtil.isEmpty(startStr)) {
            try {
                start = Integer.parseInt(startStr);
            } catch (NumberFormatException e) {
                logger.error("开始项转换失败", e);
            }
        }
        if (!BaseUtil.isEmpty(pageSizeStr)) {
            try {
                pageSize = Integer.getInteger(pageSizeStr);
            } catch (NumberFormatException e) {
                logger.error("转换页面大小，出错", e);
            }
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
                    List<ApiPictureInfo> apiPictureInfoList = new ArrayList<>();
                    if (haveImage) {
                        apiPictureInfoList = getPictureInfoByDiaryId(diaryId);
                        diaryPictureNum = apiPictureInfoList.size();
                    }
                    List<ApiFileInfo> apiFileInfoList = getFileInfoByDiaryId(diaryId);
                    apiDiaryInfo.setDiaryImageNum(diaryPictureNum);
                    apiDiaryInfo.setPictureInfoList(apiPictureInfoList);
                    apiDiaryInfo.setFileInfoList(apiFileInfoList);
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
                // 获取文件信息
                apiFileInfoList = getApiFileInfoListByDiaryFileList(diaryFileList);
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

    /**
     * 日志添加
     *
     * @param request     HttpServletRequest
     * @param userId      用户ID
     * @param fileRequest 文件
     * @return JSON
     */
    @RequestMapping(value = "{userId}/diary/add")
    public String addDiaryByUserId(HttpServletRequest request, @PathVariable("userId") String userId, MultipartHttpServletRequest fileRequest) {
        initApiData();
        // 日志内容
        String diaryContent = request.getParameter("diaryContent");
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
            int order = diaryInfoService.findLastOrder(userId);
            BigDecimal diaryOrder = new BigDecimal(order + 1);
            diaryInfo.setDiaryOrder(diaryOrder);
            diaryInfo.setCreateDatetime(createTime);
            if (multipartFileList.size() > 0) {
                diaryInfo.setDiaryHaveImage("1");
                // 获取文件保存路路径
                String filePath = Constants.getBaseFilePath() + "diary" + File.separator;
                List<Map<String, Object>> fileList = saveMultipartFile(multipartFileList, filePath);
                if (fileList != null && fileList.size() > 0) {
                    for (Map<String, Object> map : fileList) {
                        String realName = (String) map.get("fileRealName");
                        File file = (File) map.get("file");
                        if (file != null) {
                            boolean flag = saveFileToSql(file, Constants.FILE_OPERATION_MODE.PICTURE, userId, diaryId, realName);
                            if (flag) {
                                logger.info("图片文件 保存成功");
                            } else {
                                logger.warn("图片文件 保存失败");
                            }
                        } else {
                            logger.info("文件保存失败");
                        }
                    }
                }
            } else {
                diaryInfo.setDiaryHaveImage("0");
            }
            boolean flag = diaryInfoService.insert(diaryInfo);
            if (flag) {
                apiCode = Constants.API_CODE.SUCCESS.getValue();
                apiMessage = "日志发布成功";
            } else {
                apiCode = Constants.API_CODE.FAILED.getValue();
                apiMessage = "日志保存失败";
            }
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "日志发布失败，日志内容为空";
        }
        return resultJson1(diaryInfo);
    }

    /**
     * 日志删除
     *
     * @param userId  用户ID
     * @param diaryId 日志ID
     * @return JSON
     */
    @RequestMapping(value = "{userId}/diary/{diaryId}/delete")
    public String deleteDiaryByUserId(@PathVariable("userId") String userId, @PathVariable("diaryId") String diaryId) {
        initApiData();
        // 是否删除
        boolean isDelete = false;
        if (!BaseUtil.isEmpty(userId) && !BaseUtil.isEmpty(diaryId)) {
            DiaryInfo diaryInfo = diaryInfoService.findById(diaryId);
            if (diaryInfo != null) {
                String diaryUserId = diaryInfo.getDiaryUserId();
                if (diaryUserId.equals(userId)) {
                    diaryInfo.setDiaryEnable("0");
                    Date updateTime = BaseUtil.getSystemDate();
                    diaryInfo.setUpdateDatetime(updateTime);
                    String haveImage = diaryInfo.getDiaryHaveImage();
                    if ("1".equals(haveImage)) {
                        List<DiaryFile> diaryFileList = diaryFileService.findByDiaryId(diaryId);
                        if (diaryFileList != null && diaryFileList.size() > 0) {
                            for (DiaryFile diaryFile : diaryFileList) {
                                diaryFile.setEnable("0");
                                diaryFile.setUpdateDatetime(updateTime);
                                boolean flag = diaryFileService.update(diaryFile);
                                if (!flag) {
                                    logger.warn("日志文件删除失败： " + diaryFile.getMapperId());
                                }
                            }
                        }
                    }
                    isDelete = diaryInfoService.update(diaryInfo);
                }
            }
        }
        if (isDelete) {
            apiCode = Constants.API_CODE.SUCCESS.getValue();
            apiMessage = "日志文件删除成功";
        } else {
            apiCode = Constants.API_CODE.FAILED.getValue();
            apiMessage = "日志文件删除失败";
        }
        return resultJson1(isDelete);
    }

    /**
     * 通过日志ID 获取图片信息
     *
     * @param diaryId 日志Id
     * @return 图片信息 List<PictureInfo>
     */
    private List<ApiPictureInfo> getPictureInfoByDiaryId(String diaryId) {
        List<ApiPictureInfo> pictureInfoList = new ArrayList<>();
        List<DiaryPicture> diaryPictureList = diaryPictureService.findByDiaryId(diaryId, 0, 9);
        if (diaryPictureList != null && diaryPictureList.size() > 0) {
            for (DiaryPicture diaryPicture : diaryPictureList) {
                String pictureId = diaryPicture.getPictureId();
                PictureInfo pictureInfo = pictureInfoService.findById(pictureId);
                // 不为空
                if (pictureInfo != null) {
                    ApiPictureInfo apiPictureInfo = ModelUtil.pictureInfoToApi(pictureInfo);
                    pictureInfoList.add(apiPictureInfo);
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
    private List<ApiFileInfo> getFileInfoByDiaryId(String diaryId) {
        List<ApiFileInfo> fileInfoList = new ArrayList<>();
        List<DiaryFile> diaryFileList = diaryFileService.findByDiaryId(diaryId);
        if (diaryFileList != null && diaryFileList.size() > 0) {
            // 获取文件信息
            fileInfoList = getApiFileInfoListByDiaryFileList(diaryFileList);
        }
        return fileInfoList;
    }

    /**
     * 文件保存到实际路径
     *
     * @param multipartFileList 文件
     * @param filePath          文件地址
     * @return 文件列表
     */
    private List<Map<String, Object>> saveMultipartFile(List<MultipartFile> multipartFileList, String filePath) {
        List<Map<String, Object>> fileList = new ArrayList<>();
        int i = 0;
        for (MultipartFile multipartFile : multipartFileList) {
            Map<String, Object> map = new HashMap<>();
            i++;
            if (multipartFile != null && !multipartFile.isEmpty()) {
                // 文件信息获取 保存
                map = getFileInfo(multipartFile, filePath);
            } else {
                logger.warn("文件获取失败, =： " + i);
                map.put("fileRealName", "");
                map.put("file", null);
            }
            fileList.add(map);
        }
        logger.info("文件处理完成, =： " + i);
        return fileList;
    }

    /**
     * 获取实际 文件信息，并保存至本地
     *
     * @param multipartFile 文件
     * @param filePath      文件路径
     * @return 映射关系
     */
    private Map<String, Object> getFileInfo(MultipartFile multipartFile, String filePath) {
        Map<String, Object> map = new HashMap<>();
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
        String fileName = BaseUtil.getRandomUUID() + fileExt;
        filePath = filePath + fileName;
        logger.info("文件存放路径： " + filePath);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            boolean flag = file.getParentFile().mkdirs();
            logger.info("文件创建状态： " + flag);
        }
        try {
            multipartFile.transferTo(file);
            map.put("fileRealName", originalFilename);
            map.put("file", file);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("fileRealName", "");
            map.put("file", null);
        }
        return map;
    }

    /**
     * 保存文件到 SQL 数据库中
     *
     * @param file         文件
     * @param mode         操作类型
     * @param userId       用户ID
     * @param fileRealName 文件真实名字
     * @return 是否保存成功
     */
    @SuppressWarnings("SameParameterValue")
    private boolean saveFileToSql(File file, Constants.FILE_OPERATION_MODE mode, String userId, String diaryId, String fileRealName) {
        boolean flag = false;
        if (file != null && file.isFile()) {
            String fileName = file.getName();
            String fileExt = fileName.substring(fileName.lastIndexOf("."));
            String filePath = file.getPath();
            switch (mode) {
                case FILE:
                    // 保存文件
                    String fileId = saveFileInfo(fileRealName, fileName, fileExt, filePath, "file", file.length());
                    if (fileId != null) {
                        // 保存用户-文件 映射
                        String diaryFileId = saveDiaryFile(diaryId, fileId, "Api 上传文件");
                        if (!BaseUtil.isEmpty(diaryFileId)) {
                            flag = true;
                        }
//                    } else {
//                        apiCode = Constants.API_CODE.FAILED.getValue();
//                        apiMessage = "文件上传失败";
                    }
                    break;
                case PHOTO:
                    String photoId = saveFileInfo(fileRealName, fileName, fileExt, filePath, "photo", file.length());
                    if (photoId != null) {
                        // 用户-文件 映射
                        String userPhotoId = saveUserFile(userId, photoId, "Api 上传图片");
                        if (!BaseUtil.isEmpty(userPhotoId)) {
                            flag = true;
                        }
//                    } else {
//                        apiCode = Constants.API_CODE.FAILED.getValue();
//                        apiMessage = "头像上传失败";
                    }
                    break;
                case PICTURE:
                    String pictureId = savePictureInfo(filePath, "photo", file.length());
                    if (pictureId != null) {
                        flag = true;
                    }
                    break;
                default:
                    break;
            }
        }
        return flag;
    }

    /**
     * 保存文件信息
     *
     * @param fileRealName 文件实际名
     * @param fileName     文件保存名
     * @param fileExt      文件扩展名
     * @param filePath     文件路径
     * @param type         文件类型
     * @param size         文件大小
     * @return 保存成功，返回当前文件ID，否则，反回 null
     */
    private String saveFileInfo(String fileRealName, String fileName, String fileExt, String filePath, String type, long size) {
        Date createDate = BaseUtil.getSystemDate();
        FileInfo fileInfo = new FileInfo();
        String fileId = BaseUtil.getRandomUUID();
        fileInfo.setFileId(fileId);
        fileInfo.setFileExtension(fileExt);
        fileInfo.setFileEnable("1");
        fileInfo.setFileName(fileName);
        fileInfo.setCreateDatetime(createDate);
        fileInfo.setFileUrl(filePath);
        BigDecimal fileSize = new BigDecimal(size);
        fileInfo.setFileSize(fileSize);
        fileInfo.setFilePath(filePath);
        fileInfo.setFileType(type);
        fileInfo.setFileRealName(fileRealName);
        boolean flag = fileInfoService.insert(fileInfo);
        if (flag) {
            return fileId;
        } else {
            return null;
        }
    }

    /**
     * 保存图片文件
     *
     * @param filePath 文件地址
     * @param type     文件类型
     * @param size     文件大小
     * @return 保存的图片ID
     */
    @SuppressWarnings("SameParameterValue")
    private String savePictureInfo(String filePath, String type, long size) {
        Date createDate = BaseUtil.getSystemDate();
        PictureInfo pictureInfo = new PictureInfo();
        String pictureId = BaseUtil.getRandomUUID();
        pictureInfo.setPictureId(pictureId);
        pictureInfo.setPictureEnable("1");
        pictureInfo.setPictureType(type);
        pictureInfo.setPictureUrl(filePath);
        BigDecimal pictureSize = new BigDecimal(size);
        boolean isWindows = BaseUtil.isWindows();
        boolean isLinux = BaseUtil.isLinux();
        String sysName;
        if (isWindows) {
            sysName = "Windows";
        } else if (isLinux) {
            sysName = "Linux";
        } else {
            sysName = "Other";
        }
        pictureInfo.setPictureSys(sysName);
        pictureInfo.setPictureSize(pictureSize);
        pictureInfo.setPicturePath(filePath);
        pictureInfo.setCreateDatetime(createDate);
        boolean flag = pictureInfoService.insert(pictureInfo);
        if (flag) {
            return pictureId;
        } else {
            return null;
        }
    }

    /**
     * 保存用户-文件 映射关系
     *
     * @param userId     用户ID
     * @param fileId     文件ID
     * @param mapperMemo 备注
     * @return 映射ID
     */
    @SuppressWarnings("SameParameterValue")
    private String saveUserFile(String userId, String fileId, String mapperMemo) {
        if ("Api 上传图片".equals(mapperMemo)) {
            List<UserFile> userFileList = userFileService.findListEnableUserPhotoByUserId(userId);
            if (userFileList != null && userFileList.size() > 0) {
                for (UserFile userFile : userFileList) {
                    userFile.setEnable("0");
                    boolean flag = userFileService.update(userFile);
                    if (!flag) {
                        logger.error("重置用户文件映射关系出错：" + userFile.getMapperId());
                    }
                }
            }
        }
        String userFileId = BaseUtil.getRandomUUID();
        Date createDate = BaseUtil.getSystemDate();
        UserFile userFile = new UserFile();
        userFile.setMapperId(userFileId);
        userFile.setCreateDatetime(createDate);
        userFile.setEnable("1");
        userFile.setUserId(userId);
        userFile.setFileId(fileId);
        userFile.setMapperMemo(mapperMemo);
        int mapperOrder = 0;
        try {
            mapperOrder = userFileService.findLastOrder(userId);
        } catch (Exception e) {
            logger.error("获取用户文件排序出错！：", e);
        }
        mapperOrder += 1;
        BigDecimal order = new BigDecimal(mapperOrder);
        userFile.setMapperOrder(order);
        boolean flag = userFileService.insert(userFile);
        if (flag) {
            return userFileId;
        } else {
            return null;
        }
    }

    /**
     * 保存日志-文件 映射关系
     *
     * @param diaryId    日志ID
     * @param fileId     文件ID
     * @param mapperMemo 备注
     * @return 映射ID
     */
    @SuppressWarnings("SameParameterValue")
    private String saveDiaryFile(String diaryId, String fileId, String mapperMemo) {
        String diaryFileId = BaseUtil.getRandomUUID();
        Date createDate = BaseUtil.getSystemDate();
        DiaryFile diaryFile = new DiaryFile();
        diaryFile.setMapperId(diaryFileId);
        diaryFile.setCreateDatetime(createDate);
        diaryFile.setEnable("1");
        diaryFile.setDiaryId(diaryId);
        diaryFile.setFileId(fileId);
        diaryFile.setMapperMemo(mapperMemo);
        int mapperOrder = 0;
        try {
            mapperOrder = diaryFileService.findLastOrder(diaryId);
        } catch (Exception e) {
            logger.error("获取用户文件排序出错！：", e);
        }
        mapperOrder += 1;
        BigDecimal order = new BigDecimal(mapperOrder);
        diaryFile.setMapperOrder(order);
        boolean flag = diaryFileService.insert(diaryFile);
        if (flag) {
            return diaryFileId;
        } else {
            return null;
        }
    }

    /**
     * 通过日志文件 获取 ApiFileInfo 文件信息
     *
     * @param diaryFileList 日志文件信息
     * @return List:ApiFileInfo
     */
    private List<ApiFileInfo> getApiFileInfoListByDiaryFileList(List<DiaryFile> diaryFileList) {
        List<ApiFileInfo> apiFileInfoList = new ArrayList<>();
        for (DiaryFile diaryFile : diaryFileList) {
            String fileId = diaryFile.getFileId();
            FileInfo fileInfo = fileInfoService.findById(fileId);
            ApiFileInfo apiFileInfo = ModelUtil.fileInfoToApi(fileInfo);
            apiFileInfoList.add(apiFileInfo);
        }
        return apiFileInfoList;
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
