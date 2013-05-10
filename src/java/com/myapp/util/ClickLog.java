/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import java.util.Date;

/**
 *
 * @author palwal
 */
public class ClickLog {

    private String clickId;
    private String userId;
    private String actionName;
    private String createDate;

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
