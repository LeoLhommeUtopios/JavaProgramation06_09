package org.example.controller;

import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.example.*;
import org.example.service.CustomerService;

@GrpcService
public class GrpcCustomerService implements CustomerGrpc {

    private final CustomerService service;

    public GrpcCustomerService(CustomerService service) {
        this.service = service;
    }

    @Override
    public Uni<CustomerResponse> postCustomer(CustomerRequest request) {
        CustomerResponse response = service.save(request);
        return Uni.createFrom().item(()->response);
    }

    @Override
    public Uni<ListCustomer> getAllCustomer(Empty request) {
        ListCustomer listCustomer = service.getAll();
        return Uni.createFrom().item(()->listCustomer);
    }

    @Override
    public Uni<CustomerResponse> getCustomerById(CustomerId request) {
        CustomerResponse customerResponse = service.getById(request);
        return Uni.createFrom().item(()->customerResponse);
    }

    @Override
    public Uni<DeletionMessage> deleteCustomer(CustomerId request) {
        DeletionMessage deletionMessage = service.delete(request);
        return Uni.createFrom().item(()->deletionMessage);
    }
}
