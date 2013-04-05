/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.User;
import com.myapp.main.Category;
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
    private String rememberme;
    private String action;
    boolean loggedIn = false;
    Credential credential = null;
    User user = null;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(LoginAction.class);

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

    public String getActionAction() {
                ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    
    //business logic
    @Override
    public String execute() {
        logger.debug("LoginAction execute!");

        String returnVal = "success";

        //?????
//        UserDAO userDAO = new UserDAO();
//        user = userDAO.selectUserFromCredentialID(credential.getCredentialId());

        Set<User> users = credential.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            user = (User) iterator.next();
        }

        logger.debug("before state DAO1:" + getUsername());
        logger.debug("execute state DAO2:" + user.getFirstName());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
//        logger.debug("Session ID:" + request.getRequestedSessionId());
//        logger.debug("getContextPath ID:" + request.getContextPath());
//        logger.debug("getAuthType ID:" + request.getAuthType());
//        logger.debug("getPathInfo ID:" + request.getPathInfo());
//        logger.debug("getQueryString ID:" + request.getQueryString());
//        logger.debug("getRemoteUser ID:" + request.getRemoteUser());
//        logger.debug("getLocale ID:" + request.getLocale());
//        logger.debug("getRemoteHost ID:" + request.getRemoteHost());

        logger.debug("111111111111");
        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

//        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
//            logger.debug("222222222222");
//            Category c = (Category) iterator.next();
//            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());
//            Set<Bookmark> bookmarks = c.getBookmarks();
//
//            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
//                Bookmark b = (Bookmark) i.next();
//                logger.debug("bookmarkId value:" + b.getBookmarkId() + ":" + b.getBookmarkName() + ":" + b.getHiperLink());
//            }
//        }
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {

        logger.debug("before state DAO1:" + getUsername());
        logger.debug("before state DAO1:" + getActionAction());

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

//        if (getPassword().length() == 0) {
//            addFieldError("password", getText("password.empty"));
//        }
//        addActionMessage("In the LoginAction");
        logger.debug("in the validate of LoginAction");
    }
}
