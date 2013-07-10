/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.Email;
import com.myapp.admin.User;
import com.myapp.util.SendMail;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.EmailException;
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
public class LoginInterceptor extends AbstractInterceptor implements Interceptor, StrutsStatics {

    static final Logger logger = Logger.getLogger(LoginInterceptor.class);
    private static final String LOGGED_IN = "loggedIn";
    private static final String LOGIN_ATTEMPT = "loginAttempt";
    private static final String USER_HANDLE = "user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String REMEMBER_ME = "rememberMe";
    private static final String LOGIN_PAGE = "login";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String actionName = invocation.getProxy().getMethod();
        //String className = invocation.getAction().getClass().getName();
        logger.debug("Before action1: " + actionName);
        //logger.debug("Before action2: " + className);

        if (actionName.equals("loginForm")
                || actionName.equals("registerForm")
                || actionName.equals("createProfile")
                || actionName.equals("activateProfile")
                || actionName.equals("feedbackForm")
                || actionName.equals("saveFeedback")
                ) {
            logger.debug("Open actions1: " + actionName);
            return invocation.invoke();
        }

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        String loginAttempt = (String) session.getAttribute(LOGIN_ATTEMPT);
        logger.debug("Login attempt: " + loginAttempt);

        User user = (User) session.getAttribute(USER_HANDLE);
        if (user == null) {
            logger.debug("User is null");
            String username = request.getParameter(USERNAME);
            String password = request.getParameter(PASSWORD);

            //Set login attempt
            if (loginAttempt == null) {
                session.setAttribute(LOGIN_ATTEMPT, "1");
            } else {
                logger.debug("User is not logged in ......");
                int i = new Integer(loginAttempt).intValue();
                i++;
                session.setAttribute(LOGIN_ATTEMPT, i + "");

                if (new Integer(loginAttempt).intValue() > 5) {
                    Object action = invocation.getAction();
                    ((ValidationAware) action).addActionError("You exceeded your login attempts; "
                            + "Please log back after sometime.");
                    return "login_form_redirect";
                }
            }

            if (username == null || "".equals(username)) {
                logger.debug("Username is null");
                Object action = invocation.getAction();
                ((ValidationAware) action).addActionError("Username is empty.");
                return "login_form_redirect";
            }
            if (password == null || "".equals(password)) {
                logger.debug("Password is null");
                Object action = invocation.getAction();
                ((ValidationAware) action).addActionError("Password is empty.");
                return "login_form_redirect";
            }
            boolean b = login(invocation);
            if (b) {
                Object action = invocation.getAction();
                ((ValidationAware) action).addActionMessage("User logged in successfully.");
            } else {
                Object action = invocation.getAction();
                ((ValidationAware) action).addActionError("User does not exist.");
                return "login_form_redirect";
            }
        } else {
            session.setAttribute(LOGIN_ATTEMPT, "1");
        }
        return invocation.invoke();
    }

    @Override
    public void init() {
        logger.debug("Initializing ShiroInterceptor...");
    }

    @Override
    public void destroy() {
        logger.debug("Destroying ShiroInterceptor...");
    }

    private boolean login(ActionInvocation invocation) throws EmailException {
        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) context.get(HTTP_RESPONSE);
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute(USER_HANDLE);
        if (user == null) {
            logger.debug("User is null");
            if (processLoginAttempt(request, response)) {
                logger.debug("User logged in successfully");
                CredentialDAO credentialDAO = new CredentialDAO();
                Credential credential = credentialDAO.selectCredential(request.getParameter(USERNAME));

                if (credential == null) {
                    logger.debug("userId:" + request.getParameter(USERNAME));
                    credential = credentialDAO.selectInactiveCredential(request.getParameter(USERNAME));

                    if (credential == null) {
                        Object action = invocation.getAction();
                        ((ValidationAware) action).addActionError("You dont have an active login account.");
                        return false;
                    }

                    List<User> users = credential.getUsers();
                    User u = null;
                    for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                        u = (User) iterator.next();
                    }

                    if (u == null) {
                        return false;
                    } else {
                        List<Email> userEmails = new ArrayList<Email>();
                        userEmails = u.getUserEmails();
                        Email email = null;
                        for (Iterator iterator = userEmails.iterator(); iterator.hasNext();) {
                            email = (Email) iterator.next();
                        }
                        logger.debug("email address:" + email.getEmailAddress());
                        SendMail.sendActivateEmail(request, u, email.getEmailAddress());
                    }
                    return false;
                } else {
                    List<User> users = credential.getUsers();
                    for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                        user = (User) iterator.next();
                    }
                    session.setAttribute(USER_HANDLE, user);
                    Utils.recordLoginLog(user.getUserId(), request);
                }
            } else {
                Object action = invocation.getAction();
                logger.debug("User action:" + action);
                if (action instanceof ValidationAware) {
                    logger.debug("Before ValidationAware....");
                    ((ValidationAware) action).addActionError("Username or password incorrect.");
                    logger.debug("After ValidationAware....");
                }
            }
        } else {
            logger.debug("User not null....");
        }
        return true;
    }

    public boolean processLoginAttempt(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String rememberMe = request.getParameter(REMEMBER_ME);
        Subject subject = null;
        UsernamePasswordToken token = null;

        // Use the security manager to validate the user's username and password.
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
            //WebUtils.redirectToSavedRequest(request, response, LOGIN_PAGE);

            if (rememberMe != null && rememberMe.equals("true")) {
                token.setRememberMe(true);
            } else {
                logger.debug("After remember me is not set");
            }
            logger.debug("Before login....");
            token.clear();
            logger.debug("User authenticated:" + subject.isAuthenticated());

            if (subject.isAuthenticated()) {
                session.setAttribute(LOGGED_IN, "true");
                logger.debug("user authenticated successfully");
            }
            if (token.isRememberMe()) {
                logger.debug("rememberMe true....");
            } else {
                logger.debug("rememberMe is false....");
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
        return true;
    }
}
