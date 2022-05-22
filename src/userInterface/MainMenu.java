package userInterface;


import userInterface.userFlows.CreateAccount;
import userInterface.userFlows.FindAndReserve;
import userInterface.userFlows.SeeMyReservations;

import java.util.*;


public class MainMenu {
    public static void showMainMenu(){
        boolean exit = false;
        System.out.println("====================================================================");
      System.out.println();
      System.out.println("                    Welcome To VABERA Hotel                               ");
      System.out.println();
        System.out.println("====================================================================");
        System.out.println("1. Find and reserve a room");
      System.out.println("2. See my reservations");
      System.out.println("3. Create an account");
      System.out.println("4. Admin menu");
      System.out.println("5. Exit. (exit the application)");
      System.out.println("0. Reload main menu");
        System.out.println("====================================================================");
        System.out.println();


            while (!exit){
                System.out.println("Please enter any number from 1 - 5 to make a new menu choice or '0' to reload Menu.");
              try {
                  Scanner scanner = new Scanner(System.in);
                  Integer navNumber = scanner.nextInt();
                      switch (navNumber) {
                          case 1:
                              FindAndReserve.findAndReserve();
                              break;
                          case 2:
                              SeeMyReservations.show();
                              break;
                          case 3:
                              CreateAccount.create();
                              break;
                          case 4:
                              exit = true;  // end loop before starting a new one
                              AdminMenu.showAdminMenu();
                              break;
                          case 5:
                              exit = true;
                              System.out.println("Exiting application ...");
                              System.out.println();
                              System.out.println();
                              System.out.println("Thank you for using our reservation app.");
                              break;
                          case 0:
                              exit = true;  // end loop before starting a new one
                              MainMenu.showMainMenu();
                              break;
                          default:
                              System.out.println();
                              break;
                      }


                  }catch(InputMismatchException ex){
                      System.out.println();


                  }

      }
       return;
    }
}
