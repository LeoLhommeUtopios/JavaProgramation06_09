package org.example.controller;

import org.example.entity.Client;
import org.example.entity.Evenement;
import org.example.exception.NotFoundException;
import org.example.service.EvenementService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class IHMEvenement {
    private Scanner sc;
    private EvenementService evenementService;

    public IHMEvenement(Scanner sc) {
        this.sc = sc;
        evenementService = new EvenementService();
    }

    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Menu Evenement -----");
            System.out.println("1/ create evenement");
            System.out.println("2/ update un evenement");
            System.out.println("3/ supprimer un evenement");
            System.out.println("4/ recuperer un evenement par sont id");
            System.out.println("5/ recuperer tout les evenements");
            entry = sc.nextLine();
            switch(entry){
                case "1"-> createEvenenement();
                case "2"-> updateEvenenement();
                case "3"-> deleteEvenement();
                case "4"-> getById();
                case "5"-> getAll();
                default -> {
                    return;
                }
            }
        }
    }

    private void createEvenenement (){
        System.out.println(" -- creation d'un evenement --");
        System.out.println("name :");
        String nom = sc.nextLine();
        System.out.println("date de l'event (dd-MM-yyyy HH:mm) :");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(sc.nextLine(),formatter);
        System.out.println("number of place :");
        int nbrPlace = sc.nextInt();
        sc.nextLine();
        System.out.println("ville :");
        String ville = sc.nextLine();
        System.out.println("rue :");
        String rue = sc.nextLine();

        Evenement evenement = evenementService.create(nom,localDateTime,nbrPlace,ville,rue);

        System.out.println("evenement crée : " + evenement);
    }

    private void updateEvenenement (){
        System.out.println(" -- update d'un evenement --");
        System.out.println("id de l'evenement :");
        long id = sc.nextLong();
        sc.nextLine();

        Evenement evenement = evenementService.findById(id);


        System.out.println("name ("+evenement.getName()+") :");
        String name = sc.nextLine();
        System.out.println("date de l'event (dd-MM-yyyy HH:mm) ("+evenement.getDateEvenement()+") :");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateStr = sc.nextLine();
        LocalDateTime localDateTime = null;
        if(dateStr != null){
            localDateTime = LocalDateTime.parse(dateStr,formatter);
        }

        System.out.println("number of place ("+evenement.getNbrPlace()+") :");
        Integer nbrPlace = sc.nextInt();
        sc.nextLine();
        System.out.println("ville :");
        String ville = sc.nextLine();
        System.out.println("rue :");
        String rue = sc.nextLine();


        evenement = evenementService.update(id,
                name != null? name: evenement.getName(),
                localDateTime !=null? localDateTime : evenement.getDateEvenement(),
                nbrPlace != 0? nbrPlace:evenement.getNbrPlace(),
                rue != null ? rue:evenement.getAdresse().getRue(),
                ville != null? ville : evenement.getAdresse().getVille());
        System.out.println("evenement modifié : "+evenement);
    }

    private void deleteEvenement (){
        try{
            System.out.println(" -- suppresion d'un evenement --");
            System.out.println(" id de l'evenement a supprimer :");
            long id = sc.nextLong();
            sc.nextLine();

            if(evenementService.delete(id)){
                System.out.println("Evenement supprimé");
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
            System.out.println(" -- recuperation d'un evenement par son id --");
            System.out.println(" id de l'evenement :");
            long id = sc.nextLong();
            sc.nextLine();

            System.out.println(evenementService.findById(id));

        }catch (NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getAll (){
        List<Evenement> evenements = evenementService.getAll();
        for (Evenement evenement : evenements){
            System.out.println(evenement);
        }
    }

}
