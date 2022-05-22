package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    // static reference variable
    private static ReservationService reservationService = null;

    // private constructor
    private ReservationService(){};


    // static instance creator
    public static ReservationService getInstance() {
        if ( reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }


    List<IRoom> allRooms = new ArrayList<IRoom>();
    Set<IRoom> remainingRooms = new HashSet<>();
    List<Reservation> allReservations = new ArrayList<>();

    public void addRoom(IRoom room) {
        Boolean roomExists = false;
        for (IRoom data : allRooms){
            if (data.getRoomNumber().equals(room.getRoomNumber())){
                System.out.println();
                System.out.println("A room with this number already exists");
                roomExists = true;
            }
        }
        if (!roomExists){
            Room newRoom = new Room(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType(), room.isFree());
            allRooms.add(newRoom);
            remainingRooms.add(newRoom);
            System.out.println(newRoom);
            System.out.println();
            System.out.println("Room added successfully");
        }

    }

    Collection<IRoom> getFreeRooms(){     // method with default access modifier
        return  remainingRooms;
    }

    public IRoom getARoom(String roomId) {
        IRoom room = null;
        for (IRoom data: remainingRooms
             ) {
             if(data.getRoomNumber().equals(roomId)) {
                 room = data;
             }
        }
        return room;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.add(newReservation);
        for (IRoom y : allRooms) {
            if (y.getRoomNumber().equals(room.getRoomNumber()))
                remainingRooms.remove(y);
        }
        return newReservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        if (allReservations.size() == 0) {
            return allRooms;
        } else {

            Set<Reservation> freeRooms =  new HashSet<>();
            Set<Reservation> takenRooms = new HashSet<>();
            for ( Reservation x : allReservations
                 ) {
                // ..... <  x  < .....              x stands for the reservation period
                if (x.getCheckInDate().getTime() >= checkInDate.getTime() && x.getCheckOutDate().getTime() <= checkOutDate.getTime()){
                    takenRooms.add(x);
                }
                // x < ...    ||   x > .....          x stands for the reservation period
                if ( x.getCheckOutDate().getTime() < checkInDate.getTime() || x.getCheckInDate().getTime() > checkOutDate.getTime() ){
                            freeRooms.add(x);
                }
            }

            for (Reservation q : takenRooms
                 ) {
               if (freeRooms.contains(q)) {
                   freeRooms.remove(q);
               }
            }





            for (Reservation z : freeRooms
                 ) {
                remainingRooms.add(z.getRoom());
            }

          return remainingRooms;
        }

    }

    public Collection<Reservation> getCustomersReservation(String email) {
        Collection<Reservation> customerReservation = null;
        customerReservation = allReservations.stream().filter(reserved -> reserved.getCustomer().getEmail().equals(email)).collect(Collectors.toList());
       System.out.println("");
        return customerReservation;
    }

    public Collection<IRoom> getAllRooms() {
        return allRooms;
    }

    public void printAllReservations() {
        int count = 1;
        if (allReservations.size() == 0) {
           System.out.println("There are no active reservations");
        }
        else {
            System.out.println("=====================All Reservations===============");
            System.out.println();
            for (Reservation data : allReservations) {
                System.out.println(count + ". " + data);
                count++;
            }
       }
    }
}
