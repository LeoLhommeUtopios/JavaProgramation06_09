package org.example.repository;

import org.example.entity.Client;
import org.example.entity.Evenement;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EvenementRepository extends BaseRepository<Evenement> {
    public EvenementRepository(EntityManager em) {
        super(em);
    }

    public List<Evenement> getAll (){
        TypedQuery<Evenement> query = em.createQuery("select e from Evenement e", Evenement.class);
        return query.getResultList();
    }
}
