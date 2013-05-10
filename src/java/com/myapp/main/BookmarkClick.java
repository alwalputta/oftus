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

    private String bookmarkClickId;
    private String bookmarkId;
    private String hiperLink;
    private String createDate;
    private String lastModifiedDate;
    static final Logger logger = Logger.getLogger(BookmarkClick.class);

    public BookmarkClick() {
    }

    public String getBookmarkClickId() {
        return bookmarkClickId;
    }

    public void setBookmarkClickId(String bookmarkClickId) {
        this.bookmarkClickId = bookmarkClickId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
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
