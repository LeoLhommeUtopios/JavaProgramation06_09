package org.example.controller;

import org.example.entity.Billet;
import org.example.entity.Evenement;
import org.example.exception.NotFoundException;
import org.example.service.BilletService;
import org.example.util.CategoryPlace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class IHMBillet {
    private Scanner sc;
    private BilletService billetService;


    public IHMBillet(Scanner sc) {
        this.sc = sc;
        billetService = new BilletService();
    }

    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Menu Billet -----");
            System.out.println("1/ create Billet");
            System.out.println("2/ update un billet");
            System.out.println("3/ supprimer un billet");
            System.out.println("4/ recuperer un billet par sont id");
            System.out.println("5/ recuperer tout les billet");
            entry = sc.nextLine();
            switch(entry){
                case "1"-> createBillet();
                case "2"-> updateBillet();
                case "3"-> deleteBillet();
                case "4"-> getById();
                case "5"-> getAll();
                default -> {
                    return;
                }
            }
        }
    }

    private void createBillet (){
        System.out.println(" -- creation d'un Billet --");
        System.out.println("numeros de la place :");
        int numplace = sc.nextInt();
        sc.nextLine();

        System.out.println("selection de la categorie de la place :");
        for (CategoryPlace categoryPlace : CategoryPlace.values()){
            System.out.println((categoryPlace.ordinal()+1) +categoryPlace.toString());
        }
        String categori = sc.nextLine();
        CategoryPlace categoryPlace = switch (categori){
            case "1" -> CategoryPlace.STANDARD;
            case "2" -> CategoryPlace.GOLD;
            case "3" -> CategoryPlace.VIP;
            default -> CategoryPlace.STANDARD;
        };

        System.out.println("id client :");
        int idClient = sc.nextInt();
        sc.nextLine();
        System.out.println("id evenement :");
        int idEvenement = sc.nextInt();
        sc.nextLine();


        Billet billet = billetService.create(numplace,categoryPlace,idClient,idEvenement);

        System.out.println("billet crée : " + billet);
    }

    private void updateBillet (){
        System.out.println(" -- Update d'un Billet --");
        System.out.println("id du billet :");
        long id = sc.nextLong();
        sc.nextLine();
        Billet billet = billetService.findById(id);


        System.out.println("numeros de la place ("+billet.getNumPlace()+") :");
        int numplace = sc.nextInt();
        sc.nextLine();

        System.out.println("selection de la categorie de la place ("+billet.getCategoryPlace()+") :");
        for (CategoryPlace categoryPlace : CategoryPlace.values()){
            System.out.println((categoryPlace.ordinal()+1) +categoryPlace.toString());
        }
        System.out.println("4 pour ne pas changer");

        String categori = sc.nextLine();
        CategoryPlace categoryPlace = switch (categori){
            case "1" -> CategoryPlace.STANDARD;
            case "2" -> CategoryPlace.GOLD;
            case "3" -> CategoryPlace.VIP;
            default -> null;
        };

        System.out.println("id client ("+billet.getClient()+") :");
        long idClient = sc.nextLong();
        sc.nextLine();
        System.out.println("id evenement ("+billet.getEvenement()+") :");
        long idEvenement = sc.nextLong();
        sc.nextLine();


        billet = billetService.update(id,
                numplace != 0? numplace: billet.getNumPlace(),
                categoryPlace !=null? categoryPlace : billet.getCategoryPlace(),
                idClient != 0? idClient:billet.getClient().getId(),
                idEvenement != 0 ? idEvenement:billet.getEvenement().getId());
        System.out.println("billet modifié : "+billet);
    }

    private void deleteBillet (){
        try{
            System.out.println(" -- suppresion d'un billet --");
            System.out.println(" id du billet a supprimer :");
            long id = sc.nextLong();
            sc.nextLine();

            if(billetService.delete(id)){
                System.out.println("billet supprimé");
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
            System.out.println(" -- recuperation d'un billet par son id --");
            System.out.println(" id du billet :");
            long id = sc.nextLong();
            sc.nextLine();

            System.out.println(billetService.findById(id));

        }catch (NotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void getAll (){
        List<Billet> billets = billetService.getAll();
        for (Billet billet : billets){
            System.out.println(billet);
        }
    }

}
