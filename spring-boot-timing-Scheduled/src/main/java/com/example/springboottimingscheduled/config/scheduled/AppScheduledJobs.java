package com.example.springboottimingscheduled.config.scheduled;

import com.example.springboottimingscheduled.common.Constants;
import com.example.springboottimingscheduled.service.SamplePageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.time.LocalDateTime;

@Slf4j
@Component
public class AppScheduledJobs {

    /**
     * 每10秒执行一次
     *
     * @return: void
     * @author : zhengqing
     * @date : 2020/7/1 11:44
     */
//    @Scheduled(cron = "*/10 * * * * ?")
//    public void cralwer() {
//        log.info("<<<<<< Start: 【{}】 >>>>>>", LocalDateTime.now());
//        Spider.create(new SamplePageProcessor())
//                // .setDownloader(new HttpClientDownloader())
//                // 从指定的url地址开始抓
//                .addUrl(Constants.CSDN_URL)
//                // 开启5个线程抓取
//                .thread(5)
//                // 启动爬虫
//                .run();
//    }

}
