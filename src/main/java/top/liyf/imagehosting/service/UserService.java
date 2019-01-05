package top.liyf.imagehosting.service;

import top.liyf.imagehosting.model.SysPermission;
import top.liyf.imagehosting.model.SysRole;
import top.liyf.imagehosting.model.User;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;


/**
 * @author liyf
 * Created in 2018-12-20
 */
public interface UserService {

    /**
     * 功能描述:
     *
     * @param username
     * @return top.liyf.imagehosting.model.User
     * @author liyf
     */
    User getUserByUserName(String username);

    /**
     * 功能描述: 获取用户角色
     *
     * @param uid 用户id
     * @return top.liyf.imagehosting.model.SysRole
     * @author liyf
     */
    Set<String> getRoleByUid(int uid);


    Set<String> getPermissionByUid(int uid);
}
