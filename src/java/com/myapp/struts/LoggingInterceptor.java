/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.myapp.admin.User;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author palwal
 */
public class LoggingInterceptor extends AbstractInterceptor implements StrutsStatics {

    static final Logger logger = Logger.getLogger(LoggingInterceptor.class);
    private static final String USER_HANDLE = "user";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        // Is there a "user" object stored in the user's HttpSession?
        Object user = session.getAttribute(USER_HANDLE);
        if (user == null) {
            // The user has not logged in yet.
            logger.debug("User Null");
            Utils.recordClickLog("Not logged in", USER_HANDLE);
//            return "login_form_redirect";
        } else {
            String userId = ((User) user).getUserId();
            logger.debug("Logging User not null:" + userId);
            Utils.recordClickLog(userId, context.getName());
            return invocation.invoke();
        }
        return invocation.invoke();
    }

    @Override
    public void init() {
        logger.info("Intializing LoginInterceptor");
    }

    @Override
    public void destroy() {
    }
}
