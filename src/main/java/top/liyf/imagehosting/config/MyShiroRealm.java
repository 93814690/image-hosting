package top.liyf.imagehosting.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import top.liyf.imagehosting.model.User;
import top.liyf.imagehosting.service.UserService;

import java.util.Set;

/**
 * @author liyf
 * Created in 2018-12-20
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 功能描述: 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     *
     * @param principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author liyf
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("获取角色信息："+user.getRoles());
        System.out.println("获取权限信息："+user.getPerms());
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPerms());
        return info;
    }

    /**
     * 功能描述: 定义如何获取用户信息的业务逻辑，给shiro做登录
     *
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author liyf
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        User userDB = userService.getUserByUserName(username);


        if (userDB == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        Set<String> roles = userService.getRoleByUid(userDB.getUid());
        Set<String> perms = userService.getPermissionByUid(userDB.getUid());
        userDB.getRoles().addAll(roles);
        userDB.getPerms().addAll(perms);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                userDB,
                userDB.getPassword(),
                ByteSource.Util.bytes(userDB.getCredentialsSalt()),
                getName());

        return info;

    }
}
