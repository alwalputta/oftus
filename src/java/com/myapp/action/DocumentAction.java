/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.main.DocumentDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class DocumentAction extends ActionSupport {

    private File content;
    private String contentType;
    private String fileName;
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

//        DocumentDAO documentDAO = new DocumentDAO();
//        documentDAO.updateDocument(getFileName(), getContent(), getContentType());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        try {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            System.out.println("Server path:" + filePath);
            File fileToCreate = new File(filePath, fileName);

            FileUtils.copyFile(content, fileToCreate);
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
            if (getContent() == null || "".equals(getContent())) {
                addFieldError("content", "File cant be empty");
            }
        }
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    public File getContent() {
        return content;
    }

    public void setContent(File content) {
        this.content = content;
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
