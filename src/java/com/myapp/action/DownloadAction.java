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

public class DownloadAction extends ActionSupport {

    private InputStream fileInputStream;

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String downloadTemplate() throws Exception {
        String path = new File("").getAbsolutePath() + File.separator + "docroot";
        fileInputStream = new FileInputStream(new File(path + "/WEB-INF/results/export_bookmarks.xlsx"));
        return SUCCESS;
    }
}
