package com.example.async_demo;

import com.example.async_demo.Thread.ExecutorTest;
import com.example.async_demo.Thread.ExecutorTest1;
import com.example.async_demo.Thread.RunnableTest;
import com.example.async_demo.Thread.ThreadTest;
import com.example.async_demo.demo.PiceaContoller;
import com.example.async_demo.demo.PiceaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;

@SpringBootTest
class AsyncDemoApplicationTests {

    @Autowired
    private PiceaContoller piceaContoller;

    @Autowired
    private ThreadTest threadTest;

    @Autowired
    private RunnableTest runnableTest;

    @Autowired
    private ExecutorTest executorTest;

    @Autowired
    private ExecutorTest1 executorTest1;

    @Test
    void contextLoads() throws Exception {
        piceaContoller.asyncTask();
    }

    @Test
    void test1() throws Exception {
        String asyncTaskFuture = piceaContoller.asyncTaskFuture();
        System.out.println(asyncTaskFuture);
    }

    @Test
    void test2() throws Exception {
        threadTest.test();
    }

    @Test
    void test3() throws Exception {
        runnableTest.test();
    }

    @Test
    void test4() throws Exception {
        executorTest.test();
    }

    @Test
    void test5() throws Exception {
        executorTest1.test();
    }


}
