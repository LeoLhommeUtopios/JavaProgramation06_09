package org.example.repository;

import org.example.entity.product.Product;
import org.hibernate.query.Query;

import java.util.List;

public class ProductRepository extends BaseRepository<Product> {
    @Override
    public Product findById(int id) {
        session = sessionFactory.openSession();
        Product product = session.get(Product.class,id);
        session.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = null;
        session = sessionFactory.openSession();
        Query<Product> produitQuery = session.createQuery("from Product ",Product.class);
        productList = produitQuery.list();
        session.close();
        return productList;
    }
}
