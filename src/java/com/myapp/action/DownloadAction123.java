/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

/**
 *
 * @author palwal
 */
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class DownloadAction123 extends ActionSupport {

    private InputStream fileInputStream;
    static final Logger logger = Logger.getLogger(DownloadAction123.class);

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String downloadTemplate() throws Exception {
        ServletContext context = ServletActionContext.getServletContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        String contextPath = request.getContextPath();
        //String file = request.getPathInfo();
        String remoteAdd = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String requestURL = request.getRequestURL().toString();

        String path2 = context.getContextPath();
        logger.debug("Contextpath[path2]:" + path2);
        logger.debug("Contextpath[/myapp]:" + contextPath);
//        logger.debug("file[/myapp]:" + file);
        logger.debug("requestURL:" + requestURL);
        logger.debug("remoteAdd:" + remoteAdd);
        logger.debug("remoteHost:" + remoteHost);

        String path = new File("").getAbsolutePath() + File.separator + "docroot";
        fileInputStream = new FileInputStream(new File(contextPath + File.separator + "template/export_bookmarks.xlsx"));

        return SUCCESS;
    }
}
