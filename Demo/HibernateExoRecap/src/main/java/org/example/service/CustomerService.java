package org.example.service;

import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.example.utils.exception.NotFoundException;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public Customer create(String firstName,String lastName,String email){
        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();

        return customerRepository.create(customer);
    }

    public Customer update(int id,String firstName,String lastName,String email){

        Customer customer = customerRepository.findById(id);

        if (customer != null){
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            return customerRepository.update(customer);
        }
        throw new NotFoundException();
    }

    public boolean delete (int id){
        Customer customer = customerRepository.findById(id);
        if(customer != null){
            return customerRepository.delete(customer);
        }
        return false;
    }

    public Customer findById (int id){
        Customer customer = customerRepository.findById(id);
        if(customer != null){
            return customer;
        }
        throw new NotFoundException();
    }

    public List<Customer> findAll (){
        return customerRepository.findAll();
    }
}
