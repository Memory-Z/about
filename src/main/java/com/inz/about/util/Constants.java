package com.inz.about.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 静态数据
 *
 * @author 11654
 * @version 1.0.0
 * Create By ZhenglJ on 2018/10/2 10:51
 **/
public class Constants {

    /**
     * Api 返回码
     */
    public enum API_CODE {
        // 成功
        SUCCESS(10000),
        // 失败
        FAILED(10001),
        // 警告
        WARN(10002),
        // 异常
        THROWS(10003),
        // 错误
        ERROR(10004);

        int value;

        API_CODE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 文件操作方式
     */
    public enum FILE_OPERATION_MODE {
        FILE,
        PICTURE,
        PHOTO;
    }

    /**
     * 每页的记录数
     */
    public static Integer PAGE_SIZE = 10;
    /**
     * 菜单GUID
     */
    public static String GUID = "B56BBCB3-5AF2-41A1-8A5F-E4177525E9CA";

    /**
     * 文件保存路径
     */
    private static String WINDOWS_PATH = "E:\\project\\about";
    private static String LINUX_PATH = "/etc/mnt/project/about/";

    /**
     * 邮箱服务器地址：163是 smtp.163.com；qq是 smtp.qq.com
     */
    public static final String emailHost = "smtp.qq.com";
    /**
     * 邮件发送地址
     */
    public static final String emailFrom = "time-memory-z@qq.com";
    /**
     * 邮件发送者邮箱登陆名
     */
    public static final String emailUserName = "time-memory-z@qq.com";
    /**
     * 邮箱密码
     */
    public static final String emailPassword = "hkjezqmmgtxtjfgi";

    /**
     * 获取 本地路径
     */
    public static String getBaseFilePath() {
        boolean isWin = BaseUtil.isWindows();
        if (isWin) {
            return WINDOWS_PATH;
        } else {
            return LINUX_PATH;
        }
    }

//    /**
//     * 地区 省 集合
//     */
//    public static List<PlaceProvince> PROVINCE_COLLECTION = new ArrayList<>();
//    /**
//     * 地区 地级市集合
//     */
//    public static List<PlaceCity> CITY_COLLECTION = new ArrayList<>();
//    /**
//     * 地区 区/县/县级市 集合
//     */
//    public static List<PlaceRegion> REGION_COLLECTION = new ArrayList<>();
//    /**
//     * 地区 邮编 集合
//     */
//    public static List<PlaceZipcode> ZIPCODE_COLLECTION = new ArrayList<>();
//    /**
//     * 数据字典集合
//     */
//    public static List<Dictionary> DICT_COLLECTIN = new ArrayList<>();
    /**
     * 全局变量
     */
    public static Map<String, Object> GLOBAL_MAP = new HashMap<>();
    /**
     * 项目盘符
     */
    public static String ROOT_PATH;
    /**
     * 项目名称
     */
    public static String PROJECT_NAME;
    /**
     * 资源目录
     */
    public static String RESOURCE;

    static {
        String PROJECT_PATH = System.getProperty("user.dir");
        ROOT_PATH = PROJECT_PATH.split(":")[0];
        PROJECT_NAME = "about";
        RESOURCE = "file";
    }

    /**
     * 课程
     */
    public static String PATH_COURSE = "\\course";
    public static String PATH_COURSE_INFO = PATH_COURSE + "\\base";
    public static String PATH_COURSE_INTRO = PATH_COURSE + "\\intro";
    public static String PATH_COURSE_PHOTO = PATH_COURSE + "\\photo";
    public static String PATH_USER = "\\user";
    public static String PATH_USER_PHOTO = PATH_USER + "\\photo";
    /**
     * 绝对路径：文件地址
     */
    public static String FILE_PATH = ROOT_PATH + ":\\webapps\\" + PROJECT_NAME + "\\file";
    /**
     * 绝对路径：课程文件
     */
    public static String FILE_PATH_COURSE = FILE_PATH + PATH_COURSE;
    /**
     * 绝对路径：课程文件信息
     */
    public static String FILE_PATH_COURSE_INFO = FILE_PATH + PATH_COURSE_INFO;
    /**
     * 绝对路径：课程简介文件
     */
    public static String FILE_PATH_COURSE_INTRO = FILE_PATH + PATH_COURSE_INTRO;
    /**
     * 绝对路径：课程图片
     */
    public static String FILE_PATH_COURSE_PHOTO = FILE_PATH + PATH_COURSE_PHOTO;
    /**
     * 绝对路径：用户文件
     */
    public static String FILE_PATH_USER = FILE_PATH + PATH_USER;
    /**
     * 绝对路径：用户头像文件
     */
    public static String FILE_PATH_USER_PHOTO = FILE_PATH + PATH_USER_PHOTO;

    /**
     * 相对路径
     */
    public static String REL_PATH = "\\file";
    /**
     * 相对路径：课程文件
     */
    public static String REL_PATH_COURSE = REL_PATH + PATH_COURSE;
    /**
     * 相对路径：课程信息文件
     */
    public static String REL_PATH_COURSE_INFO = REL_PATH + PATH_COURSE_INFO;
    /**
     * 相对路径：课程简介文件
     */
    public static String REL_PATH_COURSE_INTRO = REL_PATH + PATH_COURSE_INTRO;
    /**
     * 相对路径：课程缩略图文件
     */
    public static String REL_PATH_COURSE_PHOTO = REL_PATH + PATH_COURSE_PHOTO;
    /**
     * 相对路径：用户文件
     */
    public static String REL_PATH_USER = REL_PATH + PATH_USER;
    /**
     * 相对路径：用户头像文件
     */
    public static String REL_PATH_USER_PHOTO = REL_PATH + PATH_USER_PHOTO;

    // public static String REL_PATH_COURSE = REL_PATH + "";


}
