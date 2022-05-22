package userInterface.adminFlows;

import api.AdminResource;
import model.IRoom;

import java.util.Collection;

public class AllRooms {
    public static void showRooms(){
        AdminResource adminResource = AdminResource.getInstance();
        if (adminResource.getAllRooms().size() == 0) {
            System.out.println();
            System.out.println("There are no rooms available");
            System.out.println();
        }else {
            Collection<IRoom> allRooms = adminResource.getAllRooms();
            System.out.println("==========All Hotel Rooms ==========");
            int count2 = 1;
            for (IRoom room : allRooms){
                System.out.println();
                System.out.println( count2 + ". " + room);
                count2++;
            }

        }
        System.out.println();
    }
}
