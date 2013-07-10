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
public class CountryDAO {

    private ArrayList<Country> countries;
    static final Logger logger = Logger.getLogger(CountryDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<Country> listCountries() {
        logger.debug("listCountries()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            countries = new ArrayList<Country>();
            countries = (ArrayList<Country>) session.createQuery("from Country where status='A' order by countryName").list();
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return countries;
    }
}
