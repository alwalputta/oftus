/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import com.myapp.action.ImageAction1;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class CustomImageBytesResult1 implements Result {

    static final Logger logger = Logger.getLogger(CustomImageBytesResult1.class);

    @Override
    public void execute(ActionInvocation invocation) throws Exception {

        ImageAction1 action = (ImageAction1) invocation.getAction();
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType(action.getCustomContentType());
        response.getOutputStream().write(action.getCustomImageInBytes());
        response.getOutputStream().flush();
    }
}
