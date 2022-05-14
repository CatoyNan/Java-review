package top.catoy;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.caoty.springbootredis.SpringBootRedisApplication;
import top.caoty.springbootredis.User;

import java.util.Map;

@SpringBootTest(classes = SpringBootRedisApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test1() {
        redisTemplate.opsForValue().set("key", "1");
        Object key = redisTemplate.opsForValue().get("key");
        System.out.println(key);
    }

    @Test
    public void test2() {
        redisTemplate.opsForValue().set("user:01", new User(12, "小明"));
        Object o = redisTemplate.opsForValue().get("user:01");
        System.out.println(o);
    }

    @Test
    public void test3() {
        stringRedisTemplate.opsForValue().set("user:02", JSON.toJSONString(new User(12, "小明")));
        String s = stringRedisTemplate.opsForValue().get("user:02");
        User user = JSON.parseObject(s, User.class);
        System.out.println(user);
    }

    @Test
    public void test4() {
        stringRedisTemplate.opsForHash().put("user:03", "name", "user3");
        stringRedisTemplate.opsForHash().put("user:04", "name", "user4");
        Object name = stringRedisTemplate.opsForHash().get("user:03", "name");
        System.out.println(name);
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:04");
        entries.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

}
