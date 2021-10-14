import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Title: DataTest
 * @Description: 测试redis
 * @author: gaoshu
 * @date: 2021/10/14 16:46
 */
public class RedisTestStr {

    /**
     * 应用场景
     * 1.缓存常用信息
     * 2.计数/秒杀
     * 3.分布式锁
     * 4.限制访问次数，
     */

    /**
     * Description:redis的字符串
     * set,get,用于存放字符串数据
     * incr，用于自增长，——————访问量
     * decr 删除指定整数————————限制访问量
     * incrby, 增加指定整数,decrby
     * append , 尾部追加字符串
     * setex, 设置生命周期
     * setnx, 实现分布式锁
     * @return
     */
    private Jedis getJedis(){
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123456");
        System.out.println("连接本地的 Redis 服务成功！");
        return jedis;
    }
    @Test
    public void demo0(){
        Jedis jedis = getJedis();
        String name = jedis.get("name");
        System.out.println(jedis.get("name"));
    }

    /**
     * 字符串的添加获取
     */
    @Test
    public void demo1(){
        Jedis jedis = getJedis();
        jedis.set("name", "zhangsan");
        System.out.println(jedis.get("name"));
    }

    /**
     * 字符串生成自增id
     */
    @Test
    public void demo2(){
        Jedis jedis = getJedis();
        jedis.incr("num");
        System.out.println(jedis.get("num"));
    }

    /**
     * 字符串生成自增id
     */
    @Test
    public void demo3(){
        Jedis jedis = getJedis();
        jedis.incrBy("num1", 2);
        System.out.println(jedis.get("num1"));
    }

    /**
     * 控制生命周期
     */
    @Test
    public void demo4() throws InterruptedException {
        Jedis jedis = getJedis();
        jedis.setex("name2", 2, "123");
        Thread.sleep(2000L);
        System.out.println(jedis.get("name2"));
    }


}
