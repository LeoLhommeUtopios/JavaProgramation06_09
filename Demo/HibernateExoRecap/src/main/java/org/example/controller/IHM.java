package org.example.controller;

import org.example.utils.SessionfactorySingleton;

import java.util.Scanner;

public class IHM {
    private final Scanner sc;
    private final IHMCustomer ihmCustomer;
    private final IHMSell ihmSell;
    private final IHMProduct ihmProduct;


    public IHM (){
        sc = new Scanner(System.in);
        ihmSell = new IHMSell(sc);
        ihmCustomer = new IHMCustomer(sc);
        ihmProduct = new IHMProduct(sc);
    }

    public void start (){
        String entry;
         while (true){
             System.out.println(" --- Stock gestion ---");
             System.out.println("1/ Customer Menu");
             System.out.println("2/ Sell Menu");
             System.out.println("3/ Product Menu");
             entry = sc.nextLine();
             switch (entry){
                 case "1"-> ihmCustomer.start();
                 case "2"-> ihmSell.start();
                 case "3"-> ihmProduct.start();
                 default -> {
                     SessionfactorySingleton.closeSessionFactory();
                     return;
                 }
             }
         }
    }
}
