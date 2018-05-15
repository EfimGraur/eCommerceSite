package com.emusicstore.dao.impl;

import com.emusicstore.dao.ProductDao;
import com.emusicstore.model.Product;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
@org.springframework.transaction.annotation.Transactional
public class ProductDaoImpl implements ProductDao {
    //todo: avoid using Autowire
    @Autowired
    private SessionFactory sessionFactory;

    public void addProduct(Product product) {
        //todo: code duplication
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();

    }

    public Product getProductById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class, id);
        session.flush();

        return product;
    }

    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Product");
        List<Product> products = query.list();
        session.flush();
        return products;
    }

    public void deleteProducts(String id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getProductById(id));
    }
}
