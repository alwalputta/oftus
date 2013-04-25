/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import com.myapp.admin.StateDAO;
import com.myapp.util.HibernateUtil;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author palwal
 */
public class DocumentDAO {

    private ArrayList<Bookmark> bookmarks;
    static final Logger logger = Logger.getLogger(StateDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<Bookmark> listBookmarks(int categoryId) {
        logger.debug("listBookmarks()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            bookmarks = new ArrayList<Bookmark>();
            bookmarks = (ArrayList<Bookmark>) session.createQuery("from Bookmark where status='A' order by bookmarkName").list();
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
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("update categorybookmark set bookmark_order = ? where bookmark_id = ?");
            query.setInteger(0, bookmarkOrder);
            query.setInteger(1, new Integer(bookmarkId).intValue());
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
    public int updateBookmarkCategory(String bookmarkId, String categoryId) {
        logger.debug("bookmarkId:" + bookmarkId);
        logger.debug("categoryId:" + categoryId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        int returnVal = 0;
        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("update categorybookmark set category_id = ? where bookmark_id = ?");
            query.setInteger(0, new Integer(categoryId).intValue());
            query.setInteger(1, new Integer(bookmarkId).intValue());
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
    public Bookmark getBookmark(int bookmarkId) {
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
    public int openBookmark(int userId, int bookmarkId) {
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
