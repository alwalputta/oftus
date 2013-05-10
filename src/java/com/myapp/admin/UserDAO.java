/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import com.myapp.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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
    public void saveUser(User user) {
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
        return;
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
    public User selectUser(String userId) {
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
