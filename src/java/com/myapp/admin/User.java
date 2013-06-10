/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import com.myapp.main.Category;
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
public class User implements Serializable {

    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String DOB;
    private String gender;
    private String createDate;
    private String status;
    private String endDate;
    private String lastModifiedDate;
    private String username;
    private String password;
    private List<Address> userAddresses = new ArrayList<Address>(0);
    private List<Email> userEmails = new ArrayList<Email>(0);
    private List<Phone> userPhones = new ArrayList<Phone>(0);
    private List<Credential> userCredentials = new ArrayList<Credential>(0);
    private List<Role> userRoles = new ArrayList<Role>(0);
    private List<Category> userCategories = new ArrayList<Category>(0);
    static final Logger logger = Logger.getLogger(User.class);

    public User(String firstName, String middleName, String lastName, String DOB, String gender) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.gender = gender;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
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

    public List<Address> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public List<Email> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(List<Email> userEmails) {
        this.userEmails = userEmails;
    }

    public List<Credential> getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(List<Credential> userCredentials) {
        this.userCredentials = userCredentials;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public List<Phone> getUserPhones() {
        return userPhones;
    }

    public void setUserPhones(List<Phone> userPhones) {
        this.userPhones = userPhones;
    }

    public List<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<Category> userCategories) {
        this.userCategories = userCategories;
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
}
