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
public class Referral {

    private int referralId;
    private int userId;
    private String referralEmail;
    private String message;
    private Date createDate;

    /**
     * @return the referralId
     */
    public int getReferralId() {
        return referralId;
    }

    /**
     * @param referralId the referralId to set
     */
    public void setReferralId(int referralId) {
        this.referralId = referralId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the referralEmail
     */
    public String getReferralEmail() {
        return referralEmail;
    }

    /**
     * @param referralEmail the referralEmail to set
     */
    public void setReferralEmail(String referralEmail) {
        this.referralEmail = referralEmail;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the referralDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the referralDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
