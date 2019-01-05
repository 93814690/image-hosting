package top.liyf.imagehosting.service.impl;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liyf
 * Created in 2018-12-30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserServiceImpl {


    @Test
    public void testMD5() {
        String result = new SimpleHash("md5","111","testabcd",1024).toHex();
        System.out.println("result = " + result);
    }
}