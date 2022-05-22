package service;

import model.Customer;
import model.IRoom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class CustomerService {
    // private static variable
    private static CustomerService customerService = null;

    HashSet<Customer> allCustomers = new HashSet<>();

    // constructor
    private CustomerService(){}

    //static method for getting class instance

    public static CustomerService getInstance() {
        if (customerService == null ) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    // add customer to collection
    public void addCustomer(String email, String firstName, String lastName){
       Customer customer =  new Customer(firstName, lastName, email);
       allCustomers.add(customer);
    }
    public Customer getCustomer(String email) {
         Customer customer = null;
        for (Customer data : allCustomers
             ) {
             if (data.getEmail().equals(email)){
                 customer = data;
             }
        }
        return customer;
    }

    public Collection<Customer> getAllCustomers(){
        return allCustomers;
    }

    public Collection<IRoom> getFreeRooms(){
        return ReservationService.getInstance().getFreeRooms();
    }

}
