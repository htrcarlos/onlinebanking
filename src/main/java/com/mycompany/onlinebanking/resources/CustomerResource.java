/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onlinebanking.resources;

import com.google.gson.Gson;
import com.mycompany.onlinebanking.model.Account;
import com.mycompany.onlinebanking.model.Customer;
import com.mycompany.onlinebanking.model.Transaction;
import com.mycompany.onlinebanking.services.CustomerService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Luciana
 */
@Path("/onlinebanking")
public class CustomerResource extends HttpServlet {

    CustomerService cs = new CustomerService();

    //curl -vi -X GET -G "http://localhost:49000/api/onlinebanking/createCustomers"
    @GET
    @Path("/createCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomers() {

        Gson gson = new Gson();
        List<Customer> customers = cs.createCustomers();
        System.out.println("Entrou create costumers " + customers.size());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(customers)).build();
    }

    //curl -v -X POST "http://localhost:49000/api/onlinebanking/createCustomer" -d {"name":"'Freddie Mercury'","address":"'35 Liffey Street South'","email":"'freddie@gmail.com'","credentials":123}
    @POST
    @Path("/createCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(String body) throws UnsupportedEncodingException, IOException {
        Gson gson = new Gson();
        Customer c = gson.fromJson(body, Customer.class);
        Customer customer = cs.createCustomer(c);
        System.out.println(cs.getList().size());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(customer)).build();
    }

    @POST
    @Path("{param}/createAccount")
    public Response createAccount(@PathParam("param") int sortCode, @QueryParam("email") String email) {
        Gson gson = new Gson();
        Account account = cs.createAccount(email, sortCode);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(account)).build();
    }

    @GET
    @Path("{sortCode}/{accountNumber}/newLodgement")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newLodgement(@PathParam("sortCode") int sortCode, @PathParam("accountNumber") int accountNumber, @QueryParam("value") double value) {
        Gson gson = new Gson();
        Transaction transaction = cs.newLodgement(sortCode, accountNumber, value);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(transaction)).build();
    }

    //curl -v -X POST http://localhost:49000/api/onlinebanking/101/111/newWithdrawal?value=25.0
    @GET
    @Path("{sortCode}/{accountNumber}/newWithdrawal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newWithdrawal(@PathParam("sortCode") int sortCode, @PathParam("accountNumber") int accountNumber, @QueryParam("value") double value) {
        Gson gson = new Gson();
        Transaction transaction = cs.newWithdrawal(sortCode, accountNumber, value);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(transaction)).build();
    }

    //curl -vi -X GET -G "http://localhost:49000/api/onlinebanking/101/111/getBalance"
    @GET
    @Path("{sortCode}/{accountNumber}/getBalance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@PathParam("sortCode") int sortCode, @PathParam("accountNumber") int accountNumber) {
        Gson gson = new Gson();
        Double currentBalance = cs.getBalance(sortCode, accountNumber);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(currentBalance)).build();
    }

    //curl -v -X POST "http://localhost:49000/api/onlinebanking/101/111/newTransfer?destinSortCode=102&destinAccountNumber=122&value=50.0"
    @GET
    @Path("{sortCode}/{accountNumber}/newTransfer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newTransfer(@PathParam("sortCode") int sortCode, @PathParam("accountNumber") int accountNumber, @QueryParam("destinSortCode") int destinSortCode, @QueryParam("destinAccountNumber") int destinAccountNumber, @QueryParam("value") double value) {
        Gson gson = new Gson();
        List<Transaction> transaction = cs.newTransfer(sortCode, accountNumber, destinSortCode, destinAccountNumber, value);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(transaction)).build();
    }

    @GET
    @Path("{email}/transactionHistory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transactionHistory(@PathParam("email") String email, @QueryParam("sortCode") int sortCode, @QueryParam("accountNumber") int accountNumber) {
        Gson gson = new Gson();
        List<Transaction> transactions = cs.transactionHistory(sortCode, accountNumber);

        return Response.status(Response.Status.CREATED).entity(gson.toJson(transactions)).build();
    }

    @POST
    @Path("/customerLogin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerLogin(@QueryParam("email") String email, @QueryParam("credentials") int credentials) {
       
        Gson gson = new Gson();
        Customer customer = cs.customerLogin(email, credentials);
        
        return Response.status(Response.Status.CREATED).entity(gson.toJson(gson.toJson(customer))).build();
    }

}
