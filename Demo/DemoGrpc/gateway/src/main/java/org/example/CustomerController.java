package org.example;

import com.google.protobuf.Empty;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.annotations.Pos;

@Path("/api/customer")
public class CustomerController {

    @GrpcClient
    CustomerGrpc customerGrpc;

    @GET
    public Uni<Response> getAll(){
        return customerGrpc.getAllCustomer(Empty.newBuilder().build())
                .onItem().transform((ListCustomer c)-> Response.ok(c).build());
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById (@PathParam("id") long id){
        return customerGrpc.getCustomerById(CustomerId.newBuilder().setId(id).build())
                .onItem().transform((CustomerResponse c)-> Response.ok(c).build());
    }

    @POST
    public Uni<Response> addCustomer (CustomerRequest customerRequest){
        return customerGrpc.postCustomer(customerRequest)
                .onItem().transform((CustomerResponse c )-> Response.ok(c).build());
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteCustomer (@PathParam("id") long id){
        return customerGrpc.deleteCustomer(CustomerId.newBuilder().setId(id).build())
                .onItem().transform((DeletionMessage d)->{
                    if(d.getDeletion()){
                        return Response.ok("Customer delete").build();
                    }else{
                        return Response.serverError().build();
                    }
                });
    }

}
