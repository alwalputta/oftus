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
public class StateDAO {

    private ArrayList<State> states;
    static final Logger logger = Logger.getLogger(StateDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<State> listStates() {
        logger.debug("listStates()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            states = new ArrayList<State>();
            states = (ArrayList<State>) session.createQuery("from State where status='A' order by stateName").list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return states;
    }
}
