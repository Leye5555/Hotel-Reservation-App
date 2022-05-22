package userInterface.adminFlows;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddRoom {
    public static void add(){
        AdminResource adminResource = AdminResource.getInstance();
        String roomNumber = null;
        Double roomPrice = null;
        RoomType roomType  = null;
        Boolean isFree = null;
        while (roomNumber == null) {
            System.out.println("Enter room number");
            try {
                Scanner sc1 = new Scanner(System.in);
                roomNumber = sc1.next();
            } catch (InputMismatchException ex) {
                System.out.println();
            }
        }
        while (roomPrice == null) {
            System.out.println("Enter the room price");
            try {
                Scanner sc2 = new Scanner(System.in);
                roomPrice = sc2.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println();
            }
        }
        while (roomType == null) {
            System.out.println("Enter the room type : 's' for single bed or 'd' for double bed");
            try {
                Scanner sc3 = new Scanner(System.in);
                switch(sc3.next().toUpperCase()){
                    case "S" :
                        roomType = RoomType.SINGLE;
                        break;
                    case "D" :
                        roomType = RoomType.DOUBLE;
                        break;
                    default:
                        System.out.println();
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println();
            }
        }
        while (isFree == null && roomType != null) {
            System.out.println("Is this a free room? enter a 'y' (for free) or 'n' (for paid).");
            try {
                Scanner sc4 = new Scanner(System.in);
                switch(sc4.next().toLowerCase()) {
                    case "y" :
                        isFree = true;
                        break;
                    case "n" :
                        isFree = false;
                        break;
                    default:
                        System.out.println();
                        break;
                }
                if (roomNumber != null && roomPrice != null && roomType != null && isFree != null) {
                    IRoom newRoom = new Room(roomNumber, roomPrice, roomType, isFree);
                    adminResource.addRoom(newRoom);
                    System.out.println();
                    System.out.println();
                }
            } catch (InputMismatchException ex) {
                System.out.println();
            }
        }
    }
}
