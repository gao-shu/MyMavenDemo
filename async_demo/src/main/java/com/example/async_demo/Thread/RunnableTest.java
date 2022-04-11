package com.example.async_demo.Thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunnableTest {

    @RequestMapping("/runnable")
    public void test() {
        System.out.println(11);
        RunnableTest01 test01 = new RunnableTest01();
        Thread thread = new Thread(test01);
        thread.start();
        System.out.println(22);
    }

    public class RunnableTest01 implements Runnable {

        @Override
        public void run() {
            System.out.println("当前线程:"+Thread.currentThread( ).getId());
            int i = 10 / 2;
            try {
//                Thread.sleep((int) Math.random() * 10);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("运行结果:"+i);
        }
    }
}




