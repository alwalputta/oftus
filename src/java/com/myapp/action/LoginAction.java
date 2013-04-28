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
    private String rememberme;
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

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            logger.debug("222222222222");
            Category c = (Category) iterator.next();
            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());
            Set<Bookmark> bookmarks = c.getBookmarks();

            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
                Bookmark b = (Bookmark) i.next();
                logger.debug("bookmarkId value:" + b.getBookmarkId() + ":" + b.getBookmarkName() + ":"
                        + b.getHiperLink() + ":" + b.getDescription());
            }
        }
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        if (getActionName().equals("login")) {
            CredentialDAO credentialDAO = new CredentialDAO();
            credential = credentialDAO.selectCredential(getUsername());

            if (credential == null) {
                addActionMessage("This user does not exist. Do you want to register?");
                addFieldError("username", getText("userdoesnotexist"));
                credential = new Credential();
            } else if ((getUsername().equals(credential.getUsername())) && getPassword().equals(credential.getPassword())) {
                logger.debug("before state DAO2:" + credential.getUsername());
            } else {
                addActionMessage("This username and password is not registered.");
                addFieldError("username", getText("userdoesnotexist"));
            }
        } else if (getActionName().equals("add_bookmark")) {
            logger.debug("add_bookmark");
        }

///////////////
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

            // Setting up the SecurityManager...
            org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
            SecurityUtils.setSecurityManager(securityManager);

            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            token.clear();
            logger.debug("User is authenticated:" + subject.isAuthenticated());

            if (subject.hasRole("user:user")) {
                logger.debug("user has user role");
            }
            if (subject.isPermitted("user:admin")) {
                logger.debug("user has admin role");
            }
            Session session = subject.getSession();
        } catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
        } catch (UnknownAccountException ex) {
            ex.printStackTrace();
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("in the validate of LoginAction");
    }

    public String getRememberme() {
        return rememberme;
    }

    public void setRememberme(String rememberme) {
        this.rememberme = rememberme;
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
