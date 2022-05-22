package userInterface.adminFlows;

import api.AdminResource;
import model.Customer;

import java.util.Collection;

public class AllCustomers {

    public static void showCustomers(){
        AdminResource adminResource = AdminResource.getInstance();
        Collection<Customer> allCustomers = adminResource.getAllCustomers();
        int count = 1;
        if (allCustomers.size() == 0) {
            System.out.println("Vabera hotel currently has no customers");
            System.out.println();

        }else{
            System.out.println("============== All Customers ===================");
            for ( Customer eachCustomer : allCustomers) {
                System.out.println();
                System.out.println(count + ". " + eachCustomer);
                System.out.println();
                count++;
            }
            System.out.println();
        }
    }
}
