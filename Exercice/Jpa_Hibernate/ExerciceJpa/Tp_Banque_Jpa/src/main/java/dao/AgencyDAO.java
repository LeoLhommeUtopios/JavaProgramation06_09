package dao;

import jdk.jshell.spi.ExecutionControl;
import model.Agency;

import javax.persistence.EntityManager;
import java.util.List;

public class AgencyDAO extends BaseDAO<Agency> {

    public AgencyDAO(EntityManager em) {
        super(em);
    }

    @Override
    public boolean Add(Agency element) {
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
            Agency agency = _em.find(Agency.class,id);
            if(agency != null){
                _em.getTransaction().begin();
                _em.remove(agency);
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
    public Agency findById(int id) {
        Agency agency = null;
        agency = _em.find(Agency.class,id);
        _em.close();
        return agency;
    }

    @Override
    public List<Agency> findAll() throws ExecutionControl.NotImplementedException {
        List<Agency> agencies = null;
        agencies = _em.createQuery("select a from Agency a", Agency.class).getResultList();
        _em.close();
        return agencies;
    }
}
