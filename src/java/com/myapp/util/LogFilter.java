/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import com.myapp.servlet.ImageServlet;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
//@WebFilter(filterName = "LogFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {

    private static final boolean debug = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    static final Logger logger = Logger.getLogger(LogFilter.class);

    public LogFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

//        if (request instanceof HttpServletRequest) {
        String url = ((HttpServletRequest) request).getRequestURL().toString();
        String queryString = ((HttpServletRequest) request).getQueryString();
        Utils.recordClickLog("XXXX123XXXX:" + session.getId(), url);
        Utils.recordClickLog("XXXX321XXXX:" + session.getId(), queryString);
//        }

        Utils.recordClickLog("YYYYY:" + session.getId(), request.getRemoteAddr());
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

        //Get init parameter
        String testParam = config.getInitParameter("test-param");
        //Print the init parameter
        System.out.println("Test Param: " + testParam);
    }

    @Override
    public void destroy() {
        //add code to release any resource
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
