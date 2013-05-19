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
public class State implements Serializable {

    private String stateId;
    private String stateCode;
    private String stateName;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    static final Logger logger = Logger.getLogger(State.class);

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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

    public State(String stateCode, String stateName) {
        this.stateCode = stateCode;
        this.stateName = stateName;
    }

    public State() {
    }
}
