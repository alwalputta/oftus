/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.main.Document;
import com.myapp.main.DocumentDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;

/**
 *
 * @author palwal
 */
public class DocumentAction extends ActionSupport {

    private File file;
    private String fileName;
    private String contentType;
    static final Logger logger = Logger.getLogger(DocumentAction.class);

    //business logic
    @Override
    public String execute() {
        logger.debug("DocumentAction execute!");
        return "success";
    }

    //business logic
    public String uploadDocument() {
        logger.debug("uploadDocument!");
        String returnVal = "success";
        Blob blob;

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        Document document = new Document();
        document.setFileName(getFileName());
        document.setContentType(getContentType());

        logger.debug("uploadDocument1 fileName:" + getFileName());
        logger.debug("uploadDocument1 contentType:" + getContentType());
        try {
            FileInputStream fi = new FileInputStream(getFile());
            blob = Hibernate.createBlob(fi);
            document.setBlob(blob);

            logger.debug("uploadDocument1:");
            DocumentDAO documentDAO = new DocumentDAO();
            documentDAO.uploadDocument(document);
            logger.debug("uploadDocument2:");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        logger.debug("in the validate of Document.validate()");
        if (getActionName().equals("upload_picture")) {
            logger.debug("in upload_picture");
//            if (fileName == null || "".equals(fileName)) {
//                addFieldError("content", "Please chose a file.");
//            }
        }
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
