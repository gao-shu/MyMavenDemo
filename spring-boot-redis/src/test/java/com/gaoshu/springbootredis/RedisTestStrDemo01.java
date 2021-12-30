package com.gaoshu.springbootredis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Title: DataTest
 * @Description: 测试redis
 * @author: gaoshu
 * @date: 2021/10/14 16:46
 */
public class RedisTestStrDemo01 {

    /**
     * 四大应用场景
     * 1.缓存常用信息
     */

    /**
     * 1，登录用户缓存
     */



    private Jedis getJedis(){
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接本地的 Redis 服务成功！");
        return jedis;
    }
    @Test
    public void demo0(){

    }


}
