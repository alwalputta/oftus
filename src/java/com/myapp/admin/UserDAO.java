/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.admin;

import com.myapp.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

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
//            session.flush();
//            session.refresh(user);
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
//            session.flush();
//            session.refresh(user);
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
        logger.debug("selectUser.121212:" + userId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = new User();
        try {
//            user = (User) session.get(User.class, userId);
//            session.flush();
//            session.refresh(user);

            Criteria c = session.createCriteria(User.class, "u")
                    .add(Expression.eq("userId", userId))
                    .createAlias("u.userCategories", "c")
                    .createAlias("c.bookmarks", "b")
                    .addOrder(Order.asc("c.order"))
                    .addOrder(Order.asc("b.order"));
            List list = c.list();

            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                user = (User) iterator.next();
            }

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
