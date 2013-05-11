/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.myapp.admin.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author palwal
 */
public class LoggingInterceptor extends AbstractInterceptor implements Interceptor, StrutsStatics {

    static final Logger logger = Logger.getLogger(LoggingInterceptor.class);
    private static final String USER_HANDLE = "user";
    private static final String LOGIN_ATTEMPT = "loginAttempt";

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        String className = invocation.getAction().getClass().getName();
//        long startTime = System.currentTimeMillis();
        logger.debug("Before calling action: " + className);

//        String result = invocation.invoke();
//        long endTime = System.currentTimeMillis();
//        logger.debug("After calling action: " + className + " Time taken: " + (endTime - startTime) + " ms");

        final ActionContext context = invocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
        HttpSession session = request.getSession(true);

        // Is there a "user" object stored in the user's HttpSession?
        Object user = session.getAttribute(USER_HANDLE);
        if (user == null) {
            // The user has not logged in yet.
            logger.debug("User is null...");
            // Is the user attempting to log in right now?
//            String loginAttempt = request.getParameter(LOGIN_ATTEMPT);

            /* The user is attempting to log in. */
//            if (!StringUtils.isBlank(loginAttempt)) {
            return "login_form_redirect";
        } else {
            logger.debug("User is not null...");
            String userId = null;
            try {
                userId = ((User) user).getUserId();
            } catch (Exception e) {
                logger.debug("User is not null, class cast exception...");
            }
            if (userId == null || "".equals(userId)) {
                return "login_form_redirect";
            } else {
                return invocation.invoke();
            }
        }
    }

    @Override
    public void destroy() {
        logger.debug("Destroying LoggingInterceptor...");
    }

    @Override
    public void init() {
        logger.debug("Initializing LoggingInterceptor...");
    }
}
