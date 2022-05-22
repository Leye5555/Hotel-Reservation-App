package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;

public class AdminResource {
    private static AdminResource adminResource = null;
    private AdminResource(){};
    // static method for getting instance
    public static AdminResource getInstance() {
        if (adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        CustomerService customerService = CustomerService.getInstance();
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public void addRoom(IRoom room) {
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.addRoom(room);

    }

    public Collection<IRoom> getAllRooms(){
        ReservationService reservationService = ReservationService.getInstance();
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers(){
        CustomerService customerService = CustomerService.getInstance();
        return customerService.getAllCustomers();
    }
    public void displayAllReservations(){
        ReservationService reservationService = ReservationService.getInstance();
        reservationService.printAllReservations();
    }
}
