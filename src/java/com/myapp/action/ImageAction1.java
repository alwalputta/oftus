/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.myapp.main.Document;
import com.myapp.main.DocumentDAO;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import sun.misc.BASE64Encoder;
import org.apache.shiro.codec.Base64;

/**
 *
 * @author palwal
 */
public class ImageAction1 extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    byte[] imageInByte = null;
    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    static final Logger logger = Logger.getLogger(ImageAction1.class);

    public ImageAction1() {
        logger.debug("ImageAction constructor");
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public byte[] getCustomImageInBytes() {
        try {
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

            /*
             (assuming you have a ResultSet named RS)
             int blobLength = (int) document.getBlob().length();
             byte[] imageInByte = document.getBlob().getBytes(1, blobLength);
             logger.debug("showPicture:" + (int) document.getBlob().length());
             */

            
            //AAA
            try {
                byte[] imageBytes = Utils.blobToByte(document.getBlob());

                BASE64Encoder encoder = new BASE64Encoder();
                String imageString = encoder.encode(imageBytes);
                logger.debug("ImageAction imageString:" + imageString.replaceAll("INFO: ", "").trim());
                logger.debug("ImageAction imageString:" + imageString.length());
                logger.debug("ImageAction imageString:" + org.apache.shiro.codec.Base64.encodeToString(imageBytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ///AAA

            int blobLength = (int) document.getBlob().length();
            BufferedImage originalImage = ImageIO.read(document.getBlob().getBinaryStream(1, blobLength));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);

            baos.flush();
            imageInByte = baos.toByteArray();
            baos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageInByte;
    }

    public String getCustomContentType() {
        return "image/jpeg";
    }

    public String getCustomContentDisposition() {
        return "user_picture.jpg";
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.servletResponse = response;
    }
}
