/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.main;

import com.myapp.admin.StateDAO;
import com.myapp.admin.User;
import com.myapp.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author palwal
 */
public class CategoryDAO {

    static final Logger logger = Logger.getLogger(StateDAO.class);

    @SuppressWarnings("unchecked")
    public ArrayList<Category> listCategories() {
        logger.debug("listCategories()");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            transaction = session.beginTransaction();
//            categories = new ArrayList<Category>();
//            categories = (ArrayList<Category>) session.createQuery("from Category where status='A'").list();

            
            
            Criteria c = session.createCriteria(Category.class, "c")
                    .createAlias("c.bookmarks", "b")
                    .addOrder(Order.asc("c.order"))
                    .addOrder(Order.asc("b.order"));
            categories = (ArrayList<Category>)c.list();
            
            
            
            transaction.commit();
        } catch (HibernateException e) {
            logger.debug("HibernateException");
            e.printStackTrace();
            transaction.rollback();
        } finally {
            logger.debug("finally block");
            session.close();
        }
        return categories;
    }

    @SuppressWarnings("unchecked")
    public Category selectCategory(String categoryId) {
        logger.debug("categoryId:" + categoryId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Category category = new Category();
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            transaction = session.beginTransaction();
            category = (Category) session.get(Category.class, categoryId);

            
            
            
            Criteria c = session.createCriteria(Category.class, "c")
                    .createAlias("c.bookmarks", "b")
                    .add(Restrictions.eq("c.categoryId", categoryId))
                    .add(Restrictions.eq("u.status", "A"))
                    .addOrder(Order.asc("c.order"))
                    .addOrder(Order.asc("b.order"));
            categories = (ArrayList<Category>)c.list();
            
            
            for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
                category = (Category) iterator.next();
            }
            
            
            transaction.commit();
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
        return category;
    }

    @SuppressWarnings("unchecked")
    public void updateCategory(Category category) {
        logger.debug("categoryId:" + category.getCategoryId());
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
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
    }

    @SuppressWarnings("unchecked")
    public int updateCategoryOrder(String categoryId, int categoryOrder) {
        logger.debug("categoryId:" + categoryId);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction;
        int returnVal = 0;
        Category category;
        try {
            transaction = session.beginTransaction();
//            SQLQuery query = session.createSQLQuery("update usercategory set category_order = ? where category_id = ?");
//            query.setInteger(0, categoryOrder);
//            query.setString(1, categoryId);
//            returnVal = query.executeUpdate();

            category = (Category) session.get(Category.class, categoryId);
            category.setOrder(categoryOrder);
            session.update(category);
            transaction.commit();
            logger.debug("right after commit");
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
        return returnVal;
    }
}
