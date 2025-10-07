package util;

import dao.AccountDAO;
import dao.AgencyDAO;
import dao.CustomerDAO;
import model.Account;
import model.Agency;
import model.Customer;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IHM {
    private Scanner scanner;
    private CustomerDAO customerDAO;
    private AgencyDAO agencyDAO;
    private AccountDAO accountDAO;
    private EntityManager em;

    public IHM() {
        scanner = new Scanner(System.in);
        em = EntityManagerSingleton.getEntityManager();
    }

    public void start() {
        int entry;
        do {
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    deleteCustomer();
                    break;
                case 3:
                    addAccount();
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    addAgency();
                    break;
                case 6:
                    deleteAgency();
                    break;
                case 7:
                    addAgencyToAccount();
                    break;
                case 8:
                    addCustomerToAccount();
                    break;
                case 9:
                    deleteCustomerToAccount();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("entrer une valeur correcte");
                    break;
            }
        } while (entry != 0);
        EntityManagerSingleton.close();
    }

    public void menu() {
        System.out.println("------- Menu -------");
        System.out.println("1-- creation d'utilisateur");
        System.out.println("2-- suppresion d'utilisateur");
        System.out.println("----------------------");
        System.out.println("3-- creation de compte");
        System.out.println("4-- suppression de compte");
        System.out.println("----------------------");
        System.out.println("5-- creation d'agence");
        System.out.println("6-- suppresion d'agence");
        System.out.println("----------------------");
        System.out.println("7-- ajouter agence a un compte");
        System.out.println("8-- ajouter utilisateur a un compte");
        System.out.println("9-- suppresion d'un utilisateur d'un compte");
        System.out.println("----------------------");
        System.out.println("0-- quitter");
    }

    //fonction d'ajout
    public void addCustomer() {
        System.out.println("-------- creation utilisateur -------");
        System.out.println("nom :");
        String lastName = scanner.nextLine();
        System.out.println("prenom :");
        String firstName = scanner.nextLine();
        System.out.println("Date de naissance (dd-MM-yyyy):");
        String dateString = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Customer customer = new Customer(lastName, firstName, birthDate);
        customerDAO = new CustomerDAO(em);
        if (customerDAO.Add(customer)) {
            System.out.println("utilisateur ajouté");
        } else {
            System.out.println("erreure lors de l'ajout");
        }
    }
    public void addAccount() {
        System.out.println("-------- creation de compte -------");
        System.out.println("libelle :");
        String libelle = scanner.nextLine();
        System.out.println("IBAN :");
        String iban = scanner.nextLine();
        System.out.println("fond de depart :");
        float balance = scanner.nextFloat();
        scanner.nextLine();

        Account account = new Account(libelle, iban, balance);
        accountDAO = new AccountDAO(em);
        if (accountDAO.Add(account)) {
            System.out.println("compte ajouté");
        } else {
            System.out.println("erreure lors de l'ajout");
        }
    }

    public void addAgency() {
        System.out.println("-------- creation d'une agence -------");
        System.out.println("Adresse :");
        String adresse = scanner.nextLine();


        Agency agency = new Agency(adresse);
        agencyDAO = new AgencyDAO(em);
        if (agencyDAO.Add(agency)) {
            System.out.println("agence ajouté");
        } else {
            System.out.println("erreure lors de l'ajout");
        }
    }

    public void addCustomerToAccount() {
        System.out.println("----- ajout d'un utilisateur a un compte");
        System.out.println("id de l'utilisateur :");
        int idUser = scanner.nextInt();

        System.out.println("id du compte :");
        int idAccount = scanner.nextInt();
        scanner.nextLine();
        accountDAO = new AccountDAO(em);
        if (accountDAO.addCustomerToAccount(idUser, idAccount)) {
            System.out.println("utilisateur ajouter au comtpe");
        } else {
            System.out.println("erreure lors de l'ajout");
        }

    }

    public void addAgencyToAccount() {
        System.out.println("------- ajout d'une agence a un comtpe -------");
        System.out.println("id de l'agence :");
        int idAgency = scanner.nextInt();
        System.out.println("id du compte :");
        int idAccount = scanner.nextInt();
        scanner.nextLine();

        accountDAO = new AccountDAO(em);

        if(accountDAO.addAgencyToAccount(idAgency,idAccount)){
            System.out.println("agence ajouté au compte");
        }else{
            System.out.println("erreure lors de l'ajout");
        }
    }

    //fonction de supression
    public void deleteCustomer() {
        System.out.println("------- supression d'utilisateur ---------");
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        customerDAO = new CustomerDAO(em);

        if (customerDAO.delete(id)) {
            System.out.println("utilisateur supprimer");
        } else {
            System.out.println("erreure lors de la suppresion");
        }
    }

    public void deleteAccount() {
        System.out.println("------- supression de compte ---------");
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        accountDAO = new AccountDAO(em);

        if (accountDAO.delete(id)) {
            System.out.println("compte supprimer");
        } else {
            System.out.println("erreure lors de la suppresion");
        }
    }

    public void deleteAgency() {
        System.out.println("------- supression d'agence ---------");
        System.out.println("id :");
        int id = scanner.nextInt();
        scanner.nextLine();
        agencyDAO = new AgencyDAO(em);

        if (agencyDAO.delete(id)) {
            System.out.println("agence supprimer");
        } else {
            System.out.println("erreure lors de la suppresion");
        }
    }

    public void deleteCustomerToAccount (){
        System.out.println("----- suppression d'unutilisateur d'un compte -----");
        System.out.println("id de l'utilisateur");
        int idUser = scanner.nextInt();
        System.out.println("id du compte :");
        int idAccount= scanner.nextInt();
        scanner.nextLine();

        accountDAO = new AccountDAO(em);
        if(accountDAO.deleteCustomerToAccount(idUser,idAccount)){
            System.out.println("utilisateur suprimer");
        }else{
            System.out.println("erreure lors de la suppresion");
        }
    }
}
