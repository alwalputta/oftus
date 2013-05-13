/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author palwal
 */
public class LoggingInterceptor extends AbstractInterceptor implements Interceptor, StrutsStatics {

    static final Logger logger = Logger.getLogger(LoggingInterceptor.class);
    private static final String LOGGED_IN = "loggedIn";
    private static final String LOGIN_ATTEMPT = "loginAttempt";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        String className = invocation.getAction().getClass().getName();
//        long startTime = System.currentTimeMillis();
        logger.debug("Before calling action: " + className);

//        String result = invocation.invoke();
//        long endTime = System.currentTimeMillis();
//        logger.debug("After calling action: " + className + " Time taken: " + (endTime - startTime) + " ms");

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        String loggedIn = (String) session.getAttribute(LOGGED_IN);
        String loginAttempt = (String) session.getAttribute(LOGIN_ATTEMPT);
        logger.debug("Login attempt: " + loginAttempt);

        if (loginAttempt == null) {
            session.setAttribute(LOGIN_ATTEMPT, "1");
        } else {
            int i = new Integer(loginAttempt).intValue();
            i++;
            session.setAttribute(LOGIN_ATTEMPT, i + "");

            if (new Integer(loginAttempt).intValue() > 5) {
                return "login_form_redirect";
            }
        }

        if (username == null || "".equals(username)) {
            logger.debug("Username is null");
            return "login_form_redirect";
        }
        if (password == null || "".equals(password)) {
            logger.debug("Password is null");
            return "login_form_redirect";
        }
        Subject subject = null;
        UsernamePasswordToken token = null;

        if (loggedIn == null || !loggedIn.equals("true")) {
            logger.debug("User is null, not logged in yet...");

            // code for authentication
            try {
                Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
                org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
                SecurityUtils.setSecurityManager(securityManager);

                subject = SecurityUtils.getSubject();
                token = new UsernamePasswordToken(username, password);
                subject.login(token);
                logger.debug("Username:" + username);
                logger.debug("Password:" + password);
                logger.debug("RememberMe:" + rememberMe);

                if (rememberMe != null && rememberMe.equals("true")) {
                    token.setRememberMe(true);
                }
                token.clear();
                logger.debug("User is authenticated:" + subject.isAuthenticated());

                if (subject.isAuthenticated()) {
                    session.setAttribute(LOGGED_IN, "true");
                    logger.debug("user authenticated successfully");
                } else {
                    logger.debug("user authentication failed");
                }
                if (subject.hasRole("user")) {
                    logger.debug("user has user role");
                } else {
                    logger.debug("user does not have user role");
                }
                if (subject.isPermitted("test")) {
                    logger.debug("user has admin role");
                } else {
                    logger.debug("user has no admin role");
                }
            } catch (IncorrectCredentialsException ex) {
                ex.printStackTrace();
            } catch (LockedAccountException ex) {
                ex.printStackTrace();
            } catch (UnknownAccountException ex) {
                ex.printStackTrace();
            } catch (AuthenticationException ex) {
                ex.printStackTrace();
            } catch (ConfigurationException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (subject.isAuthenticated()) {
            return invocation.invoke();
        } else {
            return "login_form_redirect";
        }
    }

    @Override
    public void destroy() {
        logger.debug("Destroying LoggingInterceptor...");
    }

    @Override
    public void init() {
        logger.debug("Initializing LoggingInterceptor...");
    }
}
