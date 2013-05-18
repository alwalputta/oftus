/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
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
public class ShiroInterceptor1 extends AbstractInterceptor implements Interceptor, StrutsStatics {

    static final Logger logger = Logger.getLogger(ShiroInterceptor1.class);
    private static final String LOGGED_IN = "loggedIn";
    private static final String LOGIN_ATTEMPT = "loginAttempt";
    private static final String USER_HANDLE = "userHandle";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REMEMBER_ME = "rememberMe";
    private static final String LOGIN_PAGE = "index";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        String className = invocation.getAction().getClass().getName();
        logger.debug("Before action: " + className);

//        long startTime = System.currentTimeMillis();
//        String result = invocation.invoke();
//        long endTime = System.currentTimeMillis();
//        logger.debug("After calling action: " + className + " Time taken: " + (endTime - startTime) + " ms");

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        String loginAttempt = (String) session.getAttribute(LOGIN_ATTEMPT);
        logger.debug("Login attempt: " + loginAttempt);

        /*
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
         */

        logger.debug("After Password is null....");

        // Get the action context from the invocation so we can access the
        // HttpServletRequest and HttpSession objects.

        // Is there a "user" object stored in the user's HttpSession?
        Object user = session.getAttribute(USER_HANDLE);
        if (user == null) {
            // The user has not logged in yet.
            logger.debug("loginAttempt before...." + loginAttempt);
            if (!StringUtils.isBlank(loginAttempt)) { // The user is attempting to log in.
                logger.debug("Login attempt after....");
                // Process the user's login attempt.
                if (processLoginAttempt(request, session)) {
                    // The login succeeded send them the login-success page.
                    logger.debug("After Password is null....");
                    return "login-success";
                } else {
                    // The login failed. Set an error if we can on the action.
                    logger.debug("After Password is null....");
                    Object action = invocation.getAction();
                    if (action instanceof ValidationAware) {
                        logger.debug("After Password is null....");
                        ((ValidationAware) action).addActionError("Username or password incorrect.");
                    }
                }
            }
            // Either the login attempt failed or the user hasn't tried to login yet, 
            // and we need to send the login form.
            logger.debug("After Password is null....");
            return LOGIN_PAGE;
        } else {
            logger.debug("After Password is null....");
            return invocation.invoke();
        }
    }

    @Override
    public void destroy() {
        logger.debug("Destroying ShiroInterceptor...");
    }

    @Override
    public void init() {
        logger.debug("Initializing ShiroInterceptor...");
    }

    /**
     * Attempt to process the user's login attempt delegating the work to the
     * SecurityManager.
     */
    public boolean processLoginAttempt(HttpServletRequest request, HttpSession session) {
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String rememberMe = request.getParameter(REMEMBER_ME);
        Subject user = null;
        UsernamePasswordToken token = null;

        // Use the security manager to validate the user's username and password.
        try {
            Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
            org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);

            user = SecurityUtils.getSubject();
            token = new UsernamePasswordToken(username, password);
            user.login(token);
            logger.debug("Username:" + username);
            logger.debug("Password:" + password);
            logger.debug("RememberMe:" + rememberMe);

            if (rememberMe != null && rememberMe.equals("true")) {
                logger.debug("After Password is null....");
                token.setRememberMe(true);
            }
            logger.debug("After Password is null....");
            token.clear();
            logger.debug("User authenticated:" + user.isAuthenticated());

            if (user.isAuthenticated()) {
                logger.debug("After Password is null....");
                session.setAttribute(LOGGED_IN, "true");
                logger.debug("user authenticated successfully");
            }
            logger.debug("After Password is null....");
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

        if (user != null) {
            // The user has successfully logged in. Store their user object in 
            // their HttpSession. Then return true.
            logger.debug("After Password is null....");
            session.setAttribute(USER_HANDLE, user);
            return true;
        } else {
            // The user did not successfully log in. Return false.
            logger.debug("After Password is null....");
            return false;
        }
    }
}
