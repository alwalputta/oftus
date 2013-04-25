/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class DocumentAction extends ActionSupport {

    private File content;
    private String imageContentType;
    private String imageFileName;
    static final Logger logger = Logger.getLogger(DocumentAction.class);

    //business logic
    @Override
    public String execute() {
        logger.debug("BookmarkAction newBookmark!");
        return "success";
    }

    //business logic
    public String uploadDoument() {
        logger.debug("uploadDoument!" + "");
        String returnVal = "success";

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

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
