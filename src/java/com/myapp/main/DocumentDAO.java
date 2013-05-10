/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import com.myapp.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author palwal
 */
public class DocumentDAO {
    static final Logger logger = Logger.getLogger(DocumentDAO.class);

    @SuppressWarnings("unchecked")
    public void uploadDocument(Document document) {
        logger.debug("getFileName:" + document.getFileName());
        logger.debug("getFileType:" + document.getFileType());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(document);
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
    }

    @SuppressWarnings("unchecked")
    public Document getPicture(String userId) {
        logger.debug("listPictures()");
        Document document = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("from Document d where d.fileType = 'profile_pic' and d.status='A' and d.userId = :userId");
            query.setParameter("userId", userId);

            logger.debug("query:" + query.toString());

            List documents = query.list();
            logger.debug("list size:" + documents.size());

            for (Iterator iterator = documents.iterator(); iterator.hasNext();) {
                logger.debug("11111111");
                document = (Document) iterator.next();
            }
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            logger.debug("Exception");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return document;
    }
}
