package top.liyf.imagehosting.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.liyf.imagehosting.model.User;

import java.util.Set;

/**
 * @author liyf
 * Created in 2018-12-19
 */
@Repository
public interface UserDao {

    /**
     * 功能描述: 保存用户
     *
     * @param user user实体类
     * @return int
     * @author liyf
     */
    int saveUser(User user);

    //SysRole getUserRole(@Param("uid") int uid);

    //List<SysPermission> getPermissionByRid(@Param("rid") int rid);

    Set<String> getPermissionByUId(@Param("uid") int uid);

    @Select("select * from t_user where username=#{username}")
    @Results({
            @Result(property = "registerTime", column = "register_time"),
            @Result(property = "loginTime", column = "login_time")
    })
    User getUserByUserName(@Param("username") String username);

    Set<String> getRoleByUid(@Param("uid") int uid);
}
