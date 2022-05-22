package userInterface;

import userInterface.testFlows.CreateAccount;
import userInterface.testFlows.FIndAndReserve;
import userInterface.testFlows.SeeMyReservations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestDataMenu {
    public static void showMenu() {
        boolean exitTestMenu = false;
            System.out.println("===========================================");
            System.out.println("                  Test Data Menu            ");
            System.out.println();
            System.out.println("1. Create an account");
            System.out.println("2. Find and reserve a room");
            System.out.println("3. See my reservations");
            System.out.println("4. Exit test menu");
            System.out.println("0. Reload test menu");
            System.out.println("===========================================");
        while (!exitTestMenu) {
            System.out.println("Please enter any number from 1 - 4 to make a choice or '0' to reload test menu");
            try {
                Scanner scanner = new Scanner(System.in);
                Integer navNumber = scanner.nextInt();
                switch (navNumber) {
                    case 1:
                        CreateAccount.create();
                        break;
                    case 2:
                        FIndAndReserve.findAndReserve();
                        break;
                    case 3:
                        SeeMyReservations.show();
                        break;
                    case 4:
                        exitTestMenu = true;  // end loop before starting a new one
                        AdminMenu.showAdminMenu();
                        break;
                    case 0:
                        exitTestMenu = true; // end loop before starting a new one
                        TestDataMenu.showMenu();
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
