package model;

import java.util.HashSet;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("Faith", "Olubummo", "aaeon@gmail.com");
        Customer customer2 = new Customer("Tayo", "Jose", "aaeon@gmail.com");
        HashSet<Customer> customers = new HashSet<>();
        customers.add(customer);
        customers.add(customer2);
        System.out.println(customers);
    }
}
