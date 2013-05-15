/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.User;
import com.myapp.main.Category;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.util.WebUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class LoginAction111 extends ActionSupport {

    private String username;
    private String password;
    private String rememberMe;
    User user = null;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(LoginAction111.class);

    //business logic
//    @Override
    public String loginForm() {
        logger.debug("LoginAction execute!");
        return SUCCESS;
    }

    public String login() {
        logger.debug("LoginAction execute!");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String returnVal = "success";

        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = credentialDAO.selectCredential(getUsername());

        Set<User> users = credential.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            user = (User) iterator.next();
        }

        logger.debug("before state DAO1:" + getUsername());

        Utils.recordLoginLog(user.getUserId(), request);
        session.setAttribute("user", user);

        logger.debug("execute state DAO2:" + user.getFirstName());
        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());
        logger.debug("returnVal:" + returnVal);

        return SUCCESS;
    }

    //simple validation
    @Override
    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        if (getActionName().equals("login_form")) {
            logger.debug("login_form");
        } else if (getActionName().equals("login")) {
            logger.debug("username:" + getUsername());
            logger.debug("password:" + getPassword());

            // code for authentication
            try {
                Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
                org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
                SecurityUtils.setSecurityManager(securityManager);

                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword());
                subject.login(token);
                logger.debug("After subject.login");

                if (getRememberMe().equals("true")) {
                    token.setRememberMe(true);
                    logger.debug("User RememberMe: Setting to TRUE");
                }
                token.clear();
                logger.debug("User is authenticated:" + subject.isAuthenticated());
                logger.debug("User RememberMe:" + token.isRememberMe());

//                AuthenticationToken token1 = new UsernamePasswordToken(username, password);
//                subject.login(token1);
//                WebUtils.redirectToSavedRequest(request, response, "login");

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
//                Session session = subject.getSession();

                if (subject.isAuthenticated()) {
                    logger.debug("user authenticated successfully");
                } else {
//                    addActionError("Error");
//                    addActionMessage("This user does not exist. Do you want to register?");
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
        logger.debug("in the validate of LoginAction");
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Set<Category> userCategories) {
        this.userCategories = userCategories;
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
