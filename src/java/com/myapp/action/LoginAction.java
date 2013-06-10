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
import java.util.List;
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
    boolean loggedIn = false;
    Credential credential = null;
    User user = null;
    List<Category> userCategories = null;
    private String message;
    static final Logger logger = Logger.getLogger(LoginAction.class);

    //business logic
//    @Override
    public String loginForm() {
        logger.debug("LoginAction execute!");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        if (u == null) {
            return "input";
        } else {
            setMessage("You have successfully logged in.");
            return SUCCESS;
        }
    }

    public String login() {
        logger.debug("LoginAction execute!");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        String returnVal = "success";

        CredentialDAO credentialDAO = new CredentialDAO();
        credential = credentialDAO.selectCredential(getUsername());

        List<User> users = credential.getUsers();
        for (Iterator iterator = users.iterator(); iterator.hasNext();) {
            user = (User) iterator.next();
        }

        //user = (User) session.getAttribute("user");
        logger.debug("before state DAO1:" + getUsername());
        logger.debug("execute state DAO2:" + user.getFirstName());

        //Utils.recordLoginLog(user.getUserId(), request);
        session.setAttribute("user", user);

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());



        //Testing the order of items....
        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            logger.debug("222222222222");
            Category c = (Category) iterator.next();
            logger.debug("AAAAAA:" + c.getCategoryName());

            List<Bookmark> bookmarks = c.getBookmarks();
            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
                Bookmark b = (Bookmark) i.next();
                logger.debug("BBBBBBB:" + b.getBookmarkName());
            }
        }
        //Testing the order of items....






        setMessage("You have successfully logged in.");
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (getActionName().equals("index")) {
            logger.debug("index");
            if (user == null) {
                if (username == null || "".equals(username)) {
                    logger.debug("Username is null");
                    addActionError("Username is empty.");
                }
                if (password == null || "".equals(password)) {
                    logger.debug("Password is null");
                    addActionError("Password is empty.");
                }
            } else {
            }
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

    public List<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<Category> userCategories) {
        this.userCategories = userCategories;
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
