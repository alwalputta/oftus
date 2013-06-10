/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Address;
import com.myapp.admin.Credential;
import com.myapp.admin.CredentialDAO;
import com.myapp.admin.Email;
import com.myapp.admin.EmailDAO;
import com.myapp.admin.Gender;
import com.myapp.admin.GenderDAO;
import com.myapp.admin.Phone;
import com.myapp.admin.Role;
import com.myapp.admin.State;
import com.myapp.admin.StateDAO;
import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Bookmark;
import com.myapp.main.Category;
import com.myapp.util.SendMail;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class RegisterAction extends ActionSupport {

    private String username;
    private String password;
    private String password2;
    private String emailAddress;
    private String phoneNumber;
    private String firstname;
    private String middlename;
    private String lastname;
    private String DOB;
    private String gender;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String userId;
    private String message;
    static final Logger logger = Logger.getLogger(RegisterAction.class);
    private List<Gender> genders;
    private List<State> states;
    User user = null;
    List<Category> userCategories = null;

    public String registerForm() {
        logger.debug("GeneralAction execute!");

        GenderDAO genderDAO = new GenderDAO();
        genders = genderDAO.listGenders();
        logger.debug("genders:" + genders.size());

        StateDAO stateDAO = new StateDAO();
        states = stateDAO.listStates();
        logger.debug("states:" + states.size());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("genders", genders);
        session.setAttribute("states", states);

        setMessage("General Action execute");
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String editProfile() {
        logger.debug("RegisterAction editProfile!");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        genders = (List<Gender>) session.getAttribute("genders");
        states = (List<State>) session.getAttribute("states");

        if (genders == null) {
            GenderDAO genderDAO = new GenderDAO();
            genders = genderDAO.listGenders();
        }
        logger.debug("before state DAO!");

        if (states == null) {
            StateDAO stateDAO = new StateDAO();
            states = stateDAO.listStates();
        }
        session.setAttribute("genders", genders);
        session.setAttribute("states", states);

        User u = (User) session.getAttribute("user");
        logger.debug("RegisterAction editProfile, userid:" + u.getUserId());

        setMessage("Edit profile and click update.");
        return SUCCESS;
    }

    //@Override
    public String createProfile() {
        logger.debug("RegisterAction execute!");
        HttpServletRequest request = ServletActionContext.getRequest();

        User u = new User();
        u.setFirstName(getFirstname());
        u.setMiddleName(getMiddlename());
        u.setLastName(getLastname());
        u.setGender(getGender());
        u.setStatus("N");
        u.setDOB("".equals(getDOB()) ? null : getDOB());
        u.setCreateDate(Utils.getCurrentDate());

        logger.debug("RegisterAction execute!2");
        //Address object being created
        Address address = new Address(getAddress1(), getAddress2(), getCity(), getState(), getZip());
        address.setStatus("A");
        address.setCreateDate(Utils.getCurrentDate());
        List<Address> userAddresses = new ArrayList<Address>();
        userAddresses.add(address);

        u.setUserAddresses(userAddresses);

        logger.debug("RegisterAction execute!3");
        //Email object being created
        Email email = new Email(getEmailAddress());
        email.setStatus("A");
        email.setCreateDate(Utils.getCurrentDate());
        List<Email> userEmails = new ArrayList<Email>();
        userEmails.add(email);

        u.setUserEmails(userEmails);

        logger.debug("RegisterAction execute!4");
        //Phone object being created
        Phone phone = new Phone(getPhoneNumber());
        phone.setStatus("A");
        phone.setCreateDate(Utils.getCurrentDate());
        List<Phone> userPhones = new ArrayList<Phone>();
        userPhones.add(phone);

        u.setUserPhones(userPhones);

        logger.debug("RegisterAction execute!5");
        //Credential object being created
        Credential credential = new Credential(getUsername(), getPassword());
        credential.setStatus("A");
        credential.setCreateDate(Utils.getCurrentDate());
        List<Credential> userCredentials = new ArrayList<Credential>();
        userCredentials.add(credential);

        u.setUserCredentials(userCredentials);

        logger.debug("RegisterAction execute!5.2");
        //Role object being created
        Role role = new Role();
        role.setStatus("A");
        role.setCreateDate(Utils.getCurrentDate());
        role.setRole("user");
        List<Role> userRoles = new ArrayList<Role>();
        userRoles.add(role);

        u.setUserRoles(userRoles);

        logger.debug("RegisterAction execute!6");
        //Credential object being created
        Bookmark bookmark = new Bookmark("OFTUS", "OFTUS bookmark", "http://www.oftus.com/");
        bookmark.setStatus("A");
        bookmark.setOrder(0);
        bookmark.setCreateDate(Utils.getCurrentDate());
        List<Bookmark> userBookmarks = new ArrayList<Bookmark>();
        userBookmarks.add(bookmark);

        logger.debug("RegisterAction execute!7");
        //Credential object being created
        Category category = new Category("OFTUS", "OFTUS bookmarks");
        category.setStatus("A");
        category.setOrder(0);
        category.setBookmarks(userBookmarks);
        category.setCreateDate(Utils.getCurrentDate());
        List<Category> userCategories = new ArrayList<Category>();
        userCategories.add(category);

        u.setUserCategories(userCategories);

        logger.debug("Address Set Size:" + userAddresses.size());
        logger.debug("Email Set Size:" + userEmails.size());
        logger.debug("Phone Set Size:" + userPhones.size());
        logger.debug("Credential Set Size:" + userCredentials.size());
        logger.debug("Category Set Size:" + userCategories.size());
        logger.debug("Bookmark Set Size:" + userBookmarks.size());

        UserDAO userDAO = new UserDAO();
        userDAO.saveUser(u);

        logger.debug("RegisterAction execute, userid:" + u.getUserId());

        try {
            SendMail.sendActivateEmail(request, u, email.getEmailAddress());
        } catch (Exception e) {
        }
        setMessage("User profile created successfully; "
                + "Please activate profile by clicking the link in the email.");

        return SUCCESS;
    }

    //Update the user profile
    public String updateProfile() {
        logger.debug("RegisterAction updateProfile!");

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        User u = (User) session.getAttribute("user");

        u.setFirstName(getFirstname());
        u.setMiddleName(getMiddlename());
        u.setLastName(getLastname());
        u.setGender(getGender());
        u.setDOB("".equals(getDOB()) ? null : getDOB());
        u.setStatus("A");
        u.setCreateDate(Utils.getCurrentDate());

        logger.debug("RegisterAction execute!2");
        //Address object being created

        List<Address> userAddresses = u.getUserAddresses();
        Address address = null;
        for (Iterator iterator = userAddresses.iterator(); iterator.hasNext();) {
            address = (Address) iterator.next();
        }
        address.setAddress1(address1);
        address.setAddress2(address2);
        address.setCity(city);
        address.setState(state);
        address.setZip(zip);
        address.setStatus("A");
        address.setCreateDate(Utils.getCurrentDate());

        List<Email> userEmails = u.getUserEmails();
        Email email = null;
        for (Iterator iterator = userEmails.iterator(); iterator.hasNext();) {
            email = (Email) iterator.next();
        }
        email.setEmailAddress(getEmailAddress());
        email.setStatus("A");
        email.setCreateDate(Utils.getCurrentDate());

        List<Phone> userPhones = u.getUserPhones();
        Phone phone = null;
        for (Iterator iterator = userPhones.iterator(); iterator.hasNext();) {
            phone = (Phone) iterator.next();
        }
        phone.setPhoneNumber(getPhoneNumber());
        phone.setPhoneType("M");
        phone.setStatus("A");
        phone.setCreateDate(Utils.getCurrentDate());

        if (getPassword() != null && !"".equals(getPassword())) {
            List<Credential> userCredentials = u.getUserCredentials();
            Credential credential = null;
            for (Iterator iterator = userCredentials.iterator(); iterator.hasNext();) {
                credential = (Credential) iterator.next();
            }
            credential.setPassword(getPassword());
            credential.setStatus("A");
            credential.setCreateDate(Utils.getCurrentDate());
        }

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(u);

        logger.debug("userid:" + u.getUserId());
        setMessage("User profile updated successfully.");

        return SUCCESS;
    }

    public String activateProfile() {

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        logger.debug("username:" + request.getParameter("userId"));
        logger.debug("RegisterAction activateProfile!");

        UserDAO userDAO = new UserDAO();
        User u = (User) userDAO.selectUser(request.getParameter("userId"));

        if (u == null) {
            return "input";
        } else {
            u.setStatus("A");
            userDAO.updateUser(u);
            logger.debug("userid:" + u.getUserId());
            setMessage("User activated successfully.");
            return SUCCESS;
        }
    }

    @Override
    public void validate() {
        logger.debug("in the validate of RegisterAction:" + getActionName());

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        if ("create_profile".equals(getActionName())) {
            //Check if user already exists
            EmailDAO emailDAO = new EmailDAO();
            CredentialDAO credentialDAO = new CredentialDAO();

            if (emailDAO.isEmailAvailable(getEmailAddress())) {
                logger.debug("Email available for Registration:" + getEmailAddress());
                addActionMessage("Email " + getEmailAddress() + " is available for registration.");
            } else {
                logger.debug("Email not available for Registration:" + getEmailAddress());
                addActionMessage("Email " + getEmailAddress() + " already registered.");
                addFieldError("emailAddress", "Email address already registered.");
            }

            if (credentialDAO.isUsernameAvailable(getUsername())) {
                logger.debug("Username available for Registration:" + getUsername());
                addActionMessage("Username " + getUsername() + " is available for registration.");
            } else {
                logger.debug("Username not available for Registration:" + getUsername());
                addActionMessage("Username " + getUsername() + " already registered.");
                addFieldError("username", "Username already registered.");
            }

            if (getPassword() == null || "".equals(getPassword())) {
                addFieldError("password", "Password cant be empty");
            }

            if (getPassword2() == null || "".equals(getPassword2())) {
                addFieldError("password2", "Password2 cant be empty");
            }

            if (getPassword().equals(getPassword2())) {
//            addActionMessage("Both passwords match.");
            } else {
                addFieldError("password2", "Both passwords dont match");
            }
        } else if ("update_profile".equals(getActionName())) {
            if (getPassword() != null || "".equals(getPassword())) {
                logger.debug("Username not available for Registration:");
                if (getPassword().equals(getPassword2())) {
//                  addActionMessage("Both passwords match.");
                } else {
                    addFieldError("password2", "Both passwords dont match");
                }
            }
        } else if ("activate_profile".equals(getActionName())) {
            UserDAO userDAO = new UserDAO();
            User u = (User) userDAO.selectUser(getUserId());
            if (u == null) {
                addActionMessage("Please check the link, it does not correspond to any profile in the system");
            }
        } else if ("register_form".equals(getActionName())) {
            logger.debug("register_form");
        } else if ("edit_profile".equals(getActionName())) {
            logger.debug("edit_profile");
        } else {
            logger.debug("Other");
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(List<Category> userCategories) {
        this.userCategories = userCategories;
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
