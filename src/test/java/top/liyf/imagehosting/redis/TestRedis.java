package top.liyf.imagehosting.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import top.liyf.imagehosting.model.User;

import java.util.concurrent.TimeUnit;

/**
 * @author liyf
 * Created in 2019-04-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void test2() throws InterruptedException {
        User user = new User();
        user.setUsername("张三");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("test1", user);
        operations.set("test2", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1100);
        System.out.println("user = " + user);
        boolean exists=redisTemplate.hasKey("test2");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }
}
