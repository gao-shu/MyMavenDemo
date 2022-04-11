package com.example.async_demo.Thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
public class ExecutorTest {

    @RequestMapping("/executor")
    public void test() {
        System.out.println("main 开始");
        //线程池可以控制服务器资源，保持服务器线程数量不至于服务器压力过大
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Future<Integer> submit = executorService.submit(new Callable01());
        try {
            //不get不会阻塞主线程
            Integer integer = submit.get();
            System.out.println("线程结果"+integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main 结束");
    }

    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws InterruptedException {
            System.out.println("当前线程:"+Thread.currentThread( ).getId());
            int i = 10 / 2;
            System.out.println("运行结果:"+i);
            return i;

        }
    }
}




