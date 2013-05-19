/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import au.com.bytecode.opencsv.CSVReader;
import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Bookmark;
import com.myapp.main.Category;
import com.myapp.main.Document;
import com.myapp.main.DocumentDAO;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import sun.misc.BASE64Encoder;

public class DocumentAction extends ActionSupport {

    private File file;
    private String fileFileName;
    private String fileContentType;
    private String message;
    static final Logger logger = Logger.getLogger(DocumentAction.class);

    //business logic
    @Override
    public String execute() {
        logger.debug("DocumentAction execute!");
        return "success";
    }

    //business logic
    public String uploadPicture() {
        logger.debug("uploadPicture!");
        String returnVal = "success";
        Blob blob;

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Document document = new Document();

        document.setFileType("profile_pic");
        document.setFileName(getFileFileName());
        document.setContentType(getFileContentType());
        document.setUserId(user.getUserId());
        document.setStatus("A");
        document.setCreateDate(Utils.getCurrentDate());

//xxxxxx
        String imageString = null;
        try {
            byte[] imageBytes = Utils.fileToByte(getFile());

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            //logger.debug("uploadPicture imageString:" + imageString.replaceAll("INFO: ", "").trim());
            logger.debug("uploadPicture imageString:" + imageString.length());
            logger.debug("uploadPicture imageString:");

            String encoded = Base64.encodeBase64String(imageBytes);
            //logger.debug("uploadPicture imageString:" + encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }
//xxxxx

        logger.debug("uploadPicture fileName:" + getFileFileName());
        logger.debug("uploadPicture contentType:" + getFileContentType());
        try {
            FileInputStream fi = new FileInputStream(getFile());
            blob = Hibernate.createBlob(fi);
            document.setBlob(blob);

            logger.debug("uploadPicture:");
            DocumentDAO documentDAO = new DocumentDAO();
            documentDAO.uploadDocument(document);

            logger.debug("uploadPicture:");
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        setMessage("Profile picture uploaded successfully.");
        return returnVal;
    }

    //May not need it.
    public String showPicture() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        logger.debug("showPicture:" + user.getUserId());

        DocumentDAO documentDAO = new DocumentDAO();
        Document document = documentDAO.getPicture(user.getUserId());

        logger.debug("showPicture:" + document.getContentType());
        logger.debug("showPicture:" + document.getFileName());
        logger.debug("showPicture:" + document.getFileType());
        logger.debug("showPicture:" + document.getStatus());

        setMessage("Picture displayed successfully.");
        return SUCCESS;
    }

    public String importBookmarks() {
        logger.debug("importBookmarks!");
        String returnVal = "success";
        Blob blob;
        Set<Category> userCategories;
        Set<Bookmark> bookmarks = new LinkedHashSet<Bookmark>(0);

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Document document = new Document();
        document.setFileType("import_bookmarks");
        document.setFileName(getFileFileName());
        document.setContentType(getFileContentType());
        document.setUserId(user.getUserId());
        document.setStatus("A");
        document.setCreateDate(Utils.getCurrentDate());

        logger.debug("importBookmarks fileName:" + getFileFileName());
        logger.debug("importBookmarks contentType:" + getFileContentType());

        try {
            FileInputStream fi = new FileInputStream(getFile());
            blob = Hibernate.createBlob(fi);
            document.setBlob(blob);

            logger.debug("importBookmarks:");
            DocumentDAO documentDAO = new DocumentDAO();
            documentDAO.uploadDocument(document);

            logger.debug("importBookmarks:");

            userCategories = user.getUserCategories();
            logger.debug("userCategories size:" + userCategories.size());

            CSVReader csvReader = new CSVReader(new FileReader(getFile()));
            String[] row = null;
            int i = 0;
            Category c = new Category();
            while ((row = csvReader.readNext()) != null) {
//                for (int i = 0; i < row.length; i++) {
//                    logger.debug("AAA:" + i + ":" + row[i]);
//            }
                i++;
                if (i == 1) {
                    c.setCategoryName("IMPORTS");
                    c.setDescription("Bookmark Imports");
                    c.setStatus("A");
                    i++;
                    continue;
                } else {
                    Bookmark bookmark = new Bookmark();
                    bookmark.setBookmarkName(row[0]);
                    bookmark.setHiperLink(row[1]);
                    bookmark.setDescription(row[2]);
                    bookmark.setStatus("A");
                    bookmark.setCreateDate(Utils.getCurrentDate());

                    bookmarks.add(bookmark);
                }
            }
            c.setBookmarks(bookmarks);
            if (i > 1) {
                userCategories.add(c);
            }

            user.setUserCategories(userCategories);
            UserDAO userDAO = new UserDAO();
            userDAO.updateUser(user);

        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }

        logger.debug("importBookmarks:");
        setMessage("Your bookmarks have been imported successfully.");

        return returnVal;
    }

    @Override
    public void validate() {
        logger.debug("in the validate of Document.validate()");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        if (getActionName().equals("upload_picture")) {
            logger.debug("in upload_picture");
//            if (fileName == null || "".equals(fileName)) {
//                addFieldError("content", "Please chose a file.");
//            }
        } else if (getActionName().equals("show_picture")) {
            logger.debug("in show_picture");
        } else if (getActionName().equals("import_bookmarks")) {
            logger.debug("in import_bookmarks");
        } else {
            logger.debug("in else");
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

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
