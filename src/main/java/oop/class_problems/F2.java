package oop.class_problems;

import java.util.*;

public class F2 {

    static class Customer {

        int id;
        String name;

        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Customer)) {
                return false;
            }

            Customer other = (Customer) obj;

            return id == other.id &&
                    Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {

        Set<Customer> customers = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(customers.add(c1));

        boolean secondAdded = customers.add(c2);

        System.out.println(
                secondAdded
                        + (secondAdded ? "" : " (duplicate rejected)")
        );

        System.out.println(customers.add(c3));

        System.out.println("unique count " + customers.size());

        System.out.println(
                "contains: " + customers.contains(
                        new Customer(101, "Asha")
                )
        );
    }
}