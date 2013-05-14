/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.Gender;
import com.myapp.admin.GenderDAO;
import com.myapp.admin.State;
import com.myapp.admin.StateDAO;
import com.myapp.admin.User;
import com.myapp.main.Category;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class GeneralAction extends ActionSupport {

    private List<Gender> genders;
    private List<State> states;
    User user = null;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(GeneralAction.class);

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

    public Set<Category> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Set<Category> userCategories) {
        this.userCategories = userCategories;
    }

    public GeneralAction() {
    }

    //business logic
    @Override
    public String execute() {
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

        return "success";
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

        User user = (User) session.getAttribute("user");
        logger.debug("RegisterAction editProfile, userid:" + user.getUserId());

        return "success";
    }

    @Override
    public void validate() {
        logger.debug("GeneralAction validate");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        //addActionMessage("In the GeneralAction" + Utils.getUuid());
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
