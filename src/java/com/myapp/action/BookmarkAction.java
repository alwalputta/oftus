/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Bookmark;
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
public class BookmarkAction extends ActionSupport {

    private String categoryId;
    private String bookmarkname;
    private String hiperLink;
    private String description;

    static final Logger logger = Logger.getLogger(BookmarkAction.class);

    
    //business logic
    public String addBookmark() {

        logger.debug("BookmarkAction addBookmark!");

        String returnVal = "success";

        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {

        logger.debug("before state DAO1:");

        logger.debug("in the validate of BookmarkAction");
    }
    
    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

}
