package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

public class HotelResource {
    private static HotelResource hotelResource = null;
    private HotelResource(){};
    // static reference
    public static HotelResource getInstance(){
        if (hotelResource == null){
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        CustomerService customerService = CustomerService.getInstance();
        if (customer == null ) {
            Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$");
            if (!pattern.matcher(email).matches()) {
                throw new IllegalArgumentException();
            }else {
                customer = customerService.getCustomer(email);
            }

        }
        return customer;
    }

    public void createACustomer(String email, String firstName, String lastName){
        CustomerService customerService = CustomerService.getInstance();
        customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomId){
        IRoom room = null;
        ReservationService reservationService = ReservationService.getInstance();

        if (room == null){
             room = reservationService.getARoom(roomId);
        }
        return room;
    }

    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = null;
        ReservationService reservationService = ReservationService.getInstance();
        if (reservation == null){
             reservation = reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        }
        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(String email) {
        Collection<Reservation> reservations = null;
        Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$");

        ReservationService reservationService = ReservationService.getInstance();
        if (reservations == null) {
            if (pattern.matcher(email).matches()){
                reservations = reservationService.getCustomersReservation(email);
            }else {
                throw new IllegalArgumentException();
            }

        }
        return reservations;
    }
    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        Collection<IRoom> rooms = null;
        ReservationService reservationService = ReservationService.getInstance();
        if (rooms == null){
            rooms = reservationService.findRooms(checkInDate, checkOutDate);
        }
       return rooms;
    }
}
