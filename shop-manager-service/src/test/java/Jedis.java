import org.junit.Test;
import redis.clients.jedis.JedisPool;

public class Jedis {


    @Test
    public void test() {
        redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("192.168.52.135", 6379);
        jedis.auth("admin");
        jedis.select(2);
        String ping = jedis.ping();
        System.out.println(ping);
        String key = "jedis";
        String setResult = jedis.set(key, "hello redis!");
        System.out.println(setResult);

        String getResult = jedis.get(key);

        System.out.println(getResult);

        // 3. 释放资源，关闭jedis
        jedis.close();
    }

    @Test
    public void jedisPoolTest() {
        JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
        redis.clients.jedis.Jedis jedis = jedisPool.getResource();
        jedis.select(2);
        String pong = jedis.ping();
        System.out.println(pong);

        String key = "jedisPoll";
        String setResult = jedis.set(key, "hello redis!");
        System.out.println(setResult);

        String getResult = jedis.get(key);
        System.out.println(getResult);

        // 4.释放资源，每次用完jedis连接，必须close（）,指的就是还回连接池
        jedis.close();

        // 程序结束，销毁连接池
        jedisPool.close();

    }
}
