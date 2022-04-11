package com.example.async_demo.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class PiceaServiceImpl implements PiceaService{
    @Async
    @Override
    public void asyncTask() throws Exception {
            System.out.println("无返回异步线程，线程名：" + Thread.currentThread().getName());
            System.out.println("无返回异步处理方法-----start-------");
            Thread.sleep(5000);
            System.out.println("无返回异步处理方法------end--------");
    }
    @Async
    @Override
    public Future<String> asyncTaskFuture() throws Exception {
        System.out.println("有返回睡眠异步线程，线程名：" + Thread.currentThread().getName());
        System.out.println("有返回睡眠异步处理方法-----start-------1---");
        int k = 1;
        Thread.sleep(5000);
        System.out.println("有返回睡眠异步处理方法-----end----------1--");
        return new AsyncResult<String>(String.valueOf(k));
    }
    @Async
    @Override
    public Future<String> asyncTaskFuture2() throws Exception {
        System.out.println("有返回异步线程，线程名：" + Thread.currentThread().getName());
        System.out.println("有返回异步处理方法-----start------------2---");
        int k = 2;
        System.out.println("有返回异步处理方法-----end--------------2---");
        return new AsyncResult<String> (String.valueOf(k));
    }

}
