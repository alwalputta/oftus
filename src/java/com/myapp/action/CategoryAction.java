/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Category;
import com.myapp.main.CategoryDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author palwal
 */
public class CategoryAction extends ActionSupport {

    private String categoryId;
    private String categoryName;
    private String description;
    Set<Category> userCategories = null;
    static final Logger logger = Logger.getLogger(CategoryAction.class);

//business logic to fetch the category details
    public String addCategory() {
        logger.debug("addCategory!");
        String returnVal = "success";

        return returnVal;
    }

//business logic to fetch the category details
    public String editCategory() {
        logger.debug("editCategory!" + getCategoryId());
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.selectCategory(new Integer(categoryId).intValue());

        session.setAttribute("category", category);

        return returnVal;
    }

    //business logic to update the category
    public String saveCategory() {
        logger.debug("saveCategory!" + getCategoryId());
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        Category category = new Category();
        category.setCategoryName(getCategoryName());
        category.setDescription(getDescription());
        category.setStatus("A");

        userCategories.add(category);

        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //business logic to update the category
    public String updateCategory() {
        logger.debug("updateCategory!" + getCategoryId());
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            logger.debug("222222222222");
            Category c = (Category) iterator.next();

            if (c.getCategoryId() == new Integer(categoryId).intValue()) {
                c.setCategoryName(categoryName);
                c.setDescription(description);
            }
        }
        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //Business logic to delete a category
    public String deleteCategory() {
        logger.debug("deleteCategory!" + getCategoryId());
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        Category c = null;
        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            c = (Category) iterator.next();
            logger.debug("222222222222:" + c.getCategoryId());
            if (c.getCategoryId() == new Integer(categoryId).intValue()) {
//                c.setStatus("D");
                break;
            }
        }
        userCategories.remove(c);
        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        logger.debug("in the validate");
        logger.debug("editCategory!" + getCategoryId());

        if (getActionName().equals("new_bookmark")) {
            logger.debug("in new_bookmark");
        } else if (getActionName().equals("save_category")) {
            if (getCategoryName() == null || "".equals(getCategoryName())) {
                addFieldError("categoryName", "Category title cant be empty");
            }
        } else if (getActionName().equals("update_category")) {
            if (getCategoryName() == null || "".equals(getCategoryName())) {
                addFieldError("categoryName", "Category title cant be empty");
            }
        }

//        addFieldError("categoryname", getText("categorynameisempty1"));
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }
}
