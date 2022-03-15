package com.example.my_task.Utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.List;

@Component
public class SendMail {

    @Autowired
    JavaMailSender javaMailSender;

    @Mapper
    public interface EmailMapper {
        @Select("select email from email where status = 0")
        public List<String> getEmailList();
    }

    @Autowired      //注入mapper
    @SuppressWarnings("all")
    EmailMapper emailMapper;


    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(List<String> list) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("这是一封测试邮件");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("562987458@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        List<String> emailList = emailMapper.getEmailList();
        String emails = CollUtil.join(emailList, ",");
        message.setTo(emails);
//        message.setTo("562987458@qq.com");
        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("12****32*qq.com");
        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
//        message.setText("这是测试邮件的正文");
        message.setText("今日可打新数量："+ list.size()+"个,\r\n当前时间:"+ DateUtil.date());
        // 发送邮件
        javaMailSender.send(message);
    }

    public String sendXComplexMail() throws MessagingException {
        //1、创建一个复杂的邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //邮件主题
        helper.setSubject("这是一个邮件啊");
        //文本中添加图片
        helper.addInline("image1",new FileSystemResource("D:\\images\\spring\\1.jpg"));
        //邮件内容
        helper.setText("全栈学习笔记<a href='https://www.cnblogs.com/swzx-1213/'>百度一下</a>    <img src='cid:image1'></img>",true);
        helper.setTo("xxxxx@139.com");
        helper.setFrom("qzstudynote@qq.com");
        //附件添加图片
        helper.addAttachment("1.jpg",new File("D:\\images\\spring\\1.jpg"));
        //附件添加word文档
        helper.addAttachment("哈哈哈.docx",new File("D:\\images\\spring\\哈哈哈.docx"));

        javaMailSender.send(mimeMessage);
        return "复杂邮件发送！";
    }

}
