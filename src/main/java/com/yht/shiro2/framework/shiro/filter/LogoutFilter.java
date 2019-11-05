package com.yht.shiro2.framework.shiro.filter;

import com.yht.shiro2.framework.shiro.utils.ShiroUtils;
import com.yht.shiro2.project.user.entity.User;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 退出过滤器
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向URL
     */
    private String logoutUrl;

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            Subject subject = getSubject(request,response);
            String redirectUrl = getRedirectUrl(request,response,subject);
            try {
                User user = (User) ShiroUtils.getSubject().getPrincipal();

                if (user != null){
                    String username = user.getLoginName();
                    log.info(username+"推出登陆");
                }
                subject.logout();
            } catch (SessionException e) {
                log.error("logout fail",e);
            }
            issueRedirect(request,response,redirectUrl);
        } catch (Exception e) {
            log.debug("error");
        }
        return false;
    }

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        String url = getLogoutUrl();
        if (url != null){
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}
