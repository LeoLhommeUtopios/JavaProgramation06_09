package org.example.controller;

import org.example.entity.Customer;
import org.example.entity.Sell;
import org.example.entity.SellProduct;
import org.example.entity.product.Product;
import org.example.service.CustomerService;
import org.example.service.ProductService;
import org.example.service.SellProductService;
import org.example.service.SellService;
import org.example.utils.SellStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IHMSell {
    private final Scanner sc;

    private final SellService sellService;
    private final CustomerService customerService;
    private final SellProductService sellProductService;

    public IHMSell(Scanner sc) {
        this.sc = sc;
        sellService = new SellService();
        sellProductService = new SellProductService();
        customerService = new CustomerService();
    }

    public void start (){
        String entry;
        while (true){
            System.out.println(" --- Stock gestion ---");
            System.out.println("1/ create Customer");
            System.out.println("2/ update Customer");
            System.out.println("3/ delete Customer");
            System.out.println("4/ find customer by id");
            System.out.println("5/ find all customer");
            System.out.println("6/ add product to sell");
            entry = sc.nextLine();
            switch (entry){
                case "1"-> create();
                case "2"-> update();
                case "3"-> delete();
                case "4"-> findById();
                case "5"-> findAll();
                case "6" -> addProductTosell();
                default -> {return;}
            }
        }
    }

    private void create (){
        System.out.println("--- create sell ---");
        System.out.println("id customer :");
        int idCustomer = sc.nextInt();
        Customer customer = customerService.findById(idCustomer);
        sc.nextLine();

        sellService.create(customer);
    }

    private void addProductTosell(){
        System.out.println("--- create sell Product---");
        System.out.println("id sell :");
        int idSell = sc.nextInt();
        sc.nextLine();
        System.out.println("id product :");
        int idProduct = sc.nextInt();
        sc.nextLine();

        System.out.println("Quantity :");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.println("new sale Product "+ sellProductService.create(idProduct,idSell,quantity));

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
