package com.example.async_demo.Thread;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadTest {

    @RequestMapping("/thread")
    public void test() {
        System.out.println(11);
        ThreadTest01 test01 = new ThreadTest01();
        test01.start();
        System.out.println(22);
    }

    public class ThreadTest01 extends Thread {

        @Override
        public void run() {
            System.out.println("当前线程:"+Thread.currentThread( ).getId());
            int i = 10 / 2;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("运行结果:"+i);
        }
    }
}




