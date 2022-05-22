package userInterface.userFlows;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class RecommendRooms {
     public static void showRooms() {
         HotelResource hotelResource = HotelResource.getInstance();
         boolean yes = true;
         boolean no = true;
         while (yes && no) {
             System.out.println("There are no rooms available for that date");
             System.out.println();
             System.out.println("Would you like us to make recommendations for you instead? Enter 'y' (for yes) or 'n' (for no)");
             Scanner scanner = new Scanner(System.in);
             String choice = scanner.next();
             if (choice.equalsIgnoreCase("y")) {
                 no = false;
             } else if (choice.equalsIgnoreCase("n")) {
                 yes = false;
             }
         }  // end of loop

         if (!no){
             boolean noDay = true;
             boolean noDuration = true;
             Date checkInDate = null;
             Date checkOutDate = null;
             while(noDay) {
                 System.out.println("Please enter how many days from now you would like us to check for recommendations. Hint 1, 2, 3 ... (default is 7 days)");
                 try {
                     Scanner scn1 = new Scanner(System.in);
                     Integer days1 = scn1.nextInt();
                     long milli1 = days1 * 24 * 60 * 60 * 1000;
                     long now1 = System.currentTimeMillis();
                     long sumOfMillis1 = milli1 + now1;
                     Calendar calendar1 = Calendar.getInstance();
                     Calendar calendar2 = Calendar.getInstance();
                     calendar1.setTimeInMillis(sumOfMillis1);
                     while(noDuration) {
                         System.out.println("How many days will you be staying for? Hint 1, 2, 3 ... ");
                         try{
                             Scanner scn2 = new Scanner(System.in);
                             Integer days2 = scn2.nextInt();
                             long milli2 = days2 * 24 * 60 * 60 * 1000;
                             long now2 = System.currentTimeMillis();
                             long sumOfMillis2 = milli1 + milli2 + now2;
                             calendar2.setTimeInMillis(sumOfMillis2);
                             noDuration = false;
                         }catch(InputMismatchException ex){
                             System.out.println();
                         }
                     }
                     checkInDate = calendar1.getTime();
                     checkOutDate = calendar2.getTime();
                     noDay = false;
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
                         System.out.println("**** Recommendations for " + days1 + " day(s) from now ****");
                         for (IRoom data : availableRooms) {
                             System.out.println();
                             System.out.println(count + ". " + data);
                             count++;
                         }
                         while (undecided && notInterested) {
                             System.out.println();
                             System.out.println("Would you like to book a room? Enter  y (yes) or n (no)");
                             Scanner scn3 = new Scanner(System.in);
                             String choice = scn3.next();
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

                 } catch (InputMismatchException ex) {
                     System.out.println();
                 }
             }
         }


     }
}
