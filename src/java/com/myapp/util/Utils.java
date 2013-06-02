/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class Utils {

    static final Logger logger = Logger.getLogger(Utils.class);

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static byte[] fileToByte(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static byte[] blobToByte(Blob blob) throws IOException, SQLException {
        int blobLength = (int) blob.length();
        byte[] imageInByte = blob.getBytes(1, blobLength);
        return imageInByte;
    }

    public static void recordLoginLog(String userId, HttpServletRequest request) {
        HttpSession session = request.getSession();

        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setBrowser("Browser");
        loginLog.setIpAddress(request.getLocalAddr());
        loginLog.setMachineName(request.getRemoteHost());
        loginLog.setTimeZone(request.getLocale() + "");
        loginLog.setSessionId(session.getId());
        loginLog.setCreateDate(Utils.getCurrentDate());

        UtilDAO utilDAO = new UtilDAO();
        utilDAO.recordLoginLog(loginLog);
    }

    public static void recordClickLog(String userId, String actionName) {

        ClickLog clickLog = new ClickLog();
        clickLog.setUserId(userId);
        clickLog.setActionName(actionName);
        clickLog.setCreateDate(getCurrentDate());

        UtilDAO utilDAO = new UtilDAO();
        utilDAO.recordClickLog(clickLog);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Properties setMailProps() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        return props;
    }
}
