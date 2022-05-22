package userInterface;

import userInterface.adminFlows.AddRoom;
import userInterface.adminFlows.AllCustomers;
import userInterface.adminFlows.AllReservations;
import userInterface.adminFlows.AllRooms;

import java.util.InputMismatchException;

import java.util.Scanner;


public class AdminMenu {
   public static void showAdminMenu(){
       boolean exit = false;
       System.out.println("====================================================================");
       System.out.println();
       System.out.println("                      Admin Menu                               ");
       System.out.println();
       System.out.println("====================================================================");
       System.out.println("1. See all customers");
       System.out.println("2. See all rooms");
       System.out.println("3. See all reservations");
       System.out.println("4. Add a room");
       System.out.println("5. Test Data Menu");
       System.out.println("6. Back to Main Menu");
       System.out.println("0. Reload Admin Menu");
       System.out.println("====================================================================");
       System.out.println();


           while (!exit) {
               System.out.println("Please enter any number from 1 - 6 to make a new menu choice or '0' to reload Menu.");
               try {
                   Scanner scanner = new Scanner(System.in);
                   Integer navNumber = scanner.nextInt();
                   switch (navNumber) {
                       case 1:
                           AllCustomers.showCustomers();
                           break;
                       case 2:
                           AllRooms.showRooms();
                           break;
                       case 3:
                           AllReservations.showReservations();
                           break;
                       case 4:
                           AddRoom.add();
                           break;
                       case 5 :
                            exit = true; // end loop before starting a new one
                            TestDataMenu.showMenu();
                           break;
                       case 6:
                           exit = true;  // end loop before starting a new one
                           MainMenu.showMainMenu();
                           break;
                       case 0 :
                           exit = true;  // end loop before starting a new one
                           AdminMenu.showAdminMenu();
                       default:
                           System.out.println();
                           break;
                   }
               } catch (InputMismatchException ex) {
                   System.out.println();

               }
           }

   }
}
