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
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class DownloadAction extends ActionSupport {

    private InputStream fileInputStream;
    static final Logger logger = Logger.getLogger(DownloadAction.class);

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String downloadTemplate() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        String contextPath = request.getContextPath();
        logger.debug("Contextpath[/myapp]:" + contextPath);

        String path = new File("").getAbsolutePath() + File.separator + "docroot";
        fileInputStream = new FileInputStream(new File(path + "/WEB-INF/results/export_bookmarks.xlsx"));

        return SUCCESS;
    }
}
