package org.example.repository;

import org.example.entity.Customer;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepository extends BaseRepository<Customer> {
    @Override
    public Customer findById(int id) {
        session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class,id);
        session.close();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customersList = null;
        session = sessionFactory.openSession();
        Query<Customer> customerQuery = session.createQuery("from Customer ",Customer.class);
        customersList = customerQuery.list();
        session.close();
        return customersList;
    }
}
