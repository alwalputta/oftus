/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.User;
import com.myapp.main.Bookmark;
import com.myapp.main.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.Set;
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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private String rememberMe;
    boolean loggedIn = false;
    Credential credential = null;
    User user = null;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(LoginAction.class);

    //business logic
//    @Override
    public String logIn() {
        logger.debug("LoginAction execute!");

        String returnVal = "success";

        CredentialDAO credentialDAO = new CredentialDAO();
        credential = credentialDAO.selectCredential(getUsername());

        Set<User> users = credential.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            user = (User) iterator.next();
        }

        logger.debug("before state DAO1:" + getUsername());
        logger.debug("execute state DAO2:" + user.getFirstName());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        logger.debug("111111111111");
        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

//        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
//            logger.debug("222222222222");
//            Category c = (Category) iterator.next();
//            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName() + ":"
//                    + c.getDescription());
//            Set<Bookmark> bookmarks = c.getBookmarks();
//
//            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
//                Bookmark b = (Bookmark) i.next();
//                logger.debug("bookmarkId value:" + b.getBookmarkId() + ":" + b.getBookmarkName() + ":"
//                        + b.getHiperLink() + ":" + b.getDescription());
//            }
//        }
        logger.debug("end of LoginAction execute.");
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        if (getActionName().equals("login")) {

            logger.debug("username:" + getUsername());
            logger.debug("password:" + getPassword());

            // code for authentication
            try {
                Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
                org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
                SecurityUtils.setSecurityManager(securityManager);

                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
                logger.debug("User RememberMe:" + getRememberMe());

                if (getRememberMe().equals("true")) {
                    token.setRememberMe(true);
                }
                token.clear();
                logger.debug("User is authenticated:" + subject.isAuthenticated());

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
                Session session = subject.getSession();

                if (subject.isAuthenticated()) {
                    logger.debug("user authenticated successfully");
                } else {
                    addFieldError("username", "This user does not exist. Do you want to register?");
                    addActionMessage("This user does not exist. Do you want to register?");
                }
            } catch (IncorrectCredentialsException ex) {
                addActionMessage("This user does not exist. Do you want to register?");
                ex.printStackTrace();
            } catch (LockedAccountException ex) {
                addActionMessage("This user does not exist. Do you want to register?");
                ex.printStackTrace();
            } catch (UnknownAccountException ex) {
                addActionMessage("This user does not exist. Do you want to register?");
                ex.printStackTrace();
            } catch (AuthenticationException ex) {
                addActionMessage("This user does not exist. Do you want to register?");
                ex.printStackTrace();
            } catch (ConfigurationException ex) {
                addActionMessage("This user does not exist. Do you want to register?");
                ex.printStackTrace();
            } catch (Exception ex) {
                addActionMessage("This user does not exist. Do you want to register?");
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
