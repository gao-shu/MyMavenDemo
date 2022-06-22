package com.example.springbootdemo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

@SpringBootTest
public class ThreadPoolTest {

    @Autowired
    private TaskExecutor taskExecutor;// 线程池

    @Test
    // 将创建的线程添加到线程池中
    public void test() throws Exception {
        for (int i = 0; i < 10; i++) {
            this.taskExecutor.execute(new AppContentDataPushThread());
        }
    }
}
class AppContentDataPushThread implements Runnable {

    public AppContentDataPushThread() {
        System.out.println(123);
    }

    @Override
    public void run() {
        System.out.println("执行线程");
    }
}
