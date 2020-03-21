package com.xuan.json.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
      /*  shiro过滤器：
        anon:无需认证(登录)可以访问
        authc:必须认证才可以访问
        user:如果使用rememberMe的功能可以直接访问
        perms:该资源必须得到资源权限才可以访问
        role:该资源必须得到角色权限才可以访问*/
        Map<String,String> filtermap=new LinkedHashMap<>();
        filtermap.put("/login","anon");
        filtermap.put("/add","perms[user:add]");
        filtermap.put("/add2","perms[user:add2]");
        filtermap.put("/update","perms[user:update,user:add]");
        filtermap.put("/index","user");
        filtermap.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filtermap);
        shiroFilterFactoryBean.setUnauthorizedUrl("/noPer");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }


    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm,
                                                               @Qualifier("userRealm2") UserRealm2 userRealm2,
                                                               @Qualifier("authenticator") ModularRealmAuthenticator modularRealmAuthenticator,
                                                               @Qualifier("cookieRememberMeManager")CookieRememberMeManager cookieRememberMeManager)
    {
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager(Arrays.asList(userRealm,userRealm2));
        //设置多realm管理 可写可不写 因为启动时 DefaultWebSecurityManager会把多realm再 setAuthenticator(modularRealmAuthenticator);
        //defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator);
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return  defaultWebSecurityManager;
    }

    @Bean("authenticator")
    public ModularRealmAuthenticator modularRealmAuthenticator(@Qualifier("userRealm") UserRealm userRealm,@Qualifier("userRealm2") UserRealm2 userRealm2)
    {
        ModularRealmAuthenticator modularRealmAuthenticator=new ModularRealmAuthenticator();
        //设置多realm
        modularRealmAuthenticator.setRealms(Arrays.asList(userRealm,userRealm2));
        //AllSuccessfulStrategy :所有Realm验证成功才算成功,且返回所有Realm身份验证成功的认证信息,如果有一个失败就失败了。
        //modularRealmAuthenticator.setAuthenticationStrategy(new AllSuccessfulStrategy());
        //AtLeastOneSuccessfulStrategy :默认值   只要有一个Realm验证成功即可, 和FirstSuccessfulStrategy不同,将返回所有Realm身份验证成功的认证信息
        //modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        //FirstSuccessfulStrategy.:只要有一个Realm验证成功即可,只返回第-个Realm身份验证成功的认证信息,其他的忽略
       // modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return modularRealmAuthenticator;
    }
    @Bean("cookieRememberMeManager")
    public CookieRememberMeManager cookieRememberMeManager()
    {
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
        SimpleCookie simpleCookie=new SimpleCookie();
        simpleCookie.setName("remme");
        //设置记住我过期时间
        simpleCookie.setMaxAge(10);
        cookieRememberMeManager.setCookie(simpleCookie);
        return  cookieRememberMeManager;
    }
    @Bean("userRealm")
    public UserRealm getUserRealm(@Qualifier("credentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher)
    {
        UserRealm u=new UserRealm();
        u.setCredentialsMatcher(hashedCredentialsMatcher);
        return  u;
    }
    //多real配置
    @Bean("userRealm2")
    public UserRealm2 getUserRealm2(@Qualifier("credentialsMatcher2") HashedCredentialsMatcher hashedCredentialsMatcher)
    {
        UserRealm2 u=new UserRealm2();
        u.setCredentialsMatcher(hashedCredentialsMatcher);
        return  u;
    }

    @Bean("credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法的名字，也可以设置MD5等其他加密算法名字
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    @Bean("credentialsMatcher2")
    public HashedCredentialsMatcher credentialsMatcher2(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //加密算法的名字，也可以设置MD5等其他加密算法名字
        credentialsMatcher.setHashAlgorithmName("sha");
        //加密次数
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }
    @Bean
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }

}
