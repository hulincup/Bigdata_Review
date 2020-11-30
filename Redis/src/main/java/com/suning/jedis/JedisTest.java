package com.suning.jedis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @Author lynn
 * @Date 2020/7/25 21:01
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.101", 6379);
        System.out.println(jedis.ping());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "zhangsan");
        jsonObject.put("age", "18");

        //清空当前数据库
        jedis.flushDB();
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toString();
        multi.set("user1",result);
        multi.set("user2",result);

        multi.exec();
        System.out.println(jedis.get("user1"));
        System.out.println(jedis.get("user2"));
        jedis.close();


    }

}
