package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Banque_jpa");
    private static EntityManager entityManager;

    private EntityManagerSingleton (){
        entityManager = emf.createEntityManager();
    }

    public static synchronized EntityManager getEntityManager (){
        if(entityManager == null){
            new EntityManagerSingleton();
        }
        return entityManager;
    }

    public static void close (){
        if(entityManager.isOpen()){
            entityManager.close();
        }
        if(emf.isOpen()){
            emf.close();
        }
    }

}
