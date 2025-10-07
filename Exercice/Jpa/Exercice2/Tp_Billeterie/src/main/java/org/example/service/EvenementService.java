package org.example.service;

import org.example.entity.Adresse;
import org.example.entity.Client;
import org.example.entity.Evenement;
import org.example.exception.NotFoundException;
import org.example.repository.EvenementRepository;
import org.example.util.EntityManagerSingleton;

import java.time.LocalDateTime;
import java.util.List;

public class EvenementService implements BaseService<Evenement> {

    private EvenementRepository repository;

    public EvenementService() {
        repository = new EvenementRepository(EntityManagerSingleton.getEntityManager());
    }

    public Evenement create (String name, LocalDateTime dateEvent, int nbrPlace,String rue, String ville){
        Evenement evenement = Evenement.builder()
                .name(name)
                .dateEvenement(dateEvent)
                .nbrPlace(nbrPlace)
                .adresse(
                        Adresse.builder()
                                .rue(rue)
                                .ville(ville)
                                .build()
                )
                .build();

        repository.save(evenement);
        return evenement;
    }

    public Evenement update (long id,String name, LocalDateTime dateEvent, int nbrPlace,String rue, String ville){
        Evenement evenement = repository.getById(Evenement.class,id);
        if(evenement != null){
            evenement.setName(name);
            evenement.setDateEvenement(dateEvent);
            evenement.setNbrPlace(nbrPlace);
            evenement.setAdresse(Adresse.builder().ville(ville).rue(rue).build());

            repository.save(evenement);
            return evenement;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);


    }

    @Override
    public boolean delete(long id) {
        Evenement evenement = repository.getById(Evenement.class,id);
        if(evenement != null){
            repository.delete(evenement);
            return true;
        }
        throw new NotFoundException("Aucun evenement a l'id :"+id);
    }

    @Override
    public Evenement findById(long id) {
        Evenement evenement = repository.getById(Evenement.class,id);
        if(evenement != null){
            return evenement;
        }
        throw new NotFoundException("Aucun evenement a l'id :"+id);
    }

    @Override
    public List<Evenement> getAll() {
        return repository.getAll();
    }
}
