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
public class Preference implements Serializable {

    private int preferenceId;
    private String preferenceCode;
    private String preferenceValue;
    private String description;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    static final Logger logger = Logger.getLogger(Preference.class);

    public Preference() {
    }

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getPreferenceCode() {
        return preferenceCode;
    }

    public void setPreferenceCode(String preferenceCode) {
        this.preferenceCode = preferenceCode;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
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

    public Preference(String preferenceCode, String preferenceValue, String description) {
        this.preferenceCode = preferenceCode;
        this.preferenceValue = preferenceValue;
        this.description = description;
    }
}
