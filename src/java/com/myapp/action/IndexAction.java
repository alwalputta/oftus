/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class IndexAction extends ActionSupport {

    private String returnVal;
    static final Logger logger = Logger.getLogger(IndexAction.class);

    //business logic
    @Override
    public String execute() {
        logger.debug("In execute.");

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        if (user == null) {
            returnVal = "input";
            logger.debug("user id not valid, not logged in.");
        } else {
            returnVal = "success";
            logger.debug("userid:" + user.getUserId());
        }
        return returnVal;
    }

    @Override
    public void validate() {
        logger.debug("in the validate");
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
