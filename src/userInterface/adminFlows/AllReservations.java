package userInterface.adminFlows;
import api.AdminResource;

public class AllReservations {
    public static void showReservations(){
        AdminResource adminResource = AdminResource.getInstance();
        adminResource.displayAllReservations();
        System.out.println("=================================================");
        System.out.println();
    }
}
