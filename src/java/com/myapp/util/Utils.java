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
import java.util.UUID;

/**
 *
 * @author palwal
 */
public class Utils {

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

    public static void recordLoginLog(LoginLog loginLog) {
        UtilDAO utilDAO = new UtilDAO();
        utilDAO.recordLoginLog(loginLog);
    }

    public static void recordClickLog(String userId, String actionName) {

        ClickLog pageClick = new ClickLog();
        pageClick.setUserId(userId);
        pageClick.setActionName(actionName);
        pageClick.setCreateDate(getCurrentDate());

        UtilDAO utilDAO = new UtilDAO();
        utilDAO.recordPageClick(pageClick);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
