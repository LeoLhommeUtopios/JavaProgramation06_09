package org.example.repository;

import org.example.entity.Billet;
import org.example.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ClientRepository extends BaseRepository<Client> {
    public ClientRepository(EntityManager em) {
        super(em);
    }

    public List<Client> getAll (){
        TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);
        return query.getResultList();
    }
}
