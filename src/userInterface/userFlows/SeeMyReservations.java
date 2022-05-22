package userInterface.userFlows;

import api.HotelResource;
import model.Reservation;

import java.util.Collection;
import java.util.Scanner;

public class SeeMyReservations {
    public static void show(){
        HotelResource hotelResource = HotelResource.getInstance();
        String email3 = null;
        while (email3 == null) {
            try {
                System.out.println("Please enter your email");
                Scanner scanEmail = new Scanner(System.in);
                email3 = scanEmail.nextLine();
                Collection<Reservation> customerReservations = hotelResource.getCustomersReservation(email3);
                int newCount = 1;
                if (customerReservations.size() == 0) {
                    System.out.println("************ You have no active reservations ***********");
                    System.out.println();

                } else {
                    System.out.println(" Your Reservations ");
                    for (Reservation reservation : customerReservations) {
                        System.out.println(newCount + " " + reservation);
                    }
                    System.out.println();
                }
            } catch (IllegalArgumentException ex) {
                email3 = null;
            }

        }

    }
}
