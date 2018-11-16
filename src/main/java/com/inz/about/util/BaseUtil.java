package com.inz.about.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {

    /**
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    public static final MySimpleDateFormat FORMAT = new MySimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final MySimpleDateFormat FORMAT_CN = new MySimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    /**
     * 格式：yyyyMMddHHmmss
     */
    public static final MySimpleDateFormat FORMAT_CN_SHORT = new MySimpleDateFormat("yyyyMMddHHmmss");
    /**
     * 格式：yyyy-MM-dd
     */
    public static final MySimpleDateFormat FORMAT_DATE = new MySimpleDateFormat("yyyy-MM-dd");
    /**
     * 格式：yyyy年MM月dd日
     */
    public static final MySimpleDateFormat FORMAT_DATE_CN = new MySimpleDateFormat("yyyy年MM月dd日");
    /**
     * 格式：yyyyMMdd
     */
    public static final MySimpleDateFormat FORMAT_DATE_SHORT = new MySimpleDateFormat("yyyyMMdd");
    /**
     * 格式：HH:mm:ss
     */
    public static final MySimpleDateFormat FORMAT_TIME = new MySimpleDateFormat("HH:mm:ss");
    /**
     * 格式：HHmmss
     */
    public static final MySimpleDateFormat FORMAT_TIME_SHORT = new MySimpleDateFormat("HHmmss");

    /**
     * 获取系统时间
     *
     * @return 时间
     */
    public static Date getSystemDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取系统时间，格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 时间类型字符串
     */
    public static String getSystemDateTimeStr() {
        return FORMAT.format(getSystemDate());
    }

    /**
     * 获取系统时间，格式：yyyy-MM-dd
     *
     * @return 时间类型字符串
     */
    public static String getSystemDateStr() {
        return FORMAT_DATE.format(getSystemDate());
    }

    /**
     * 获取时间的字符串，格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间
     * @return 时间类型字符串
     */
    public static String getDateStr(Date date) {
        return FORMAT.format(date);
    }

    /**
     * 获取时间的字符串，格式：yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getShortDateStr(Date date) {
        return FORMAT_DATE.format(date);
    }

    /**
     * 获取系统时间，格式：yyyyMMdd
     *
     * @return
     */
    public static String getShortDateStr() {
        return FORMAT_DATE_SHORT.format(getSystemDate());
    }

    /**
     * @param dateStr 格式：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date getShortDate(String dateStr) throws ParseException {
        return FORMAT_DATE_SHORT.parse(dateStr);
    }

    /**
     * @param dateStr 格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static Date getDate(String dateStr) throws ParseException {
        return FORMAT.parse(dateStr);
    }

    /**
     * 对日期添加天数后获得新的日期
     *
     * @param oldDate 旧日期
     * @param dayNum  天数
     * @return
     */
    public static Date getDateAdd(Date oldDate, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.DATE, dayNum);
        return calendar.getTime();
    }

    /**
     * 对日期增加月份后获取新的日期，比如，当前日期为2017-12-31日，得到增加一个月后日期是2018-01-31日
     *
     * @param oldDate  旧日期
     * @param monthNum 月份
     * @return
     */
    public static Date getMonthAdd(Date oldDate, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /**
     * 取得当前日期增加的月数后月份的第一天，比如，当前日期为2017-12-31日，得到增加一个月后日期是2018-01-01日
     *
     * @param dateStr  旧日期
     * @param monthNum 月份
     * @return
     * @throws ParseException
     */
    public static Date getMonthAdd(String dateStr, int monthNum) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(FORMAT_DATE.parse(dateStr));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }

    /**
     * 日期增加年份获取新的日期，比如，当前日期为2017-12-31日，得到增加一年后日期是2018-12-31日
     *
     * @param oldDate 旧日期
     * @param yearNum 年份
     * @return
     */
    public static Date getYearAdd(Date oldDate, int yearNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, yearNum);
        return calendar.getTime();
    }

    /**
     * 获取指定日期是星期几
     *
     * @param data
     * @return
     */
    public static String getDateOfWeek(Date data) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取两日期之间相差的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int getBetweenTwoDate(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int days = endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR) + 1;
        if (days < 0) {
            days = 0 - days;
        }
        return days;
    }

    /**
     * 获取两日期之间相差的天数
     *
     * @param startDateStr 开始日期，格式：yyyy-MM-dd
     * @param endDateStr   结束日期，格式：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static int getBetweenTwoDate(String startDateStr, String endDateStr) throws ParseException {
        Date startDate = FORMAT_DATE.parse(startDateStr);
        Date endDate = FORMAT_DATE.parse(endDateStr);
        return getBetweenTwoDate(startDate, endDate);
    }

    /**
     * 获取两日期之间相差的月份
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int getBetweenTwoMonth(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int months = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH) + 1;
        if (months < 0) {
            months = 0 - months;
        }
        return months;
    }

    /**
     * 获取两日期之间相差的月份
     *
     * @param startDateStr 开始日期，格式：yyyy-MM-dd
     * @param endDateStr   结束日期，格式：yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static int getBetweenTwoMonth(String startDateStr, String endDateStr) throws ParseException {
        Date startDate = FORMAT_DATE.parse(startDateStr);
        Date endDate = FORMAT_DATE.parse(endDateStr);
        return getBetweenTwoMonth(startDate, endDate);
    }

    /**
     * 日期相减（前-后）
     *
     * @param firstDate
     * @param secondDate
     * @return 返回相减后的毫秒数
     */
    public static long getDiffDate(Date firstDate, Date secondDate) {
        return firstDate.getTime() - secondDate.getTime();
    }

    /**
     * 判断前一个时间是否在后一个时间之前
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static boolean isBeforDate(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);
        return firstCalendar.before(secondCalendar);
    }

    /**
     * 判断前一个时间是否在后一个时间之前
     *
     * @param firstDateStr  ，格式：yyyy-MM-dd HH:mm:ss
     * @param secondDateStr ，格式：yyyy-MM-dd HH:mm:ss
     * @return
     * @throws ParseException
     */
    public static boolean isBeforDate(String firstDateStr, String secondDateStr) throws ParseException {
        Date firstDate = FORMAT.parse(firstDateStr);
        Date secondDate = FORMAT.parse(secondDateStr);
        return isBeforDate(firstDate, secondDate);
    }

    /**
     * 判断前一个时间是否在后一个时间之后
     *
     * @param firstDate  第一个时间
     * @param secondDate 第二个时间
     * @return
     */
    public static boolean isAfterDate(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);
        return firstCalendar.after(secondCalendar);
    }

    /**
     * 判断前一个时间是否在后一个时间之后
     *
     * @param firstDateStr  第一个时间
     * @param secondDateStr 第二个时间
     * @return
     * @throws ParseException
     */
    public static boolean isAfterDate(String firstDateStr, String secondDateStr) throws ParseException {
        Date firstDate = FORMAT.parse(firstDateStr);
        Date secondDate = FORMAT.parse(secondDateStr);
        return isAfterDate(firstDate, secondDate);
    }

    /**
     * 获取指定月份的最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, lastDay);
        return calendar.getTime();
    }

    /**
     * 获取指定月份的最后一天的日期，格式：yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfMonth(String dateStr) throws ParseException {
        Date date = FORMAT_DATE.parse(dateStr);
        return getLastDayOfMonth(date);
    }

    /**
     * 获取年份的最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }

    /**
     * 获取年份的最后一天的日期，格式：yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfYear(String dateStr) throws ParseException {
        Date date = FORMAT_DATE.parse(dateStr);
        return getLastDayOfYear(date);
    }

    /**
     * 获取年份的第一天的日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        return calendar.getTime();
    }

    /**
     * 获取年份的第一天的日期，格式：yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getFirstDayOfYear(String dateStr) throws ParseException {
        Date date = FORMAT_DATE.parse(dateStr);
        return getFirstDayOfYear(date);
    }

    /**
     * 将一个字符串统一格式化为yyyy-MM-dd
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date formatSCTDateString(String dateStr) throws ParseException {
        Date date = null;
        SimpleDateFormat sdfCST = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat sdfYYYY = new SimpleDateFormat("yyyy-MM-dd");
        String cst = "CST";
        String cdt = "CDT";
        if (dateStr.toUpperCase().indexOf(cst) != -1 || dateStr.toUpperCase().indexOf(cdt) != -1) {
            Date d = sdfCST.parse(dateStr);
            String str = sdfYYYY.format(d);
            date = sdfYYYY.parse(str);
        } else {
            date = sdfYYYY.parse(dateStr);
        }
        return date;
    }

    /**
     * MD5 加密字符串
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String input) throws NoSuchAlgorithmException {
        byte[] inputByte = input.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputByte);
        byte[] digest = md.digest();
        StringBuilder suf = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int val = ((int) digest[i]) & 0xff;
            if (val < 16) {
                suf.append("0");
            }
            suf.append(Integer.toHexString(val));
        }
        return suf.toString().toLowerCase();
    }

    /**
     * MD5 加密算法
     *
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5(String input) throws NoSuchAlgorithmException {
        byte[] inputByte = input.getBytes();
        String s = "";
        char[] hexDigest = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputByte);
        byte[] digest = md.digest();
        // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节，
        // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32个字符
        char[] c = new char[16 * 2];
        // 表示转换结果中对应的字符位置
        int k = 0;
        for (int i = 0; i < digest.length; i++) {
            byte b = digest[i];
            // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
            c[k++] = hexDigest[b >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            c[k++] = hexDigest[b & 0xf];
        }
        s = new String(c);
        return s;
    }

    private static final String MD5_KEY = "inz_about";

    /**
     * MD5 加密
     *
     * @param value 需要加密的值
     * @return 加密后的值
     */
    public static String encryptMd5(String value) {
        return DigestUtils.md5Hex(value + MD5_KEY);
    }

    /**
     * 比较MD5
     *
     * @param value    输入值
     * @param md5Value 加密后的值
     * @return 是否相同
     */
    public static boolean matchesMd5(String value, String md5Value) {
        if ("".equalsIgnoreCase(value) || "".equalsIgnoreCase(md5Value)) {
            return false;
        }
        String m = encryptMd5(value);
        return m.equalsIgnoreCase(md5Value);
    }


