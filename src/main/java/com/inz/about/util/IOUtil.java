package com.inz.about.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/2 20:30
 **/
public class IOUtil {


    /**
     * 将字节数组转换为输入流
     *
     * @param buffer
     * @return
     */
    public static InputStream byte2Input(byte[] buffer) {
        return new ByteArrayInputStream(buffer);
    }

    /**
     * 将输入流转换为字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] input2Byte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[100];
        int len = 0;
        if (inputStream.markSupported()) {
            if (inputStream.available() <= 0) {
                inputStream.reset();
            }
        }

        while ((len = inputStream.read(buffer, 0, 100)) > 0) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byte[] in2b = byteArrayOutputStream.toByteArray();
        return in2b;
    }

    /**
     * 字节数组拼接
     *
     * @param origin  原字节数组
     * @param newbyte 新字节数组
     * @return
     * @throws IOException
     */
    public static byte[] append(byte[] origin, byte[] newbyte) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(origin);
        outputStream.write(newbyte);
        return outputStream.toByteArray();
    }

    /**
     * 保存文件
     *
     * @param filePath 文件路径
     * @param fileName 文件名称
     * @param content  文件内容
     * @param append   是否追加
     * @return
     */
    public static boolean saveFile(String filePath, String fileName, String content, boolean append) {
        boolean isSuccess = mkdir(filePath);
        boolean isSave = false;
        FileWriter fileWriter = null;
        if (isSuccess) {
            try {
                fileWriter = new FileWriter(filePath + "/" + fileName, append);
                fileWriter.write(content);
                fileWriter.flush();
                fileWriter.close();
                isSave = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                fileWriter = null;
            }
        }
        return isSave;
    }

    /**
     * 读取文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static String readFile(String fileName) {
        FileReader fileReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                sb.append(s).append("\r\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader = null;
        }
        return sb.toString();
    }

    /**
     * 读取邮件模板
     *
     * @param fileName         文件名
     * @param userName         用户名
     * @param verificationCode 验证码
     * @return
     */
    public static String readEmailTemp(String fileName, String userName, String verificationCode) {
        FileReader fileReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = "";
            int i = 0;
            // html 第115 行 ：用户名；第128 行：验证码
            while ((s = bufferedReader.readLine()) != null) {
                i++;
                if (i == 115) {
                    sb.append(userName).append("\r\n");
                } else if (i == 128) {
                    sb.append(verificationCode).append("\r\n");
                } else {
                    sb.append(s).append("\r\n");
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader = null;
        }
        return sb.toString();
    }

    /**
     * 判断是否是文件
     *
     * @param filePath
     * @return
     */
    public static boolean isFile(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (file.exists() && file.isFile()) {
                return true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            file = null;
        }
        return false;
    }

    /**
     * 获取文件列表
     *
     * @param filePath
     * @return
     */
    public static List<List<Object>> getFileList(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (file.isDirectory()) {
                String[] filesName = file.list();
                List<List<Object>> list = new ArrayList<List<Object>>();
                if (filesName != null) {
                    for (String aFilesName : filesName) {
                        List<Object> fileList = new ArrayList<Object>();
                        file = new File(filePath + "/" + aFilesName);
                        if (file.isFile()) {
                            fileList.add(aFilesName);
                            fileList.add("FILE");
                        } else {
                            fileList.add(aFilesName);
                            fileList.add("DIR");
                        }
                        list.add(fileList);
                    }
                } else {
                    System.out.println("文件夹为空");
                }
                return list;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            file = null;
        }
        return null;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     * @throws NullPointerException
     */
    public static boolean mkdir(String path) {
        boolean isDir = false;
        File file = null;
        try {
            file = new File(path);
            if (!file.exists() && !file.isDirectory()) {
                isDir = file.mkdirs();
            } else {
                isDir = true;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        } finally {
            file = null;
        }
        return isDir;
    }

    /**
     * 删除目录
     *
     * @param dir
     */
    public static void deleteDir(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    file.delete();
                } else if (file.isDirectory()) {
                    deleteDir(file);
                }
            }
        }
        dir.delete();
    }

    /**
     * 删除文件或文件夹下的全部文件
     *
     * @param dir
     */
    public static void deleteDir(String dir) {
        File file = new File(dir);
        deleteDir(file);
    }

    /**
     * File 类型 转 字节数组
     *
     * @param filePath
     * @return
     */
    public static byte[] file2Byte(String filePath) {
        byte[] buffer = null;
        File file = null;
        try {
            file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, n);
            }
            fileInputStream.close();
            byteArrayOutputStream.close();
            buffer = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file = null;
        }
        return buffer;
    }

    /**
     * 将字节数组转换为 File 类型
     *
     * @param buffer
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    public static void byte2File(byte[] buffer, String filePath, String fileName) {
        BufferedOutputStream bufferedOutputStream = null;
        FileOutputStream fileOutputStream = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fileOutputStream = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制文件
     *
     * @param oldPath  文件原地址
     * @param newDir   文件新地址
     * @param fileName 文件名
     */
    public static void copyFile(String oldPath, String newDir, String fileName) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        File newFile = null;
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            newFile = new File(newDir);
            if (oldfile.exists()) { // 文件存在时
                inStream = new FileInputStream(oldPath); // 读入原文件
                fs = new FileOutputStream(newFile + "\\" + fileName);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 移动文件
     *
     * @param oldPath
     * @param newPath
     */
    public static void removeFile(String oldPath, String newPath) {
        String dir = newPath.substring(0, newPath.lastIndexOf("\\"));
        mkdir(dir);
        String fileName = newPath.substring(newPath.lastIndexOf("\\"));
        copyFile(oldPath, dir, fileName);
        // File file = new File(oldPath);
        // file.delete();
    }

    /**
     * 复制某个目录及目录下的所有子目录和文件到新文件夹
     *
     * @param oldPath
     * @param newPath
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在，则建立新文件夹
            (new File(newPath)).mkdirs();
            // 读取整个文件夹的内容到file字符串数组，下面设置一个游标i，不停地向下移开始读这个数组
            File filelist = new File(oldPath);
            String[] file = filelist.list();
            // 要注意，这个temp仅仅是一个临时文件指针
            // 整个程序并没有创建临时文件
            File temp = null;
            if (file != null) {
                for (String aFile : file) {
                    // 如果oldPath以路径分隔符/或者\结尾，那么则oldPath/文件名就可以了
                    // 否则要自己oldPath后面补个路径分隔符再加文件名
                    // 谁知道你传递过来的参数是f:/a还是f:/a/啊？
                    if (oldPath.endsWith(File.separator)) {
                        temp = new File(oldPath + aFile);
                    } else {
                        temp = new File(oldPath + File.separator + aFile);
                    }

                    // 如果游标遇到文件
                    if (temp.isFile()) {
                        FileInputStream input = new FileInputStream(temp);
                        // 复制
                        FileOutputStream output = new FileOutputStream(newPath + "\\" + (temp.getName()).toString());
                        byte[] bufferarray = new byte[1024 * 64];
                        int prereadlength;
                        while ((prereadlength = input.read(bufferarray)) != -1) {
                            output.write(bufferarray, 0, prereadlength);
                        }
                        output.flush();
                        output.close();
                        input.close();
                    }
                    // 如果游标遇到文件夹
                    if (temp.isDirectory()) {
                        copyFolder(oldPath + "\\" + aFile, newPath + "\\" + aFile);
                    }
                }
            } else {
                System.out.println("复制的文件夹内容为空");
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
        }
    }

}
