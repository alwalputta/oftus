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

    private Long referralId;
    private Long userId;
    private String referralEmail;
    private String message;
    private Date referralDate;

    /**
     * @return the referralId
     */
    public Long getReferralId() {
        return referralId;
    }

    /**
     * @param referralId the referralId to set
     */
    public void setReferralId(Long referralId) {
        this.referralId = referralId;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
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
    public Date getReferralDate() {
        return referralDate;
    }

    /**
     * @param referralDate the referralDate to set
     */
    public void setReferralDate(Date referralDate) {
        this.referralDate = referralDate;
    }
}
