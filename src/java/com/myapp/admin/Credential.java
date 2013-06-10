/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

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
public class Credential implements Serializable {

    private String credentialId;
    private String username;
    private String password;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    private List<User> users = new ArrayList<User>(0);
    static final Logger logger = Logger.getLogger(Credential.class);

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credential() {
    }
}
