package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.*;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerResponse save (CustomerRequest customerRequest){
        Customer customer = requestToCustomer(customerRequest);
        repository.persist(customer);
        return customerToResponse(customer);
    }

    public ListCustomer getAll (){
       List<Customer> customers = repository.findAll().stream().toList();
//        List<Customer> customers = new ArrayList<>();
//        customers.add(new Customer(1,"toto","Tata",LocalDate.now()));
//        List<CustomerResponse> customerResponses = new ArrayList<>();
//        for(Customer customer : customers){
//            customerResponses.add(customerToResponse(customer));
//        }
//        return ListCustomer.newBuilder().addAllListofCustomer(customerResponses).build();
        return customers;
    }

    public CustomerResponse getById (CustomerId customerId){
//        Customer customer = repository.findById(customerId.getId());
        return customerToResponse(new Customer(1,"toto","Tata",LocalDate.now()));
    }

    public DeletionMessage delete (CustomerId customerId){
//        boolean deletion = repository.deleteById(customerId.getId());
        return DeletionMessage.newBuilder().setDeletion(true).build();
    }

    private Customer requestToCustomer (CustomerRequest customerRequest){
        return new Customer(customerRequest.getFirstname(),customerRequest.getLastname(), LocalDate.ofEpochDay(customerRequest.getBirthDateTimeStamp()));
    }

    private CustomerResponse customerToResponse (Customer customer){
        return CustomerResponse.newBuilder()
                .setId(customer.getId())
                .setFirstname(customer.getFirstname())
                .setLastname(customer.getLastname())
                .setBirthDateTimeStamp(customer.getBirthDate().toEpochDay())
                .build();
    }
}
