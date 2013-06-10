/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class Bookmark implements Serializable {

    private String bookmarkId;
    private String bookmarkName;
    private String description;
    private String hiperLink;
    private String status;
    private int order;
    private String createDate;
    private String endDate;
    private String lastModifiedDate;
    private List<Category> categories = new ArrayList<Category>(0);
    static final Logger logger = Logger.getLogger(Bookmark.class);

    public Bookmark() {
    }

    public Bookmark(String bookmarkName, String description, String hiperLink) {
        this.bookmarkName = bookmarkName;
        this.description = description;
        this.hiperLink = hiperLink;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getHiperLink() {
        return hiperLink;
    }

    public void setHiperLink(String hiperLink) {
        this.hiperLink = hiperLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
