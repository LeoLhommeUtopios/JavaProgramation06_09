package org.example.repository;

import org.example.utils.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class BaseRepository<T> {

    protected SessionFactory sessionFactory;

    protected Session session;

    public BaseRepository(){
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public T create (T element){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(element);
            session.getTransaction().commit();
            return element;
        }catch (Exception e){
            session.getTransaction().rollback();
            return null;
        }
        finally {
            session.close();
        }
    }
    public T update (T element){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(element);
            session.getTransaction().commit();
            return element;
        }catch (Exception e){
            session.getTransaction().rollback();
            return null;
        }
            finally {
            session.close();
        }
    }

    public boolean delete (T element){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(element);
            session.getTransaction().commit();
            return true;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false;
        }
            finally {
            session.close();
        }
    }

    public abstract T findById (int id);

    public abstract List<T> findAll ();

}
