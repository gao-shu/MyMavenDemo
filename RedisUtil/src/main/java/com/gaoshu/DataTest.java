package com.gaoshu;

import redis.clients.jedis.Jedis;

/**
 * @Title: DataTest
 * @Description: redis测试
 * @author: gaoshu
 * @date: 2021/10/14 14:19
 */
public class DataTest {

    private Jedis jedis;

    public void connectRedis(){
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("admin");
    }
    
    public void demo1(){
        jedis.set("name", "zhangsan");
        System.out.println(jedis.get("name"));
    }

}
