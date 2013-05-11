/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.util.Utils;
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
public class LogoutAction extends ActionSupport {

    static final Logger logger = Logger.getLogger(LogoutAction.class);

    //business logic
    @Override
    public String execute() {
        logger.debug("LogoutAction execute!");

        String returnVal = "input";

        ServletActionContext.getRequest().getSession().invalidate();
        addActionMessage("You are successfully logout!");

        returnVal = "success";
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        logger.debug("in the validate of LogoutAction");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        addActionMessage("In the LogoutAction");
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
