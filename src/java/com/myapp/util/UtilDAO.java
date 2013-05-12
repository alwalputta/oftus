/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author palwal
 */
public class UtilDAO {
    static final Logger logger = Logger.getLogger(UtilDAO.class);

    @SuppressWarnings("unchecked")
    public void recordClickLog(ClickLog pageClick) {
        logger.debug("userId:" + pageClick.getUserId());
        logger.debug("actionName:" + pageClick.getActionName());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(pageClick);
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
    public void recordLoginLog(LoginLog loginLog) {
        logger.debug("actionName:" + loginLog.getSessionId());

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(loginLog);
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
