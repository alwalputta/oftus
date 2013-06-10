/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import com.myapp.main.Category;
import java.io.Serializable;
import java.util.LinkedHashSet;
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
    private Set<Address> userAddresses = new LinkedHashSet<Address>(0);
    private Set<Email> userEmails = new LinkedHashSet<Email>(0);
    private Set<Phone> userPhones = new LinkedHashSet<Phone>(0);
    private Set<Credential> userCredentials = new LinkedHashSet<Credential>(0);
    private Set<Role> userRoles = new LinkedHashSet<Role>(0);
    private Set<Category> userCategories = new LinkedHashSet<Category>(0);
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

    public Set<Address> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<Address> userAddresses) {
        this.userAddresses = userAddresses;
    }

    public Set<Email> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(Set<Email> userEmails) {
        this.userEmails = userEmails;
    }

    public Set<Credential> getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(Set<Credential> userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Set<Role> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Phone> getUserPhones() {
        return userPhones;
    }

    public void setUserPhones(Set<Phone> userPhones) {
        this.userPhones = userPhones;
    }

    public Set<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Set<Category> userCategories) {
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
