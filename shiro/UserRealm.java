package com.xuan.json.config;

import com.xuan.json.pojo.User;
import com.xuan.json.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("realm1权限");
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String[] split = user.getPermission().split(",");
        simpleAuthorizationInfo.addStringPermissions(Arrays.asList(split));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("realm1密码");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        User user = userService.byCount(token.getUsername());
        if(user==null)
        {
            return  null;
        }
 /*       Subject subject= SecurityUtils.getSubject();
        subject.getSession().setAttribute("key",user);*/
        ByteSource credentialsSalt = ByteSource.Util.bytes("xuan");
        return new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,getName());
    }
}
