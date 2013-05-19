/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Bookmark;
import com.myapp.main.Category;
import com.myapp.main.CategoryDAO;
import com.myapp.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;
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
    private String categoryOrder;
    private String source;
    Set<Category> userCategories = null;
    public static final long serialVersionUID = 42L;
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
        Category category = categoryDAO.selectCategory(categoryId);

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

        Bookmark bookmark = new Bookmark("OFTUS", "OFTUS bookmark", "http://www.oftus.com/");
        bookmark.setStatus("A");
        Set<Bookmark> userBookmarks = new LinkedHashSet<Bookmark>();
        userBookmarks.add(bookmark);

        Category category = new Category();
        category.setCategoryName(getCategoryName());
        category.setDescription(getDescription());
        category.setStatus("A");
        category.setCreateDate(Utils.getCurrentDate());

        category.setBookmarks(userBookmarks);

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

            if (c.getCategoryId().equals(categoryId)) {
                c.setCategoryName(categoryName);
                c.setDescription(description);
                break;
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
            if (c.getCategoryId().equals(categoryId)) {
//                c.setStatus("D");
                userCategories.remove(c);
                break;
            }
        }
        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //business logic to update the category
    public String updateCategoryOrder() {
        logger.debug("updateCategoryOrder!" + getCategoryOrder());
        String returnVal = "success";
        int updated = 0;

        StringTokenizer st = new StringTokenizer(getCategoryOrder(), ":");
        for (int i = 0; st.hasMoreTokens(); i++) {
            categoryId = st.nextToken();
            logger.debug("category ID" + categoryId);
            CategoryDAO categoryDAO = new CategoryDAO();
            updated = categoryDAO.updateCategoryOrder(categoryId, i);
            logger.debug("Records Updated" + updated);
        }
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        logger.debug("in the validate");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Utils.recordClickLog(session.getId(), getActionName());

        logger.debug("editCategory!" + getCategoryId());

        if (getActionName().equals("new_bookmark")) {
            logger.debug("in new_bookmark");
        } else if (getActionName().equals("save_category")) {
            if (getCategoryName() == null || "".equals(getCategoryName())) {
                addFieldError("categoryName", "Category title cant be empty");
            }
        } else if (getActionName().equals("update_category") || getActionName().equals("update_category_list")) {
            if (getCategoryName() == null || "".equals(getCategoryName())) {
                addFieldError("categoryName", "Category title cant be empty");
            }
        } else if (getActionName().equals("update_category_order")) {
            logger.debug("in update_category_order");
        } else {
        }
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

    public String getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(String categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
