package com.example.springboottimingscheduled.config.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//        ****Scheduled的使用******
//        首先使用 @Scheduled 注解开启一个定时任务。
//        fixedRate 表示任务执行之间的时间间隔，具体是指两次任务的开始时间间隔，即第二次任务开始时，第一次任务可能还没结束。
//        fixedDelay 表示任务执行之间的时间间隔，具体是指本次任务结束到下次任务开始之间的时间间隔。
//        initialDelay 表示首次任务启动的延迟时间。
//        所有时间的单位都是毫秒。
//        @Scheduled 注解也支持 cron 表达式
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class Scheduled_demo {

//    每隔5秒钟执行一次	           */5  *  *  *  *  ?
//    每隔1分钟执行一次	           0  */1  *  *  *  ?
//    每天1点执行一次	　　　　0  0  1  *  *  ?
//    每天23点55分执行一次	　　　　0  55  23  *  *  ？
//    每月最后一天23点执行一次	　　　　0  0  23  L  *  ？
//    每周六8点执行一次	　　　　0  0  8  ?  *  L
//    每月最后一个周五，每隔2小时执行一次	　　　　0  0  */2  ?  *  6L
//    每月的第三个星期五上午10:15执行一次	　　　　0  15  10  ?  *  5#3
//    在每天下午2点到下午2:05期间的每1分钟执行	　　　　0  0-5  14  *  *  ?
//    表示周一到周五每天上午10:15执行	　　　　0  15  10  ?  *  2-6
//    每个月的最后一个星期五上午10:15执行	　　　　0  15  10  ?  *  6L
//    每天上午10点，下午2点，4点执行一次	　　　　0  0  10,14,16  * * ?
//    朝九晚五工作时间内每半小时执行一次	　　　　0  0/30  9-17  *  * ?
//    每个星期三中午12点执行一次	　　　　0  0  12  ?  *  4
//    每年三月的星期三的下午2:10和2:44各执行一次	　　　   0  10,44  14  ?  3  4　
//    每月的第三个星期五上午10:15执行一次	　　　　0  15  10  ?  *  6#3
//    每月一日凌晨2点30执行一次	　　　　0  30  2  1  *  ?
//    每分钟的第10秒与第20秒都会执行	　　　　10,20  *  *  *  * ?
//    每月的第2个星期的周5，凌晨执行	　　　　0  0  0  ?  *  6#2

//    @Scheduled(fixedRate = 2000)
//    public void fixedRate() {
//        System.out.println("fixedRate>>>"+new Date());
//    }
//    @Scheduled(fixedDelay = 2000)
//    public void fixedDelay() {
//        System.out.println("fixedDelay>>>"+new Date());
//    }
//    @Scheduled(initialDelay = 2000,fixedDelay = 2000)
//    public void initialDelay() {
//        System.out.println("initialDelay>>>"+new Date());
//    }

//    @Scheduled(cron = "0/5 * * * * *")
//    public void cron() {
//        System.out.println(new Date());
//    }
}
