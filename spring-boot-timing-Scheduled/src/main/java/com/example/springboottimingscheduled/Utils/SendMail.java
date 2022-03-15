package com.example.springboottimingscheduled.Utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.example.springboottimingscheduled.config.scheduled.DynamicScheduleTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
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
}
