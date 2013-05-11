/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class LoggingInterceptor implements Interceptor {
    
    static final Logger logger = Logger.getLogger(LoggingInterceptor.class);
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        
        String className = invocation.getAction().getClass().getName();
        long startTime = System.currentTimeMillis();
        logger.debug("Before calling action: " + className);
        
        String result = invocation.invoke();
        
        long endTime = System.currentTimeMillis();
        logger.debug("After calling action: " + className + " Time taken: " + (endTime - startTime) + " ms");
        
        return result;
    }
    
    @Override
    public void destroy() {
        logger.debug("Destroying MyLoggingInterceptor...");
    }
    
    @Override
    public void init() {
        logger.debug("Initializing MyLoggingInterceptor...");
    }
}
