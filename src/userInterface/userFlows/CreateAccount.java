package userInterface.userFlows;

import api.HotelResource;
import model.Customer;

import java.util.Scanner;

public class CreateAccount {
    public static void create(){
        HotelResource hotelResource = HotelResource.getInstance();
        String firstName2 = null, lastName2 = null, email2 = null;
        Customer existingUser = null;
        while (email2 == null) {
            try {
                if (email2 == null) {
                    System.out.println("please enter a valid email");
                    Scanner userEmail = new Scanner(System.in);
                    email2 = userEmail.next();
                    existingUser = hotelResource.getCustomer(email2);
                    if (existingUser != null) {
                        firstName2 = existingUser.getFirstName();
                        lastName2 = existingUser.getLastName();
                        System.out.println("Welcome back " + firstName2);
                        System.out.println();
                    }
                }
                if (firstName2 == null) {
                    System.out.println("Please enter your first name");
                    Scanner userFirstName = new Scanner(System.in);
                    firstName2 = userFirstName.next();
                }
                if (lastName2 == null) {
                    System.out.println("Please enter your last name");
                    Scanner userLastName = new Scanner(System.in);
                    lastName2 = userLastName.next();
                }
                if (existingUser == null) {
                    hotelResource.createACustomer(email2, firstName2, lastName2);
                    System.out.println("Sign up successful");
                }


            } catch (IllegalArgumentException ex) {
                email2 = null;
            }
        }
    }
}
