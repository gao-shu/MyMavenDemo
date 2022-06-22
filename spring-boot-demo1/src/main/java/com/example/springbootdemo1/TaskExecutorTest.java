package com.example.springbootdemo1;

public class TaskExecutorTest {



    public static void main(String[] args) throws InterruptedException {
        //新建线程并执行任务类
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "：异步任务");
        }).start();
        Thread.sleep(3000);
    }
}
