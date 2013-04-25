/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import com.myapp.admin.StateDAO;
import com.myapp.util.HibernateUtil;
import java.io.File;
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
    public void updateDocument(String fileName, File content, String contentType) {
        logger.debug("getFileName:" + fileName);
        logger.debug("getContentType:" + contentType);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        int returnVal = 0;

        try {
            transaction = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("update categorybookmark set category_id = ? where bookmark_id = ?");
//            query.setInteger(0, new Integer(categoryId).intValue());
//            query.setInteger(1, new Integer(bookmarkId).intValue());
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
        return;
    }
}
