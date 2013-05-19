/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class Gender implements Serializable {

    private String genderId;
    private String genderCode;
    private String genderName;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    static final Logger logger = Logger.getLogger(Gender.class);

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public Gender(String genderCode, String genderName) {
        this.genderCode = genderCode;
        this.genderName = genderName;
    }

    public Gender() {
    }
}
