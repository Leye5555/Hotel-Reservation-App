package userInterface.userFlows;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class FindAndReserve {
    public static void findAndReserve(){
        HotelResource hotelResource = HotelResource.getInstance();
        Date checkInDate = null, checkOutDate = null;
        while (checkInDate == null) {
            System.out.println("Please enter the check in date; format : dd/MM/yyyy ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            final Scanner sc = new Scanner(System.in);
            final String dateString = sc.nextLine();
            try {
                checkInDate = formatter.parse(dateString);
            } catch (ParseException e) {
                System.out.println();
            }
        }   // end of loop
        while (checkOutDate == null) {
            System.out.println("Please enter the check out date; format :  dd/MM/yyyy ");
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            final Scanner scan = new Scanner(System.in);
            final String dateString2 = scan.nextLine();
            try {
                checkOutDate = formatter2.parse(dateString2);
            } catch (ParseException ex) {
                System.out.println();
            }
        }   // end of loop

            Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                if (availableRooms.size() == 0) {
                   RecommendRooms.showRooms();
                } else {
                    int count = 1;
                    boolean undecided = true;
                    boolean notInterested = true;
                    Customer customer = null;
                    Customer existingUser = null;
                    String firstName = null, lastName = null, email = null;
                    IRoom room = null;
                    Reservation reservation = null;
                    System.out.println();
                    System.out.println("==========All Available Rooms ==========");
                    for (IRoom data : availableRooms) {
                        System.out.println();
                        System.out.println(count + ". " + data);
                        count++;
                    }
                    while (undecided && notInterested) {
                        System.out.println();
                        System.out.println("Would you like to book a room? Enter  y (yes) or n (no)");
                        Scanner scn = new Scanner(System.in);
                        String choice = scn.next();
                        if (choice.equalsIgnoreCase("y")) {
                            undecided = false;
                        } else if (choice.equalsIgnoreCase("n")) {
                            notInterested = false;
                        }
                    }  // end of loop


                        if (!undecided) {
                            while (reservation == null) {
                                if (room == null) {
                                    System.out.println("========================================");
                                    System.out.println();
                                    System.out.println("Enter the room number to choose a room");
                                    Scanner selectedRoom = new Scanner(System.in);
                                    room = hotelResource.getRoom(selectedRoom.next());
                                    if (room == null) {
                                        System.out.println("There is no available room with that ID.");
                                    }
                                } else {
                                    try {
                                        if (email == null) {
                                            System.out.println("please enter your email");
                                            Scanner userEmail = new Scanner(System.in);
                                            email = userEmail.next();
                                            // check if it is an existing user
                                            existingUser = hotelResource.getCustomer(email);
                                            if (existingUser != null) {
                                                firstName = existingUser.getFirstName();
                                                lastName = existingUser.getLastName();
                                                System.out.println("Welcome back " + firstName);
                                                System.out.println();
                                            }
                                        }
                                        if (firstName == null) {
                                            // if not an existing user
                                            System.out.println("Please enter your first name");
                                            Scanner userFirstName = new Scanner(System.in);
                                            firstName = userFirstName.next();
                                        }
                                        if (lastName == null) {
                                            System.out.println("Please enter your last name");
                                            Scanner userLastName = new Scanner(System.in);
                                            lastName = userLastName.next();
                                        }


                                        if (existingUser == null) { // prevent recreating the same customer twice
                                            hotelResource.createACustomer(email, firstName, lastName);
                                        }
                                        customer = hotelResource.getCustomer(email);
                                        reservation = hotelResource.bookARoom(customer, room, checkInDate, checkOutDate);
                                        if (reservation == null) {
                                            System.out.println("This is an existing reservation");
                                        } else {
                                            System.out.println("These are your reservation details");
                                            Collection<Reservation> allReservations = hotelResource.getCustomersReservation(email);
                                            int count2 = 1;
                                            for (Reservation eachReservation : allReservations) {
                                                System.out.println(count2 + ". " + eachReservation);
                                                count2++;
                                            }
                                        }

                                        System.out.println();


                                    } catch (IllegalArgumentException ex) {
                                        email = null;
                                    }
                                }

                            }  // end of loop
                        }



                }

    }
}
