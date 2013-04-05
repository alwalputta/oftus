package com.myapp.util;

import com.myapp.main.Category;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    
    public static void main(String[] args) {
        Main obj = new Main();

        Category category = new Category("Family222", "this is used for family related links");
        Long id;
        id = obj.saveCategory(category);
        System.out.println("ALWAL CATEGORY ID:" + id);
        obj.listCategory();
        obj.updateCategory(new Long(10), "ALWAL CATEGORY");
        obj.deleteCategory(new Long(21));
    }

    
    public Long saveCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long categoryId = null;
        try {
            transaction = session.beginTransaction();
            categoryId = (Long) session.save(category);
//            System.out.println("Before commit");
            transaction.commit();
//            System.out.println("After commit");
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return categoryId;
    }

    
    public void listCategory() {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
        try {
//            transaction = session.beginTransaction();
            List categories = session.createQuery("from Category").list();
            for (Iterator iterator = categories.iterator(); iterator.hasNext();) {
                Category category = (Category) iterator.next();
                System.out.println(category.getCategoryName());
            }
//            transaction.commit();
        } catch (HibernateException e) {
        } finally {
            session.close();
        }
    }

    
    public void updateCategory(Long categoryId, String categoryName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category category = (Category) session.get(Category.class, categoryId);
            category.setCategoryName(categoryName);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    
    public void deleteCategory(Long categoryId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Category category = (Category) session.get(Category.class, categoryId);
            session.delete(category);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
