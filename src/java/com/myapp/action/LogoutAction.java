/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class LogoutAction extends ActionSupport {

    static final Logger logger = Logger.getLogger(LogoutAction.class);

    //business logic
    @Override
    public String execute() {

        logger.debug("LogoutAction execute!");

        String returnVal = "input";

        Cookie cookie;
        cookie = new Cookie("user", "Login user details");
        cookie.setMaxAge(0); //invalidate

        HttpServletResponse response = ServletActionContext.getResponse();
        response.addCookie(cookie);
        returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("user");

        User user = (User) session.getAttribute("user");

        if (user == null) {
            returnVal = "success";
        } else {
            returnVal = "fail";
        }

        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        System.out.println("in the validate of LogoutAction");
        logger.debug("in the validate of LogoutAction");
        addActionMessage("In the LogoutAction");
    }

    public String getActionAction() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
