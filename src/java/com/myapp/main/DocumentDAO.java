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
        Transaction transaction = null;
        int returnVal = 0;
        
        Document document = new Document();
        document.setFileName(fileName);
        document.setContent(content);
        document.setContentType(contentType);

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
}
