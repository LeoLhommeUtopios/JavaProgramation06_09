package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.entity.Customer;

@ApplicationScoped
public class CustomerRepository  implements PanacheRepository<Customer> {
}
