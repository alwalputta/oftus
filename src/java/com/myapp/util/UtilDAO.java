/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.util;

import com.myapp.main.*;
import com.myapp.admin.StateDAO;
import com.myapp.util.HibernateUtil;
import java.util.ArrayList;
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
public class UtilDAO {
    static final Logger logger = Logger.getLogger(UtilDAO.class);

    @SuppressWarnings("unchecked")
    public void recordPageClick(PageClick pageClick) {
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
