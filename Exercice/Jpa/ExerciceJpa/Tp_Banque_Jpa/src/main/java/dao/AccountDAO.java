package dao;

import model.Account;
import model.Agency;
import model.Customer;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountDAO extends BaseDAO<Account> {

    public AccountDAO(EntityManager em) {
        super(em);
    }

    @Override
    public boolean Add(Account element){
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
            Account account = _em.find(Account.class,id);
            if(account != null){
                _em.getTransaction().begin();
                _em.remove(account);
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
    public Account findById(int id){
        Account account = null;
        account = _em.find(Account.class,id);
        _em.close();
        return account;
    }

    @Override
    public List<Account> findAll(){
        List<Account> accounts = null;
        accounts = _em.createQuery("select a from Account a", Account.class).getResultList();
        _em.close();
        return accounts;
    }

    public boolean addCustomerToAccount (int idCustomer , int idAccount){
        try{
            Account account = _em.find(Account.class,idAccount);
            Customer customer = _em.find(Customer.class,idCustomer);
            if (account != null && customer != null){
                account.addCustomer(customer);
                customer.addAccount(account);
                _em.getTransaction().begin();
                _em.persist(account);
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

    public boolean addAgencyToAccount (int idAgency , int idAccount){
        try{
            Account account = _em.find(Account.class,idAccount);
            Agency agency = _em.find(Agency.class,idAgency);
            if (account != null && agency != null){
                account.setAgency(agency);
                _em.getTransaction().begin();
                _em.persist(account);
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

    public boolean deleteCustomerToAccount (int idCUstomer , int idAccount){
        try{
            Account account = _em.find(Account.class,idAccount);
            Customer customer = _em.find(Customer.class,idCUstomer);
            if (account != null && customer != null){
                account.deleteCustomer(customer);
                customer.deleteAcount(account);
                _em.getTransaction().begin();
                _em.persist(account);
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


}
