package org.example.repository;

import org.example.entity.Billet;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BilletRepository extends BaseRepository<Billet> {

    public BilletRepository(EntityManager em) {
        super(em);
    }


    public List<Billet> getAll (){
        TypedQuery<Billet> query = em.createQuery("select b from Billet b", Billet.class);
        return query.getResultList();
    }
}
