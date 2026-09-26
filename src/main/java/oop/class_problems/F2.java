package oop.class_problems;

public class F2 {

    static abstract class Vehicle {

        protected String name;
        protected boolean available;

        Vehicle(String name) {
            this.name = name;
            this.available = true;
        }

        abstract double calculateCharge(int days);

        String getName() {
            return name;
        }

        boolean isAvailable() {
            return available;
        }

        void markRented() {
            available = false;
        }

        void markAvailable() {
            available = true;
        }
    }

    static class StandardCar extends Vehicle {

        StandardCar(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 50.0 * days;
        }
    }

    static class LuxuryCar extends Vehicle {

        LuxuryCar(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 100.0 * days;
        }
    }

    static class SUV extends Vehicle {

        SUV(String name) {
            super(name);
        }

        @Override
        double calculateCharge(int days) {
            return 80.0 * days;
        }
    }

    static class Customer {

        private String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Rental {

        private Customer customer;
        private Vehicle vehicle;
        private int days;
        private boolean active;

        Rental(
                Customer customer,
                Vehicle vehicle,
                int days) {

            this.customer = customer;
            this.vehicle = vehicle;
            this.days = days;
            this.active = true;
        }

        double getTotalCharge() {
            return vehicle.calculateCharge(days);
        }

        Vehicle getVehicle() {
            return vehicle;
        }

        boolean isActive() {
            return active;
        }

        void close() {
            active = false;
        }
    }

    static class RentalService {

        private java.util.ArrayList<Rental> rentals =
                new java.util.ArrayList<>();

        Rental rentVehicle(
                Customer customer,
                Vehicle vehicle,
                int days) {

            if (days <= 0) {
                System.out.println(
                        "Rental duration must be positive."
                );
                return null;
            }

            if (!vehicle.isAvailable()) {
                System.out.println(
                        vehicle.getName()
                                + " is already rented."
                );
                return null;
            }

            vehicle.markRented();

            Rental rental =
                    new Rental(
                            customer,
                            vehicle,
                            days
                    );

            rentals.add(rental);

            System.out.println(
                    vehicle.getName()
                            + " rented for "
                            + days
                            + " days."
            );

            System.out.printf(
                    "Total charge: $%.2f%n",
                    rental.getTotalCharge()
            );

            return rental;
        }

        void returnVehicle(Rental rental) {

            if (rental == null
                    || !rental.isActive()) {
                return;
            }

            rental.close();

            Vehicle vehicle =
                    rental.getVehicle();

            vehicle.markAvailable();

            System.out.println(
                    vehicle.getName()
                            + " returned. Now available."
            );
        }
    }

    public static void main(String[] args) {

        RentalService service =
                new RentalService();

        Customer customer =
                new Customer("John");

        Vehicle luxury =
                new LuxuryCar(
                        "Luxury Car A"
                );

        Vehicle standard =
                new StandardCar(
                        "Standard Car B"
                );

        // 1. Luxury Car for 3 days
        Rental r1 =
                service.rentVehicle(
                        customer,
                        luxury,
                        3
                );

        // 2. Standard Car for 5 days
        service.rentVehicle(
                customer,
                standard,
                5
        );

        // 3. Try renting already rented luxury car
        service.rentVehicle(
                customer,
                luxury,
                2
        );

        // 4. Return luxury car
        service.returnVehicle(r1);

        // 5. It is available again
        service.rentVehicle(
                customer,
                luxury,
                1
        );
    }
}