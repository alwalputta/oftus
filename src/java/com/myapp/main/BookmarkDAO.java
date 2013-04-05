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
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author palwal
 */
public class BookmarkDAO {

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
}
