package top.liyf.imagehosting.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.liyf.imagehosting.model.User;

import java.util.Date;

/**
 * @author liyf
 * Created in 2018-12-20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("test");
        user.setSalt("abcd");
        user.setPassword("111");
        user.setRegisterTime(new Date());
        user.setLoginTime(new Date());
        int i = userDao.saveUser(user);
        System.out.println("i = " + i);
    }

    public void test() {

    }
}