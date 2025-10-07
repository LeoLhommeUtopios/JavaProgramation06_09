package org.example.repository;

import javax.persistence.EntityManager;

// <T> declare que notre classe est generique,
// ce qui veux dire qu'elle s'adaptera au objet que l'on lui donnera
public abstract class  BaseRepository<T> {
    protected EntityManager em;

    public BaseRepository (EntityManager em){
        this.em = em;
    }

    public void save (T element){
        try{
            em.getTransaction().begin();
            em.persist(element);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public void delete (T element){
        try{
            em.getTransaction().begin();
            em.remove(element);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    public T getById (Class<T> classe, long id){
        return em.find(classe,id);
    }
}
