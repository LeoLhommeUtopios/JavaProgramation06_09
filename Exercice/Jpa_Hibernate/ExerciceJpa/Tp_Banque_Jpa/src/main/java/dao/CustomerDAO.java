package dao;

import model.Customer;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer> {
    public CustomerDAO(EntityManager em) {
        super(em);
    }

    @Override
    public boolean Add(Customer element) {
        try{
            _em.getTransaction().begin();
            _em.persist(element);
            _em.getTransaction().commit();
            return true;
        }catch (Exception e){
            if (_em.getTransaction().isActive()){
                _em.getTransaction().rollback();
            }
            return false;
        }
        finally {
            _em.close();
        }
    }

    @Override
    public boolean delete(int id) {
        try{
            Customer customer = _em.find(Customer.class,id);
            if(customer != null){
                _em.getTransaction().begin();
                _em.remove(customer);
                _em.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if (_em.getTransaction().isActive()){
                _em.getTransaction().rollback();
            }
            return false;
        }
        finally {
            _em.close();
        }
    }

    @Override
    public Customer findById(int id){
        Customer customer = null;
        customer = _em.find(Customer.class,id);
        _em.close();
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = null;
        customers = _em.createQuery("select c from Customer c", Customer.class).getResultList();
        _em.close();
        return customers;
    }
}
