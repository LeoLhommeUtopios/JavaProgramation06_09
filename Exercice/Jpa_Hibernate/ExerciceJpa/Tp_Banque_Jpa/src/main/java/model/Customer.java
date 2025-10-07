package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer ;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;

    @ManyToMany(mappedBy = "customers",fetch = FetchType.EAGER)
    private List<Account> accounts;

    public Customer() {
    }

    public Customer(String lastName, String firstName, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void addAccount (Account account){
        this.accounts.add(account);
    }
    public void deleteAcount (Account account){
        this.accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
