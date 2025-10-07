package org.example.controller;

import org.example.entity.Customer;
import org.example.service.CustomerService;

import java.util.Scanner;

public class IHMCustomer {

    private final Scanner sc;

    private final CustomerService customerService;

    public IHMCustomer(Scanner sc) {
        this.sc = sc;
        customerService = new CustomerService();
    }

    public void start (){
        String entry;
        while (true){
            System.out.println(" --- Stock gestion ---");
            System.out.println("1/ create product");
            System.out.println("2/ update product");
            System.out.println("3/ delete product");
            System.out.println("3/ find product by id");
            System.out.println("3/ find all product");
            entry = sc.nextLine();
            switch (entry){
                case "1"-> create();
                case "2"-> update();
                case "3"-> delete();
                case "4"-> findById();
                case "5"-> findAll();
                default -> {return;}
            }
        }
    }

    private void create (){
        System.out.println("--- create customer ---");
        System.out.println("firstname :");
        String firstname = sc.nextLine();
        System.out.println("lastname :");
        String lastname = sc.nextLine();
        System.out.println("email :");
        String email = sc.nextLine();

        customerService.create(firstname,lastname,email);
    }


    private void update (){

    }

    private void delete (){

    }

    private void findById (){

    }

    private void findAll (){

    }
}
