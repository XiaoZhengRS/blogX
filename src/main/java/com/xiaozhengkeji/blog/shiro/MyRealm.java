package com.xiaozhengkeji.blog.shiro;


import com.xiaozhengkeji.blog.entity.User;
import com.xiaozhengkeji.blog.jwt.JWTToken;
import com.xiaozhengkeji.blog.jwt.JWTUtil;


import com.xiaozhengkeji.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Slf4j
@Service
public class MyRealm extends AuthorizingRealm {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("进入权限判断");
        String username = JWTUtil.getUsername(principals.toString());
        User 查询条件 = new User();
        查询条件.setUsername(username);
        User user = userService.queryAll(查询条件).get(0);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRole());
        simpleAuthorizationInfo.addStringPermission(user.getRole());
        /*List<String> permissions = new ArrayList<>();
        for(XZPermissionPoJo permission : user.getPermissions())
        {
            permissions.add(permission.getPermission());
        }
        simpleAuthorizationInfo.addStringPermissions(permissions);*/
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("令牌无效");
        }
        User 查询条件 = new User();
        查询条件.setUsername(username);
        User userBean = userService.queryAll(查询条件).get(0);
        if (userBean == null) {
            throw new AuthenticationException("用户不存在!");
        }

        if (!JWTUtil.verify(token, username, userBean.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
