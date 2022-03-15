package com.example.springboottimingscheduled.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.springboottimingscheduled.Utils.SendMail;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Pa_httpclient {

    @Autowired
    SendMail sendMail;

    @Autowired
    JavaMailSender javaMailSender;

    public void pa() throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet请求
//        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        HttpGet httpGet = new HttpGet("https://data.eastmoney.com/xg/xg/calendar.html");

        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            response = httpClient.execute(httpGet);

            //判断响应状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                String[] split = content.split("var pagedata=");
                String[] split1 = split[1].split(";</script>");
                JSONObject jsonObject = JSONUtil.parseObj(split1[0]);
                JSONObject calendardata = JSONUtil.parseObj(jsonObject.get("calendardata"));
                JSONObject result = JSONUtil.parseObj(calendardata.get("result"));
                JSONArray jsonArray = JSONUtil.parseArray(result.get("data"));
                List<String> list = new ArrayList<>();
                for (Object o : jsonArray) {
                    JSONObject object = JSONUtil.parseObj(o);
                    if (object.get("SECURITY_NAME_ABBR").toString().contains("转债") &&
                            DateUtil.isSameDay(DateUtil.date(), DateUtil.parse(object.get("TRADE_DATE").toString())) &&
                            object.get("DATE_TYPE").equals("申购")) {
                        list.add(object.get("SECURITY_NAME_ABBR").toString());
                    }
                }
                if (list.size() > 0) {
                    sendMail.sendSimpleMail(list);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet请求
//        HttpGet httpGet = new HttpGet("http://www.itcast.cn/");
        HttpGet httpGet = new HttpGet("https://data.eastmoney.com/xg/xg/calendar.html");

        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求
            response = httpClient.execute(httpGet);

            //判断响应状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //如果为200表示请求成功，获取返回数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                String[] split = content.split("var pagedata=");
                String[] split1 = split[1].split(";</script>");
                JSONObject jsonObject = JSONUtil.parseObj(split1[0]);
                JSONObject calendardata = JSONUtil.parseObj(jsonObject.get("calendardata"));
                JSONObject result = JSONUtil.parseObj(calendardata.get("result"));
                JSONArray jsonArray = JSONUtil.parseArray(result.get("data"));
                List<String> list = new ArrayList<>();
                for (Object o : jsonArray) {
                    JSONObject object = JSONUtil.parseObj(o);
                    if (object.get("SECURITY_NAME_ABBR").toString().contains("转债") &&
                            DateUtil.isSameDay(DateUtil.date(), DateUtil.parse(object.get("TRADE_DATE").toString())) &&
                            object.get("DATE_TYPE").equals("申购")) {
                        list.add(object.get("SECURITY_NAME_ABBR").toString());
                        System.out.println();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            if (response == null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                httpClient.close();
            }
        }
    }

    /**
     * 普通邮件发送
     */
    public void sendSimpleMail(List<String> list) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("********"+DateUtil.today()+"打新情况******");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("562987458@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo("562987458@qq.com");
        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("12****32*qq.com");
        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        message.setText("今日可打新数量："+ list.size()+"个,\r\n当前时间:"+DateUtil.date());
        // 发送邮件
        javaMailSender.send(message);
    }

}
