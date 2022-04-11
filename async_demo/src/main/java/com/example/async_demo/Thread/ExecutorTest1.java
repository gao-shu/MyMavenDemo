package com.example.async_demo.Thread;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class ExecutorTest1 implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ExecutorTest1.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    @RequestMapping("/taskExecutor")
    public void test() {
        Callable01 callable = new Callable01();
        TaskExecutor taskExecutor = ExecutorTest1.getBean(TaskExecutor.class);
        taskExecutor.execute(callable);
        System.out.println("吃饭");
    }


    public static class Callable01 implements Runnable {
        @Override
        public void run() {
//            try {
//                Thread.currentThread().wait(30);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("睡觉");
        }
    }
}




