package top.liyf.imagehosting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liyf.imagehosting.dao.UserDao;
import top.liyf.imagehosting.model.User;
import top.liyf.imagehosting.service.UserService;

import java.util.Set;

/**
 * @author liyf
 * Created in 2018-12-20
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByUserName(String username) {
        return userDao.getUserByUserName(username);
    }

    @Override
    public Set<String> getRoleByUid(int uid) {
        return userDao.getRoleByUid(uid);
    }

    @Override
    public Set<String> getPermissionByUid(int uid) {
        return userDao.getPermissionByUId(uid);
    }
}
