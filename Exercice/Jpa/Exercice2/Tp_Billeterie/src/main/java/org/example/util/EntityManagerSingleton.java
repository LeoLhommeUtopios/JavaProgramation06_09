package org.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private EntityManagerSingleton (){
        emf = Persistence.createEntityManagerFactory("Billeterie");
        em = emf.createEntityManager();
    }

    public static synchronized EntityManager getEntityManager(){
        if(em == null || !em.isOpen() ){
            new EntityManagerSingleton();
        }
        return em;
    }


}