//    /**
//     * MD5 加密
//     *
//     * @param value 需要被加密的值
//     * @param salt  盐值
//     * @return
//     */
//    public static String encrypt(String value, String salt) {
//        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        return encoder.encodePassword(value, salt);
//    }
//
//    /**
//     * 通过Pbkdf2 进行加密, 设置加密后的字符串长度为 60
//     *
//     * @param rawPassword 输入的字符串
//     * @return
//     */
//    public static String encodePbkdf2(String rawPassword) {
//        PasswordEncoder encoder = new Pbkdf2PasswordEncoder("PK", 185000, 180);
//        return encoder.encode(rawPassword);
//    }
//
//    /**
//     * 通过Pbkdf2 进行比较
//     *
//     * @param rawPassword     输入的字符串
//     * @param encodedPassword 编码后的字符串
//     * @return
//     */
//    public static boolean matchesPbkdf2(String rawPassword, String encodedPassword) {
//        PasswordEncoder encoder = new Pbkdf2PasswordEncoder("PK", 185000, 100);
//        return encoder.matches(rawPassword, encodedPassword);
//    }
//
//    /**
//     * 通过 BCrypt 加密
//     *
//     * @param rawPassword 输入的字符串
//     * @return
//     */
//    public static String encodeBCrypt(String rawPassword) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder(14);
//        return encoder.encode(rawPassword);
//    }
//
//    /**
//     * 通过 BCrypt 进行比较
//     *
//     * @param rawPassword     输入的字符串
//     * @param encodedPassword 编码后的字符串
//     * @return
//     */
//    public static boolean matchesBCryot(String rawPassword, String encodedPassword) {
//        PasswordEncoder encoder = new BCryptPasswordEncoder(14);
//        return encoder.matches(rawPassword, encodedPassword);
//    }

    /**
     * 创建正则表达式，采用常用形式
     *
     * @param strRegex 通常习惯书写的正则表达式，如"^\\d+\\w."等，注意要用两条斜杠。
     * @param flagCase 是否区分大小写。0 不区分，1 区分， 默认区分大小写。另外，此方法默认了多行匹配。
     * @return
     */
    public static Pattern createPattren(String strRegex, int flagCase) {
        Pattern patternRegex = null;
        if (flagCase == 0) {
            patternRegex = Pattern.compile(strRegex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        } else {
            patternRegex = Pattern.compile(strRegex, Pattern.MULTILINE);
        }
        return patternRegex;
    }

    /**
     * 使用正则表达式检查某字串是否匹配表达式的方法
     *
     * @param strBeChecked 被检查的字符串。
     * @param strRegex     通常习惯书写的正则表达式，如"^\\d+\\w."等，注意要用两条斜杠。
     * @param flagCase     是否区分大小写。0 不区分，1 区分， 默认区分大小写。另外，此方法默认了多行匹配。
     * @return
     */
    public static boolean checkMatchRegex(String strBeChecked, String strRegex, int flagCase) {
        Pattern patternRegex = createPattren(strRegex, flagCase);
        Matcher matcher = patternRegex.matcher(strBeChecked);
        return matcher.find();
    }

    /**
     * 使用正则表达式替换某字串中匹配表达式的部分的方法
     *
     * @param strBeChecked 被检查的字符串。
     * @param strRegex     通常习惯书写的正则表达式，如"^\\d+\\w."等，注意要用两条斜杠。
     * @param flagCase     是否区分大小写。0 不区分，1 区分， 默认区分大小写。另外，此方法默认了多行匹配。
     * @param strChange    替换的字串。
     * @return
     */
    public static String replaceMatchRegex(String strBeChecked, String strRegex, int flagCase, String strChange) {
        Pattern patternRegex = createPattren(strRegex, flagCase);
        Matcher matcher = patternRegex.matcher(strBeChecked);
        return matcher.replaceAll(strChange);
    }

    /**
     * 使用正则表达式获取某字串中匹配表达式的部分的方法
     *
     * @param strBeChecked 被检查的字符串。
     * @param strRegex     通常习惯书写的正则表达式，如"^\\d+\\w."等，注意要用两条斜杠。
     * @param flagCase     是否区分大小写。0 不区分，1 区分， 默认区分大小写。另外，此方法默认了多行匹配。
     * @return
     */
    public static String subMatchRegex(String strBeChecked, String strRegex, int flagCase) {
        Pattern patternRegex = createPattren(strRegex, flagCase);
        Matcher matcher = patternRegex.matcher(strBeChecked);
        StringBuilder sub = new StringBuilder();
        while (matcher.find()) {
            sub.append(matcher.group());
        }
        return sub.toString();
    }

    /**
     * 截取字符串中的子串
     *
     * @param str    需要截取的字符串
     * @param start  截取字符串的起始位置;当start=-1时，表示倒序截取该字符串的最后cutNum位;
     *               当start>0时，表示顺序截取该字符串前cutNum位(索引值从1开始)
     * @param cutNum 截取字符串的个数
     * @return
     */
    public static String subString(String str, int start, int cutNum) {
        if (start > 0) {
            start = start - 1;
            if (start < str.length() && str.length() <= (start + cutNum)) {
                return str.substring(start, str.length());
            } else if (str.length() > (start + cutNum)) {
                return str.substring(start, start + cutNum);
            } else {
                return "";
            }
        } else {
            start = str.length() - cutNum;
            if (start > 0) {
                return str.substring(start, start + cutNum);
            } else {
                return str.substring(0, str.length());
            }
        }
    }

    /**
     * 获取随机码
     *
     * @return
     */
    public static String generatetRandomStr() {
        String randStr = "ABCDEFGHIabdefgh01234567890";
        StringBuilder generateRandStr = new StringBuilder();
        Random random = new Random();
        int randStrLength = 6;
        for (int i = 0; i < randStrLength; i++) {
            int randNum = random.nextInt(27);
            generateRandStr.append(randStr.substring(randNum, randNum + 1));
        }
        return generateRandStr.toString();
    }

    /**
     * 双精度加法运算
     *
     * @param firstDouble
     * @param secondDouble
     * @return
     */
    public static double add(double firstDouble, double secondDouble) {
        String firstStr = Double.toString(firstDouble);
        String secondStr = Double.toString(secondDouble);
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.add(secondBigDecimal).doubleValue();
    }

    /**
     * 字符串加法运算
     *
     * @param firstStr
     * @param secondStr
     * @return
     * @throws NumberFormatException
     */
    public static String add(String firstStr, String secondStr) throws NumberFormatException {
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.add(secondBigDecimal).toString();
    }

    /**
     * 双精度减法运算
     *
     * @param firstDouble
     * @param secondDouble
     * @return
     */
    public static double sub(double firstDouble, double secondDouble) {
        String firstStr = Double.toString(firstDouble);
        String secondStr = Double.toString(secondDouble);
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.subtract(secondBigDecimal).doubleValue();
    }

    /**
     * 字符串减法运算
     *
     * @param firstStr
     * @param secondStr
     * @return
     * @throws NumberFormatException
     */
    public static String sub(String firstStr, String secondStr) throws NumberFormatException {
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.subtract(secondBigDecimal).toString();
    }

    /**
     * 双精度乘法运算
     *
     * @param firstDouble
     * @param secondDouble
     * @return
     */
    public static double mul(double firstDouble, double secondDouble) {
        String firstStr = Double.toString(firstDouble);
        String secondStr = Double.toString(secondDouble);
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.multiply(secondBigDecimal).doubleValue();
    }

    /**
     * 字符串乘法运算
     *
     * @param firstStr
     * @param secondStr
     * @return
     * @throws NumberFormatException
     */
    public static String mul(String firstStr, String secondStr) throws NumberFormatException {
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.multiply(secondBigDecimal).toString();
    }

    /**
     * 默认除法运算结果精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 双精度除法运算
     *
     * @param firstDouble
     * @param secondDouble
     * @param scale        精确到小数点后的位数
     * @return
     */
    public static double div(double firstDouble, double secondDouble, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        String firstStr = Double.toString(firstDouble);
        String secondStr = Double.toString(secondDouble);
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.divide(secondBigDecimal, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 双精度除法运算
     *
     * @param firstDouble
     * @param secondDouble
     * @return
     */
    public static double div(double firstDouble, double secondDouble) throws IllegalArgumentException {
        return div(firstDouble, secondDouble, DEF_DIV_SCALE);
    }

    /**
     * 字符串除法运算
     *
     * @param firstStr
     * @param secondStr
     * @param scale     精度
     * @return
     */
    public static String div(String firstStr, String secondStr, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal firstBigDecimal = new BigDecimal(firstStr);
        BigDecimal secondBigDecimal = new BigDecimal(secondStr);
        return firstBigDecimal.divide(secondBigDecimal, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param value 值
     * @param scale 精度
     * @return
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return bigDecimal.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将数字格式化输出为字符串
     *
     * @param formatStr 格式形式
     * @param value     值
     * @return
     */
    public static String formatString(String formatStr, int value) {
        DecimalFormat format = new DecimalFormat(formatStr);
        return format.format(value);
    }

    /**
     * 将双精度数格式化输出为字符串
     *
     * @param formatStr 格式形式
     * @param value     值
     * @return
     */
    public static String formatString(String formatStr, double value) {
        NumberFormat format = new DecimalFormat(formatStr);
        return format.format(value);
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成随机ID号
     *
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 生成随机码
     *
     * @param count 随机码的位数
     * @param type  随机码类型：0-数字字母组合；1-纯数字；2-纯字母
     * @return
     */
    public static String getRandomCode(int count, String type) {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        while (val.length() < count) {
            /* 输出字母还是数字 */
            String charOrNum = (random.nextInt(2) % 2 == 0) ? "char" : "num";
            boolean a = "0".equals(type) || "2".equals(type);
            boolean b = "char".equalsIgnoreCase(charOrNum);
            boolean c = "0".equals(type) || "1".equals(type);
            boolean d = "num".equalsIgnoreCase(charOrNum);
            if (a && b) {
                /* 数字 */
                val.append(String.valueOf(random.nextInt(10)));
            } else if (c && d) {
                /* ASCII码 65 -> A ; ASCII码 97 -> a */
                int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97;
                /* 字母 */
                val.append((char) (choice + random.nextInt(26)));
            }
        }
        return val.toString();
    }

    /**
     * 获取 IP 地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    /**
     * 获取 MAC 地址
     *
     * @param ipAddr IP 地址
     * @return
     * @throws IOException
     */
    public static String getMACAddr(String ipAddr) throws IOException {
        String str = "", strMAC = "", macAddr = "";
        String macAddressPrefix = "MAC Address ";
        String loopbackAddress = "127.0.0.1";

        /* 判断是否为本地访问 */
        if (loopbackAddress.equals(ipAddr)) {
            InetAddress address = InetAddress.getLocalHost();
            byte[] mac = NetworkInterface.getByInetAddress(address).getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }

                String s = Integer.toHexString(mac[i] & 0xFF);
                sb.append((s.length() == 1) ? "0" + s : s);
            }
            macAddr = sb.toString().trim().toUpperCase();
        } else {
            Process process = Runtime.getRuntime().exec("nbtstat -A " + ipAddr);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            // LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
            BufferedReader lineNumberReader = new BufferedReader(inputStreamReader);
            while ((str = lineNumberReader.readLine()) != null) {
                int index = str.indexOf(macAddressPrefix);
                if (index != -1) {
                    strMAC = str.substring(str.indexOf("MAC Address ") + index).trim().toUpperCase();
                    break;
                }
            }
            int macLength = 17;
            if (strMAC.length() < macLength) {
                return "Error!";
            }
            macAddr = strMAC.substring(0, 2) + ":" + strMAC.substring(3, 5) + ":" + strMAC.substring(6, 8) + ":"
                    + strMAC.substring(9, 11) + ":" + strMAC.substring(12, 14) + ":" + strMAC.substring(15, 17);
        }

        return macAddr;
    }

    /**
     * 获取主机名
     *
     * @param clientIP 客户端IP
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static String getHostName(String clientIP) throws IOException, InterruptedException {
        String str = "";
        String ip3 = null;
        int ipLength = 4;
        String localIp = "127";
        if (BaseUtil.isEmpty(clientIP) || clientIP.length() < ipLength) {
            ip3 = "";
        } else {
            ip3 = clientIP.substring(0, 3);
        }
        if (localIp.equals(ip3)) {
            str = "本机";
        } else {
            String s1 = "nbtstat -A " + clientIP;
            Process process = Runtime.getRuntime().exec(s1);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String nextLine;
            for (String line = bufferedReader.readLine(); line != null; line = nextLine) {
                nextLine = bufferedReader.readLine();
                int index = -1;
                if (nextLine != null) {
                    index = nextLine.indexOf("<00>");
                }
                if (index != -1) {
                    if ("".equals(str)) {
                        str = nextLine.substring(0, index).toUpperCase();
                    }  // String work_group = nextLine.substring(0, index).toUpperCase();

                }
            }
            bufferedReader.close();
            process.waitFor();
        }
        return str.trim();
    }

    /**
     * 获取访问的前一个URL
     *
     * @param request
     * @return
     */
    public static String getRefererURL(HttpServletRequest request) {
        String str = "";
        str = request.getHeader("Referer").toString();
        return str;
    }

    public static String refererPage(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        String formParam = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                // 判断cookie中存的URL请求的文件路径是否包含当前路径参数Url
                if ("URL".equals(c.getName()) && c.getValue().contains(url)) {
                    for (Cookie cookie : cookies) {
                        // 判断cooki中是否已经存在页面表单序列化内容
                        if ("formParam".equals(cookie.getName()) && !isEmpty(cookie.getValue())) {
                            formParam = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        }
                    }
                }
                break;
            }
        }
        if (formParam != null) {
            // formParam不为null,请求路径带参数重定向
            return "forward:" + url + ".do?" + formParam + "&premger=no";
        } else {
            // 如果formParam为null,则没有执行cookie内容调取或者访问非当前路径参数下的请求
            return "forward:" + url + ".do?premger=no";
        }
    }

    private static final String[] NUMBER_VERIFY_STR = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String[] LETTER_VERIFY_STR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static final String[] VERIFY_STR = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    /**
     * 获取验证码
     *
     * @param count 位数
     * @return 验证码
     */
    public static String getVerifyCode(int count) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(VERIFY_STR.length);
            sb.append(VERIFY_STR[x]);
        }
        return sb.toString();
    }

    /**
     * 获取数字验证码
     *
     * @param count 位数
     * @return 验证码
     */
    public static String getNumberVerifyCode(int count) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(NUMBER_VERIFY_STR.length);
            sb.append(NUMBER_VERIFY_STR[x]);
        }
        return sb.toString();
    }

    /**
     * 获取字母验证码
     *
     * @param count 位数
     * @return 验证码
     */
    public static String getLetterVerifyCode(int count) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int x = random.nextInt(LETTER_VERIFY_STR.length);
            sb.append(LETTER_VERIFY_STR[x]);
        }
        return sb.toString();
    }

    /**
     * 获取是否为Windows
     *
     * @return 是否为Windows 系统
     */
    public static boolean isWindows() {
        String systemName = System.getProperty("os.name").toLowerCase();
        int index = systemName.indexOf("windows");
        return index != -1;
    }

    /**
     * 获取是否为Linux
     *
     * @return 是否为Linux 系统
     */
    public static boolean isLinux() {
        String systemName = System.getProperty("os.name").toLowerCase();
        int index = systemName.indexOf("linux");
        return index != -1;
    }




}
