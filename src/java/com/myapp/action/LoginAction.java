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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private String rememberMe;
    private String loginAttempt;
    boolean loggedIn = false;
    Credential credential = null;
    User user = null;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(LoginAction.class);

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
        credential = credentialDAO.selectCredential(getUsername());

        Set<User> users = credential.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            user = (User) iterator.next();
        }

        logger.debug("before state DAO1:" + getUsername());
        logger.debug("execute state DAO2:" + user.getFirstName());

        Utils.recordLoginLog(user.getUserId(), request);
        session.setAttribute("user", user);

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        if (getActionName().equals("login_form")) {
            logger.debug("login_form");
        } else if (getActionName().equals("login")) {
            logger.debug("username:" + getUsername());
            logger.debug("password:" + getPassword());
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

    public String getLoginAttempt() {
        return loginAttempt;
    }

    public void setLoginAttempt(String loginAttempt) {
        this.loginAttempt = loginAttempt;
    }
}
