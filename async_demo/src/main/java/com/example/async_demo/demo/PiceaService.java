package com.example.async_demo.demo;

import java.util.concurrent.Future;

public interface PiceaService {
    //无返回参数方法
    void asyncTask() throws Exception;
    //有返回参数方法
    Future<String> asyncTaskFuture() throws Exception;
    //有返回参数方法2
    Future<String> asyncTaskFuture2() throws Exception;
}
