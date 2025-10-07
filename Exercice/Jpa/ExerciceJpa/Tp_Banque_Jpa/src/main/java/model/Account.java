package model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;
    @NotNull
    private String libelle;
    @NotNull
    @Column(length = 27)
    private String iban;
    private float balance;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "Account_Customer",joinColumns = @JoinColumn(name = "idAccount"),
            inverseJoinColumns = @JoinColumn(name = "idCustomer"))
    private List<Customer> customers;

    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "idAgency")
    private Agency agency;

    public Account() {
    }

    public Account(String libelle, String iban, float balance) {
        this.libelle = libelle;
        this.iban = iban;
        this.balance = balance;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public void addCustomer (Customer customer){
        this.customers.add(customer);
    }
    public void deleteCustomer (Customer customer){
        this.customers.remove(customer);
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAccount=" + idAccount +
                ", libelle='" + libelle + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", agency=" + agency +
                '}';
    }
}
