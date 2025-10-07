package org.example.controller;

import java.util.Scanner;

public class IHMGLobal {

    private Scanner sc;
    private IHMClient ihmClient;
    private IHMBillet ihmBillet;
    private IHMEvenement ihmEvenement;

    public IHMGLobal() {
        this.sc = new Scanner(System.in);
        ihmBillet = new IHMBillet(sc);
        ihmClient = new IHMClient(sc);
        ihmEvenement = new IHMEvenement(sc);
    }

    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Billeterie -----");
            System.out.println("1/ Menu client");
            System.out.println("2/ Menu Billet");
            System.out.println("3/ Menu Evenement");
            entry = sc.nextLine();
            switch(entry){
                case "1"-> ihmClient.start();
                case "2"-> ihmBillet.start();
                case "3"-> ihmEvenement.start();
                default -> {
                    return;
                }
            }
        }
    }

}
