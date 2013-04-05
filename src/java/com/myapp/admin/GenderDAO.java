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
public class GenderDAO {

    private ArrayList<Gender> genders;
    static final Logger logger = Logger.getLogger(GenderDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<Gender> listGenders() {
        logger.debug("listGenders()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            genders = new ArrayList<Gender>();
            genders = (ArrayList<Gender>) session.createQuery("from Gender where status='A' order by genderName").list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return genders;
    }
}
