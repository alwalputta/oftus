/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import com.myapp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author palwal
 */
public class CredentialDAO {

    static final Logger logger = Logger.getLogger(CredentialDAO.class);

    /**
     *
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public Credential selectCredential(String username) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Credential credential = null;

        try {
            logger.debug("username:" + username);
            transaction = session.beginTransaction();

            Criteria c = session.createCriteria(Credential.class, "c")
                    .createAlias("c.users", "u")
                    .add(Restrictions.eq("c.username", username))
                    .add(Restrictions.eq("u.status", "A"));

//                    .setMaxResults(1)
            List list = c.list();

            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                credential = (Credential) iterator.next();
            }
            logger.debug("username:" + username);

            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            logger.debug("Exception");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return credential;
    }

    /**
     *
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public Credential selectInactiveCredential(String username) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Credential credential = null;

        try {
            logger.debug("username:" + username);
            transaction = session.beginTransaction();

            logger.debug("username:" + username);
            Query query = session.createQuery("from Credential where username = '" + username + "'");
            List list = query.list();

            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                credential = (Credential) iterator.next();
            }
            logger.debug("username:" + username);

            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } catch (Exception e) {
            logger.debug("Exception");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return credential;
    }

    /**
     *
     * @param username
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean isUsernameAvailable(String username) {

        ArrayList<Credential> credentials = null;
        logger.debug("here...");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            credentials = new ArrayList<Credential>();
            credentials = (ArrayList<Credential>) session.createQuery("from Credential where username = :username")
                    .setParameter("username", username)
                    .list();
            transaction.commit();

            logger.debug("credentials size:" + credentials.size());
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }

        if (credentials.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
