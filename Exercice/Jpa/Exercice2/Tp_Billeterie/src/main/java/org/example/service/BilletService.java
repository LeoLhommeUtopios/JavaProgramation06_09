package org.example.service;

import org.example.entity.Adresse;
import org.example.entity.Billet;
import org.example.entity.Client;
import org.example.entity.Evenement;
import org.example.exception.NotFoundException;
import org.example.repository.BilletRepository;
import org.example.repository.EvenementRepository;
import org.example.util.CategoryPlace;
import org.example.util.EntityManagerSingleton;

import java.time.LocalDateTime;
import java.util.List;

public class BilletService implements BaseService<Billet> {

    private BilletRepository repository;
    private EvenementService evenementService;
    private ClientService clientService;

    public BilletService() {
        repository = new BilletRepository(EntityManagerSingleton.getEntityManager());
        evenementService = new EvenementService();
        clientService = new ClientService();
    }

    public Billet create (int numPlace, CategoryPlace categoryPlace, long idClient, long idEvent){
       try {
           Billet billet = Billet.builder()
                   .numPlace(numPlace)
                   .categoryPlace(categoryPlace)
                   .client(clientService.findById(idClient))
                   .evenement(evenementService.findById(idEvent))
                   .build();

           repository.save(billet);
           return billet;
       }catch (NotFoundException ex){
           System.out.println("erreure dans l'id client ou dans l'id evenement");
           return null;
       }
    }

    public Billet update (long id,int numPlace, CategoryPlace categoryPlace, long idClient, long idEvent){
        Billet billet = repository.getById(Billet.class,id);
        if(billet != null){
            try{
                billet.setNumPlace(numPlace);
                billet.setCategoryPlace(categoryPlace);
                billet.setClient(clientService.findById(idClient));
                billet.setEvenement(evenementService.findById(idEvent));

                repository.save(billet);
                return billet;
            }catch (NotFoundException ex){
                System.out.println("erreure dans l'id client ou dans l'id evenement");
                return null;
            }

        }
        throw new NotFoundException("Aucun client a l'id :"+id);
    }

    @Override
    public boolean delete(long id) {
        Billet billet = repository.getById(Billet.class,id);
        if(billet != null){
            repository.delete(billet);
            return true;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);
    }

    @Override
    public Billet findById(long id) {
        Billet billet = repository.getById(Billet.class,id);
        if(billet != null){
            return billet;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);
    }

    @Override
    public List<Billet> getAll() {
        return repository.getAll();
    }
}
