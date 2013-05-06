/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.myapp.main.Document;
import com.myapp.main.DocumentDAO;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DocumentAction extends ActionSupport {

    private File file;
    private String fileFileName;
    private String fileContentType;
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

        return SUCCESS;
    }

    public String importBookmarks() {
        logger.debug("importBookmarks!");
        String returnVal = "success";
        Blob blob;

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Document document = new Document();

        document.setFileType("import_bookmarks");
        document.setFileName(getFileFileName());
        document.setContentType(getFileContentType());
        document.setUserId(user.getUserId());
        document.setStatus("A");

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


            HSSFWorkbook workbook = new HSSFWorkbook(fi);
            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            //Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();

            //Get iterator to all cells of current row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            logger.debug("importBookmarks:" + cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            logger.debug("importBookmarks:" + cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            logger.debug("importBookmarks:" + cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        logger.debug("importBookmarks:");
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
}
