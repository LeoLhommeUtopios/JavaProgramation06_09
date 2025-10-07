package org.example.service;

import org.example.entity.Adresse;
import org.example.entity.Client;
import org.example.exception.NotFoundException;
import org.example.repository.ClientRepository;
import org.example.util.EntityManagerSingleton;

import java.util.List;

public class ClientService implements BaseService<Client>{

    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository(EntityManagerSingleton.getEntityManager());
    }

    public Client create (String nom, String prenom, int age,String telephone,String rue, String ville){
        Client client = Client.builder()
                .nom(nom)
                .prenom(prenom)
                .age(age)
                .telephone(telephone)
                .adresse(
                        Adresse.builder()
                        .rue(rue)
                        .ville(ville)
                        .build()
                )
                .build();

        clientRepository.save(client);
        return client;
    }

    public Client update (long id,String nom, String prenom, int age,String telephone,String rue, String ville){
        Client client = clientRepository.getById(Client.class,id);
        if(client != null){
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setAge(age);
            client.setTelephone(telephone);
            client.setAdresse(Adresse.builder().ville(ville).rue(rue).build());

            clientRepository.save(client);
            return client;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);


    }

    @Override
    public boolean delete(long id) {
        Client client = clientRepository.getById(Client.class,id);
        if(client != null){
            clientRepository.delete(client);
            return true;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);
    }

    @Override
    public Client findById(long id) {
        Client client = clientRepository.getById(Client.class,id);
        if(client != null){
            return client;
        }
        throw new NotFoundException("Aucun client a l'id :"+id);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }
}
