/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

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
public class EmailDAO {

    static final Logger logger = Logger.getLogger(EmailDAO.class);

    @SuppressWarnings("unchecked")
    public boolean isEmailAvailable(String emailAddress) {
        ArrayList<Email> emails = null;

        logger.debug("here...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            emails = new ArrayList<Email>();
            emails = (ArrayList<Email>) session.createQuery("from Email where email_address = :emailAddress")
                    .setParameter("emailAddress", emailAddress)
                    .list();
            transaction.commit();
            logger.debug("emails size:" + emails.size());
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }

        if (emails.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
