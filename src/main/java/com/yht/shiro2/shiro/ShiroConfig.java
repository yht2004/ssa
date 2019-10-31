package com.yht.shiro2.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 将自定义验证方式加入容器
     * @return
     */
    @Bean
    public UserRealm myUserRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean
    public SecurityManager securityManager(CacheManager cacheManager, SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(myUserRealm());
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> map = new HashMap<String,String>();
        //登出
        map.put("/logout","logout");
        //对所有用户认证
        map.put("/doLogin","anon");
        map.put("/**","authc");

        //登陆
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面,认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);


        return shiroFilterFactoryBean;
    }



    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public CacheManager cacheManager(){
        return new EhCacheManager();
    }

    @Bean
    public SessionDAO sessionDAO(){
        return new EnterpriseCacheSessionDAO();
    }


    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionDAO(sessionDAO);
        manager.setGlobalSessionTimeout(3600000);
        manager.setSessionValidationInterval(36000000);
        return manager;
    }
}
