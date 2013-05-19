/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author palwal
 */
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFormAuthenticationFilter extends PassThruAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("onAccessDenied");
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
//    @Override
//    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
//        logger.debug("onLoginSuccess");
//        return true;
//    }
//
//    @Override
//    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
//        logger.debug("onLoginFailure");
//        return true;
//    }
}
