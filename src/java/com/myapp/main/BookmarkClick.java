/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class BookmarkClick implements Serializable {

    private int bookmarkClickId;
    private int bookmarkId;
    private String hiperLink;
    private String createDate;
    private String lastModifiedDate;
    static final Logger logger = Logger.getLogger(BookmarkClick.class);

    public BookmarkClick() {
    }

    public int getBookmarkClickId() {
        return bookmarkClickId;
    }

    public void setBookmarkClickId(int bookmarkClickId) {
        this.bookmarkClickId = bookmarkClickId;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getHiperLink() {
        return hiperLink;
    }

    public void setHiperLink(String hiperLink) {
        this.hiperLink = hiperLink;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
