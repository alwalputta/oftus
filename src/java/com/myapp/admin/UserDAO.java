/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

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
public class UserDAO {

    static final Logger logger = Logger.getLogger(UserDAO.class);

    /**
     *
     * @param user
     * @return
     */
    @SuppressWarnings("unchecked")
    public int saveUser(User user) {
        logger.debug("saveUser()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            session.flush();
            session.refresh(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return user.getUserId();
    }

    @SuppressWarnings("unchecked")
    public void updateUser(User user) {
        logger.debug("In the UserDAO updateUser()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.flush();
            session.refresh(user);
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return;
    }

    @SuppressWarnings("unchecked")
    public User selectUserFromCredentialID(int credentialID) {
        logger.debug("credentialId:" + credentialID);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int userId = 0;
        User user = new User();
        try {
            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("select uc.user_id from UserCredential uc where uc.credential_id = :credentialId");
            query.setParameter("credentialId", credentialID);

            logger.debug("query:" + query.toString());

            List users = query.list();
            logger.debug("list size:" + users.size());

            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                logger.debug("11111111");
                userId = (Integer) iterator.next();
                logger.debug("userId value:" + userId);
            }
            user = (User) session.get(User.class, userId);
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
        return user;
    }

    @SuppressWarnings("unchecked")
    public User selectUser(int userId) {
        logger.debug("userId:" + userId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User();
        try {
            user = (User) session.get(User.class, userId);
            session.flush();
            session.refresh(user);
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
        return user;
    }
}
