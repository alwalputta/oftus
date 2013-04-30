/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class Credential implements Serializable {

    private int credentialId;
    private String username;
    private String password;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    private Set<User> users = new LinkedHashSet<User>(0);
    static final Logger logger = Logger.getLogger(Credential.class);

    /**
     * @return the credentialId
     */
    public int getCredentialId() {
        return credentialId;
    }

    /**
     * @param credentialId the credentialId to set
     */
    public void setCredentialId(int credentialId) {
        this.credentialId = credentialId;
    }

    /**
     * @return the userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the userName to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the startDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credential() {
    }
}
