package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        super();
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public IRoom getRoom(){
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override()
    public String toString() {
       return "Reservation Details : \n"
               +  "** " + customer + "\n"
               + "** " + room + "\n"
               + "** " + checkInDate + "\n"
               +  "** " + checkOutDate + "\n";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Reservation)) return false;
        Reservation reservation = (Reservation) obj;
        if (getRoom().getRoomNumber().equals(reservation.getRoom().getRoomNumber()))
            return true;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoom().getRoomNumber());
    }
}