package com.inz.about.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 邮件攻击
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/2 21:05
 **/
public class MailUtil {

    /**
     * 发件邮箱服务器
     */
    private String emailHost;
    /**
     * 发件人邮箱
     */
    private String emailFrom;
    /**
     * 发件人用户名
     */
    private String emailUserName;
    /**
     * 发件人密码
     */
    private String emailPassword;
    /**
     * 收件人邮箱，多个邮箱间用英文分号（;）分隔
     */
    private String toEmails;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件图片，为空时表示无图片，MAP 中KEY 值为图片ID, VALUE 值为 图片地址
     */
    private Map<String, String> pictures;
    /**
     * 邮件附件，为空时表示无附件， MAP 中KEY 值为附件ID, VALUE 值为附件地址
     */
    private Map<String, String> attachments;

    public String getEmailHost() {
        if (BaseUtil.isEmpty(emailHost)) {
            emailHost = Constants.emailHost;
        }
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public String getEmailFrom() {
        if (BaseUtil.isEmpty(emailFrom)) {
            emailFrom = Constants.emailFrom;
        }
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailUserName() {
        if (BaseUtil.isEmpty(emailUserName)) {
            emailUserName = Constants.emailUserName;
        }
        return emailUserName;
    }

    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    public String getEmailPassword() {
        if (BaseUtil.isEmpty(emailPassword)) {
            emailPassword = Constants.emailPassword;
        }
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getToEmails() {
        if (BaseUtil.isEmpty(toEmails)) {
            return "";
        } else {
            return toEmails.trim();
        }
    }

    public void setToEmails(String toEmails) {
        this.toEmails = toEmails;
    }

    public String getSubject() {
        if (BaseUtil.isEmpty(subject)) {
            return "无主题";
        } else {
            return subject.trim();
        }
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        if (BaseUtil.isEmpty(content)) {
            return "";
        } else {
            return content;
        }
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getPictures() {
        return pictures;
    }

    public void setPictures(Map<String, String> pictures) {
        this.pictures = pictures;
    }

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    /**
     * 发送邮件，发送前务必检查，收件人邮箱是否填写正确
     */
    public void sendEmail() {
        if ("".equals(this.getEmailHost()) || "".equals(this.getEmailFrom()) || "".equals(this.getEmailUserName())
                || "".equals(this.getEmailPassword())) {
            throw new RuntimeException("发件人信息不完全，请确认发件人信息！");
        }
        if ("".equals(this.getToEmails())) {
            throw new RuntimeException("收件人邮箱不能为空！");
        }

        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定 邮箱服务器
        senderImpl.setHost(this.getEmailHost());

        // 建立邮件消息
        MimeMessage mimeMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = null;

        // 设置发件人邮件
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setFrom(new InternetAddress(getEmailFrom(), "Inz.", "UTF-8"));
        } catch (UnsupportedEncodingException | MessagingException e2) {
            e2.printStackTrace();
        }

        String[] toEmailArray = this.getToEmails().split(";");
        List<String> toEmailList = new ArrayList<String>();
        if (toEmailArray.length < 1) {
            throw new RuntimeException("收件人邮箱不能为空！");
        } else {
            for (String email : toEmailArray) {
                if (!BaseUtil.isEmpty(email)) {
                    toEmailList.add(email);
                }
            }
            if (toEmailList.isEmpty()) {
                throw new RuntimeException("收件人邮箱不能为空！");
            } else {
                toEmailArray = new String[toEmailList.size()];
                for (int i = 0; i < toEmailList.size(); i++) {
                    toEmailArray[i] = toEmailList.get(i);
                }
            }
        }
        try {
            if (messageHelper != null) {
                messageHelper.setTo(toEmailArray);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 邮件主题
        try {
            if (messageHelper != null) {
                messageHelper.setSubject(this.getSubject());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // true 表示启动HTML格式
        try {
            if (messageHelper != null) {
                messageHelper.setText(this.getContent(), true);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 添加图片
        if (null != pictures) {
            for (Map.Entry<String, String> entry : pictures.entrySet()) {
                String cid = entry.getKey();
                String filePath = entry.getValue();
                if (null == cid || null == filePath) {
                    throw new RuntimeException("确保每张图片都有ID和地址！");
                }
                File file = new File(filePath);
                if (!file.exists()) {
                    throw new RuntimeException("图片" + filePath + "不存在！");
                }
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                try {
                    if (messageHelper != null) {
                        messageHelper.addInline(cid, fileSystemResource);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        // 添加附件
        if (null != attachments) {
            for (Map.Entry<String, String> entry : attachments.entrySet()) {
                String cid = entry.getKey();
                String filePath = entry.getValue();
                if (null == cid || null == filePath) {
                    throw new RuntimeException("确保每个附件都有ID和地址！");
                }
                File file = new File(filePath);
                if (!file.exists()) {
                    throw new RuntimeException("附件" + filePath + "不存在！");
                }
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                try {
                    if (messageHelper != null) {
                        messageHelper.addAttachment(cid, fileSystemResource);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        // 设置发送时间
        try {
            mimeMessage.setSentDate(new Date());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        Properties properties = new Properties();
        // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        properties.put("mail.smtp.auth", true);
        // 设置发送超时时间
        properties.put("mail.smtp.timeout", 2500);
        // 邮件传输协议 （必须）
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", this.getEmailHost());
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // 添加验证
        MailAuthenticator authenticator = new MailAuthenticator(emailUserName, emailPassword);
        Session session = Session.getDefaultInstance(properties, authenticator);
        session.setDebug(true);
        senderImpl.setSession(session);

        // 保存更改
        try {
            mimeMessage.saveChanges();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 发送邮件
        senderImpl.send(mimeMessage);
    }

    /**
     * 验证邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        try {
            InternetAddress address = new InternetAddress(email);
            address.validate();
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * // 测试 public static void main(String[] args) throws Exception { MailUtil mu =
     * new MailUtil(); // test1(mu); // test2(mu); // test3(mu); // test4(mu); //
     * test5(mu); test6(mu); }
     *
     * public static void test1(MailUtil mu) throws Exception { String toEmails =
     * "time-memory-z@qq.com"; String subject = "第一封，简单文本邮件"; StringBuilder builder
     * = new StringBuilder(); builder.append("我相信天上不会掉馅饼"); String content =
     * builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content); try
     * { mu.sendEmail(); } catch (RuntimeException e) {
     * System.out.println(e.getLocalizedMessage()); return; } }
     *
     * public static void test2(MailUtil mu) throws Exception { String toEmails =
     * "1165469346@qq.com"; String subject = "第二封，HTML邮件"; StringBuilder builder =
     * new StringBuilder(); builder.
     * append("<html><body>-**：<br />*************？<br />*********<br /></body></html>"
     * ); String content = builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content);
     *
     * mu.sendEmail(); }
     *
     * public static void test3(MailUtil mu) throws Exception { String toEmails =
     * "1165469346@qq.com"; String subject = "第三封，图片邮件";
     *
     * Map<String, String> pictures = new java.util.HashMap<String, String>();
     * pictures.put("d1", "E:/dowmloads/0ed1209e186cd178a5d0a9a588c56c02.jpg");
     * pictures.put("d2", "E:/dowmloads/段落式报表.png");
     *
     * StringBuilder builder = new StringBuilder();
     * builder.append("<html><body>看看下面的图，你会知道花儿为什么是这样红的：<br />");
     * builder.append("<img src=\"cid:d1\" /><br />");
     * builder.append("<img src=\"cid:d2\" /><br />");
     * builder.append("</body></html>"); String content = builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content);
     * mu.setPictures(pictures);
     *
     * mu.sendEmail();
     *
     * }
     *
     * public static void test4(MailUtil mu) throws Exception { String toEmails =
     * "1165469346@qq.com"; String subject = "第四封，附件邮件"; Map<String, String>
     * attachments = new java.util.HashMap<String, String>();
     * attachments.put("d1.jar", "E:/dowmloads/druid-1.1.3.jar");
     * attachments.put("d2.doc", "E:/dowmloads/名称4.doc"); StringBuilder builder =
     * new StringBuilder();
     * builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。</body></html>"); String
     * content = builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content);
     * mu.setAttachments(attachments);
     *
     * mu.sendEmail(); }
     *
     * public static void test5(MailUtil mu) throws Exception { String toEmails =
     * "1165469346@qq.com"; String subject = "第五封，综合邮件";
     *
     * Map<String, String> attachments = new java.util.HashMap<String, String>();
     * attachments.put("d1.jar", "E:/dowmloads/druid-1.1.3.jar");
     * attachments.put("名称.doc", "E:/dowmloads/名称4.doc");
     *
     * Map<String, String> pictures = new java.util.HashMap<String, String>();
     * pictures.put("d1", "E:/dowmloads/0ed1209e186cd178a5d0a9a588c56c02.jpg");
     * pictures.put("d2", "E:/dowmloads/段落式报表.png");
     *
     * StringBuilder builder = new StringBuilder();
     * builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
     * builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
     * builder.append("<img src=\"cid:d1\" /><br />");
     * builder.append("<img src=\"cid:d2\" /><br />");
     * builder.append("</body></html>"); String content = builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content);
     * mu.setPictures(pictures); mu.setAttachments(attachments);
     *
     * mu.sendEmail(); }
     *
     * public static void test6(MailUtil mu) throws Exception { String toEmails =
     * "1165469346@qq.com;zlj1165469346@163.com"; String subject = "第五封，群发邮件";
     *
     * Map<String, String> attachments = new java.util.HashMap<String, String>();
     * attachments.put("jquery-3.2.1.js", "H:/Downloads/jquery-3.2.1.js");
     * attachments.put("bootstrap.zip", "H:/Downloads/bootstrap.zip");
     *
     * Map<String, String> pictures = new java.util.HashMap<String, String>();
     * pictures.put("d1", "C:/Users/Z_/Desktop/jiaoben3555/images/banner1.jpg");
     * pictures.put("d2", "H:/Downloads/favicon.ico");
     *
     * StringBuilder builder = new StringBuilder();
     * builder.append("<html><body>看看附件中的资料，你会知道世界为什么是平的。<br />");
     * builder.append("看看下面的图，你会知道花儿为什么是这样红的：<br />");
     * builder.append("<img src=\"cid:d1\" /><br />");
     * builder.append("<img src=\"cid:d2\" /><br />");
     * builder.append("</body></html>"); String content = builder.toString();
     *
     * mu.setToEmails(toEmails); mu.setSubject(subject); mu.setContent(content);
     * mu.setPictures(pictures); mu.setAttachments(attachments);
     *
     * mu.sendEmail(); }
     */

}
