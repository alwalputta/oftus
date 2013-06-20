/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import com.myapp.util.HibernateUtil;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author palwal
 */
public class BookmarkDAO {

    static final Logger logger = Logger.getLogger(BookmarkDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<Bookmark> listBookmarks(String categoryId) {
        logger.debug("listBookmarks()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        ArrayList<Bookmark> bookmarks = new ArrayList<Bookmark>();
        try {
            transaction = session.beginTransaction();
//            bookmarks = new ArrayList<Bookmark>();
//            bookmarks = (ArrayList<Bookmark>) session.createQuery("from Bookmark where status='A'").list();

            Criteria c = session.createCriteria(Bookmark.class, "b")
                    .createAlias("b.userCategories", "c")
                    .add(Restrictions.eq("b.status", "A"))
                    .add(Restrictions.eq("c.categoryId", categoryId))
                    .addOrder(Order.asc("c.order"))
                    .addOrder(Order.asc("b.order"));

            bookmarks = (ArrayList<Bookmark>) c.list();

            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return bookmarks;
    }

    @SuppressWarnings("unchecked")
    public int updateBookmarkOrder(String bookmarkId, int bookmarkOrder) {
        logger.debug("categoryId:" + bookmarkId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        int returnVal = 0;
        Bookmark bookmark;
        try {
            transaction = session.beginTransaction();
//            SQLQuery query = session.createSQLQuery("update categorybookmark set bookmark_order = ? where bookmark_id = ?");
//            query.setInteger(0, bookmarkOrder);
//            query.setString(1, bookmarkId);
//            returnVal = query.executeUpdate();
//            transaction.commit();

            bookmark = (Bookmark) session.get(Bookmark.class, bookmarkId);
            bookmark.setOrder(bookmarkOrder);
            session.update(bookmark);
            transaction.commit();
            logger.debug("right after commit");

        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
        } catch (Exception e) {
            logger.debug("Exception");
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return returnVal;
    }

    @SuppressWarnings("unchecked")
    public int updateBookmarkCategory(String bookmarkId, String categoryId) {
        logger.debug("bookmarkId:" + bookmarkId);
        logger.debug("categoryId:" + categoryId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        int returnVal = 0;
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("update categorybookmark set category_id = ? where bookmark_id = ?");
            query.setString(0, categoryId);
            query.setString(1, bookmarkId);
            returnVal = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
        } catch (Exception e) {
            logger.debug("Exception");
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return returnVal;
    }

    @SuppressWarnings("unchecked")
    public Bookmark getBookmark(String bookmarkId) {
        logger.debug("bookmarkId:" + bookmarkId);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Bookmark bookmark = new Bookmark();

        try {
            bookmark = (Bookmark) session.get(Bookmark.class, bookmarkId);
            session.flush();
            session.refresh(bookmark);
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
        } catch (Exception e) {
            logger.debug("Exception");
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return bookmark;
    }

    @SuppressWarnings("unchecked")
    public String openBookmark(String userId, String bookmarkId) {
        logger.debug("userId:" + userId);
        logger.debug("bookmarkId:" + bookmarkId);

        Bookmark bookmark = getBookmark(bookmarkId);

        BookmarkClick bookmarkClick = new BookmarkClick();
        bookmarkClick.setBookmarkId(bookmark.getBookmarkId());
        bookmarkClick.setHiperLink(bookmark.getHiperLink());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(bookmarkClick);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return bookmarkClick.getBookmarkClickId();
    }
}
