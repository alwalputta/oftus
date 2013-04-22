/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import com.myapp.main.Bookmark;
import com.myapp.main.BookmarkDAO;
import com.myapp.main.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Iterator;
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
public class BookmarkAction extends ActionSupport {

    private String categoryId;
    private String bookmarkId;
    private String bookmarkName;
    private String hiperLink;
    private String description;
    private String bookmarkOrder;
    User user = null;
    static final Logger logger = Logger.getLogger(BookmarkAction.class);
    private Set<Category> userCategories;

    //business logic
    @Override
    public String execute() {
        logger.debug("BookmarkAction newBookmark!");
        return "success";
    }

    //business logic
    public String addBookmark() {
        logger.debug("addBookmark!" + getCategoryId());
        String returnVal = "success";

        return returnVal;
    }

    //business logic
    public String editBookmark() {
        logger.debug("editBookmark!" + getBookmarkId());
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        user = (User) session.getAttribute("user");

        logger.debug("category id:" + getCategoryId());

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            Category c = (Category) iterator.next();
            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());

            Set<Bookmark> bookmarks = c.getBookmarks();

            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
                Bookmark b = (Bookmark) i.next();

                if (b.getBookmarkId() == new Integer(getBookmarkId()).intValue()) {
                    logger.debug("bbbbbbbbbbbbbookmarkId value:" + b.getBookmarkId() + ":" + b.getBookmarkName()
                            + ":" + b.getHiperLink() + ":" + b.getDescription());
                    session.setAttribute("bookmark", b);
                    break;
                }
            }
        }
        return returnVal;
    }

    //business logic
    public String saveBookmark() {
        logger.debug("BookmarkAction saveBookmark!");
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        user = (User) session.getAttribute("user");

        logger.debug("category id:" + getCategoryId());

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        Bookmark bookmark = new Bookmark();
        bookmark.setBookmarkName(getBookmarkName());
        bookmark.setHiperLink(hiperLink);
//        bookmark.setBookmarkOrder(10000); //modify this later//

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            Category c = (Category) iterator.next();
            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());

            if (c.getCategoryId() == new Integer(getCategoryId()).intValue()) {
                Set<Bookmark> bookmarks = c.getBookmarks();
                logger.debug("bookmarks size1:" + bookmarks.size());
                bookmarks.add(bookmark);
                logger.debug("bookmarks size2:" + bookmarks.size());
                break;
            }
        }
        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //business logic
    public String updateBookmark() {
        logger.debug("BookmarkAction updateBookmark!");
        String returnVal = "success";
        String categoryChanged = "no";
        int updated = 0;

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        user = (User) session.getAttribute("user");

        logger.debug("category id:" + getCategoryId());

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            Category c = (Category) iterator.next();
            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());

            Set<Bookmark> bookmarks = c.getBookmarks();

            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
                Bookmark b = (Bookmark) i.next();

                if (b.getBookmarkId() == new Integer(getBookmarkId()).intValue()) {
                    logger.debug("cccccccccbookmarkId value:" + getBookmarkId() + ":" + getBookmarkName() + ":"
                            + getHiperLink() + ":" + getDescription());
                    b.setBookmarkName(getBookmarkName());
                    b.setHiperLink(getHiperLink());
                    b.setDescription(getDescription());
//                    b.setBookmarkOrder(10000); //modify this later//

                    if (c.getCategoryId() == new Integer(getCategoryId()).intValue()) {
                        ;
                    } else {
//                        bookmarks.remove(b);
                        BookmarkDAO bookmarkDAO = new BookmarkDAO();
                        updated = bookmarkDAO.updateBookmarkCategory(getBookmarkId(), getCategoryId());
                    }
                    break;
                }
            }
        }

        user.setUserCategories(userCategories);
        UserDAO userDAO = new UserDAO();

        userDAO.updateUser(user);
        return returnVal;
    }

    //business logic
    public String moveBookmark() {
        logger.debug("BookmarkAction moveBookmark!");
        String returnVal = "success";
        int updated = 0;

        logger.debug("bookmark id:" + getBookmarkId());
        logger.debug("category id:" + getCategoryId());

        BookmarkDAO bookmarkDAO = new BookmarkDAO();
        updated = bookmarkDAO.updateBookmarkCategory(getBookmarkId(), getCategoryId());

        return returnVal;
    }

    //business logic
    public String deleteBookmark() {
        logger.debug("BookmarkAction deleteBookmark!");
        String returnVal = "success";

        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();

        user = (User) session.getAttribute("user");
        logger.debug("category id:" + getCategoryId());

        userCategories = user.getUserCategories();
        logger.debug("userCategories size:" + userCategories.size());

        for (Iterator iterator = userCategories.iterator(); iterator.hasNext();) {
            Category c = (Category) iterator.next();
            logger.debug("categoryId value:" + c.getCategoryId() + ":" + c.getCategoryName());

            Set<Bookmark> bookmarks = c.getBookmarks();

            for (Iterator i = bookmarks.iterator(); i.hasNext();) {
                Bookmark b = (Bookmark) i.next();

                if (b.getBookmarkId() == new Integer(getBookmarkId()).intValue()) {
                    logger.debug("bookmarkId value:" + b.getBookmarkId() + ":" + b.getBookmarkName() + ":"
                            + b.getHiperLink() + ":" + b.getDescription());
//                    b.setStatus("D");
                    bookmarks.remove(b);
                    break;
                }
            }
        }
        user.setUserCategories(userCategories);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUser(user);

        return returnVal;
    }

    //business logic to update the category
    public String updateBookmarkOrder() {
        logger.debug("updateCategoryOrder!" + getBookmarkOrder());
        String returnVal = "success";
        int updated = 0;

        StringTokenizer st = new StringTokenizer(getBookmarkOrder(), ":");
        for (int i = 0; st.hasMoreTokens(); i++) {
            bookmarkId = st.nextToken();
            logger.debug("bookmark ID" + bookmarkId);
            BookmarkDAO bookmarkDAO = new BookmarkDAO();
            updated = bookmarkDAO.updateBookmarkOrder(bookmarkId, i);
            logger.debug("Records Updated" + updated);
        }
        return returnVal;
    }

    //simple validation
    @Override
    public void validate() {
        logger.debug("in the validate of BookmarkAction");
        if (getActionName().equals("new_bookmark")) {
            logger.debug("in new_bookmark");
        } else if (getActionName().equals("update_bookmark_order")) {
            logger.debug("in update_bookmark_order");
        } else if (getActionName().equals("move_bookmark")) {
            logger.debug("in move_bookmark");
        } else if (getActionName().equals("save_bookmark")) {
            if (getBookmarkName() == null || "".equals(getBookmarkName())) {
                addFieldError("bookmarkName", "Bookmark title cant be empty");
            }

            if (getHiperLink() == null || "".equals(getHiperLink())) {
                addFieldError("hiperLink", "Bookmark/URL cant be empty");
            }

            if (getCategoryId().equals("-1")) {
                addFieldError("categoryId", "Please select a category");
            }
        } else if (getActionName().equals("update_bookmark")) {
            if (getBookmarkName() == null || "".equals(getBookmarkName())) {
                addFieldError("bookmarkName", "Bookmark title cant be empty");
            }

            if (getHiperLink() == null || "".equals(getHiperLink())) {
                addFieldError("hiperLink", "Bookmark/URL cant be empty");
            }

            if (getCategoryId().equals("-1")) {
                addFieldError("categoryId", "Please select a category");
            }
        } else {
            logger.debug("in else action");
        }
        logger.debug("action");
    }

    public String getActionName() {
        ActionContext context = ActionContext.getContext();
        return context.getName();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getBookmarkName() {
        return bookmarkName;
    }

    public void setBookmarkName(String bookmarkName) {
        this.bookmarkName = bookmarkName;
    }

    public String getHiperLink() {
        return hiperLink;
    }

    public void setHiperLink(String hiperLink) {
        this.hiperLink = hiperLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookmarkOrder() {
        return bookmarkOrder;
    }

    public void setBookmarkOrder(String bookmarkOrder) {
        this.bookmarkOrder = bookmarkOrder;
    }
}
