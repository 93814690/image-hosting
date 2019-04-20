package top.liyf.imagehosting.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.liyf.imagehosting.model.WeChatUser;

import static org.junit.Assert.*;

/**
 * @author liyf
 * Created in 2019-04-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWeChatUserDao {

    @Autowired
    private WeChatUserDao weChatUserDao;

    @Test
    public void getUserByOpenId() {
        String openId = "sdfjs";
        WeChatUser userByOpenId = weChatUserDao.getUserByOpenId(openId);
        System.out.println("userByOpenId = " + userByOpenId);
    }
}