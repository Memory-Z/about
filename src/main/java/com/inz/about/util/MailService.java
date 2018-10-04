package com.inz.about.util;

import java.util.Map;

/**
 * 邮件服务
 *
 * @author inz
 * @version 1.0.0
 * Create by Zhenglj on 2018/10/2 21:59
 **/
public class MailService {


    /**
     * 发送简单邮件
     *
     * @param toEmails 收件人邮箱，多个邮箱之间用英文分号隔开（;）
     * @param subject  邮件主题
     * @param content  邮件内容
     * @return
     */
    public static boolean sendSimpleMail(String toEmails, String subject, String content) {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setToEmails(toEmails);
        mailUtil.setSubject(subject);
        mailUtil.setContent(content);
        return sendEmail(mailUtil);
    }

    /**
     * 发送带图片的邮件
     *
     * @param toEmails 收件人邮箱，多个邮箱之间用英文分号隔开（;）
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param pictures 邮件图片，为空时表示无图片，MAP 中KEY 值为图片ID, VALUE 值为 图片地址
     *                 图片的引用；<img src="cid:图片ID" />
     * @return
     */
    public static boolean sendImageMail(String toEmails, String subject, String content, Map<String, String> pictures) {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setToEmails(toEmails);
        mailUtil.setSubject(subject);
        mailUtil.setPictures(pictures);
        mailUtil.setContent(content);
        return sendEmail(mailUtil);
    }

    /**
     * 发送带附件的邮件
     *
     * @param toEmails    收件人邮箱，多个邮箱之间用英文分号隔开（;）
     * @param subject     邮件主题
     * @param content     邮件内容
     * @param attachments 邮件附件，为空时表示无附件， MAP 中KEY 值为附件ID, VALUE 值为附件地址
     * @return
     */
    public static boolean sendAttachmentsMail(String toEmails, String subject, String content,
                                              Map<String, String> attachments) {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setToEmails(toEmails);
        mailUtil.setSubject(subject);
        mailUtil.setContent(content);
        mailUtil.setAttachments(attachments);
        return sendEmail(mailUtil);
    }

    /**
     * 发送带图片、附件的邮件
     *
     * @param toEmails    收件人邮箱，多个邮箱之间用英文分号隔开（;）
     * @param subject     邮件主题
     * @param content     邮件内容
     * @param pictures    邮件图片，为空时表示无图片，MAP 中KEY 值为图片ID, VALUE 值为 图片地址
     *                    图片的引用；<img src="cid:图片ID" />
     * @param attachments 邮件附件，为空时表示无附件， MAP 中KEY 值为附件ID, VALUE 值为附件地址
     * @return
     */
    public static boolean sendComplexMail(String toEmails, String subject, String content, Map<String, String> pictures,
                                          Map<String, String> attachments) {
        MailUtil mailUtil = new MailUtil();
        mailUtil.setToEmails(toEmails);
        mailUtil.setSubject(subject);
        mailUtil.setContent(content);
        mailUtil.setPictures(pictures);
        mailUtil.setAttachments(attachments);
        return sendEmail(mailUtil);
    }

    private static boolean sendEmail(MailUtil mailUtil) {
        try {
            mailUtil.sendEmail();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
