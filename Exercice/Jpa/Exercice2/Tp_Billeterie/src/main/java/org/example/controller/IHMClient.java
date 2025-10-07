package org.example.controller;

import org.example.entity.Client;
import org.example.exception.NotFoundException;
import org.example.service.ClientService;

import javax.swing.event.CaretListener;
import java.util.List;
import java.util.Scanner;

public class IHMClient {
    private Scanner sc;
    private ClientService clientService;

    public IHMClient(Scanner sc) {
        this.sc = sc;
        clientService = new ClientService();
    }

    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Menu Client -----");
            System.out.println("1/ crée un client");
            System.out.println("2/ update un client");
            System.out.println("3/ supprimer un client");
            System.out.println("4/ recuperer un client par sont id");
            System.out.println("5/ recuperer tout les clients");
            entry = sc.nextLine();
            switch(entry){
                case "1"-> createClient();
                case "2"-> updateClient();
                case "3"-> deleteClient();
                case "4"-> getById();
                case "5"-> getAll();
                default -> {
                    return;
                }
            }
        }
    }

    private void createClient (){
        System.out.println(" -- creation d'un client --");
        System.out.println("nom :");
        String nom = sc.nextLine();
        System.out.println("prenom :");
        String prenom = sc.nextLine();
        System.out.println("l'age :");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("telephone :");
        String telephone = sc.nextLine();
        System.out.println("ville :");
        String ville = sc.nextLine();
        System.out.println("rue :");
        String rue = sc.nextLine();

        Client client = clientService.create(nom,prenom,age,telephone,rue,ville);

        System.out.println("client crée : " + client);
    }

    private void updateClient (){
        System.out.println(" -- mise a jour d'un client --");
        System.out.println("id du client :");
        long id = sc.nextLong();
        sc.nextLine();

        Client client = clientService.findById(id);

        System.out.println("nom ("+client.getNom()+") :");
        String nom = sc.nextLine();
        System.out.println("prenom ("+client.getPrenom()+") :");
        String prenom = sc.nextLine();
        System.out.println("age ("+client.getAge()+") :");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("telephone ("+client.getTelephone()+") :");
        String telephone = sc.nextLine();
        System.out.println("ville ("+client.getAdresse().getVille()+") :");
        String ville = sc.nextLine();
        System.out.println("rue ("+client.getAdresse().getRue()+") :");
        String rue = sc.nextLine();


        client = clientService.update(id,
                nom != null? nom: client.getNom(),
                prenom !=null? prenom : client.getPrenom(),
                age != 0? age:client.getAge(),
                telephone != null ? telephone : client.getTelephone(),
                rue != null ? rue:client.getAdresse().getRue(),
                ville != null? ville : client.getAdresse().getVille());
        System.out.println("client modifié : "+client);
    }

    private void deleteClient (){
        try{
            System.out.println(" -- suppresion d'un client --");
            System.out.println(" id du client a supprimer :");
            long id = sc.nextLong();
            sc.nextLine();

            if(clientService.delete(id)){
                System.out.println("CLient supprimé");
            }
            else{
                System.out.println("erreure lors de la suppresion");
            }
        }catch (NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getById (){
        try{
            System.out.println(" -- recuperation d'un client par son id --");
            System.out.println(" id du client :");
            long id = sc.nextLong();
            sc.nextLine();

            System.out.println(clientService.findById(id));

        }catch (NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getAll (){
        List<Client> clients = clientService.getAll();
        for (Client client : clients){
            System.out.println(client);
        }
    }
}
