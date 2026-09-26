package oop.class_problems;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class F3 {

    interface PricingStrategy {
        double calculatePrice(
                LocalDate start,
                LocalDate end);
    }

    static class StandardPricing
            implements PricingStrategy {

        private static final double RATE = 150.0;

        @Override
        public double calculatePrice(
                LocalDate start,
                LocalDate end) {

            long nights =
                    java.time.temporal.ChronoUnit.DAYS
                            .between(start, end);

            return nights * RATE;
        }
    }

    static class DeluxePricing
            implements PricingStrategy {

        private static final double RATE = 200.0;

        @Override
        public double calculatePrice(
                LocalDate start,
                LocalDate end) {

            long nights =
                    java.time.temporal.ChronoUnit.DAYS
                            .between(start, end);

            return nights * RATE;
        }
    }

    static class SuitePricing
            implements PricingStrategy {

        private static final double RATE = 300.0;

        @Override
        public double calculatePrice(
                LocalDate start,
                LocalDate end) {

            long nights =
                    java.time.temporal.ChronoUnit.DAYS
                            .between(start, end);

            return nights * RATE;
        }
    }

    static class Customer {

        private String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Room {

        private String roomNumber;
        private String category;
        private PricingStrategy pricingStrategy;

        public Room(
                String roomNumber,
                String category,
                PricingStrategy pricingStrategy) {

            this.roomNumber = roomNumber;
            this.category = category;
            this.pricingStrategy = pricingStrategy;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getCategory() {
            return category;
        }

        public double calculatePrice(
                LocalDate start,
                LocalDate end) {

            return pricingStrategy.calculatePrice(
                    start,
                    end
            );
        }
    }

    enum ReservationStatus {
        ACTIVE,
        CANCELLED
    }

    static class Reservation {

        private Room room;
        private Customer customer;
        private LocalDate startDate;
        private LocalDate endDate;
        private LocalDateTime cancellationDeadline;
        private ReservationStatus status;

        public Reservation(
                Room room,
                Customer customer,
                LocalDate startDate,
                LocalDate endDate,
                LocalDateTime cancellationDeadline) {

            this.room = room;
            this.customer = customer;
            this.startDate = startDate;
            this.endDate = endDate;
            this.cancellationDeadline =
                    cancellationDeadline;

            this.status =
                    ReservationStatus.ACTIVE;
        }

        public boolean isActive() {
            return status ==
                    ReservationStatus.ACTIVE;
        }

        public boolean overlaps(
                LocalDate start,
                LocalDate end) {

            // [start, end) date-range convention.
            return start.isBefore(endDate)
                    && end.isAfter(startDate);
        }

        public double getPrice() {
            return room.calculatePrice(
                    startDate,
                    endDate
            );
        }

        public void cancel(
                LocalDateTime cancellationTime) {

            if (!isActive()) {
                return;
            }

            if (cancellationTime.isBefore(
                    cancellationDeadline)
                    || cancellationTime.isEqual(
                    cancellationDeadline)) {

                status =
                        ReservationStatus.CANCELLED;

                System.out.println(
                        "Reservation for "
                                + room.getCategory()
                                + " Room "
                                + room.getRoomNumber()
                                + " cancelled successfully."
                );

            } else {

                System.out.println(
                        "Cancellation deadline has passed."
                );
            }
        }

        public Room getRoom() {
            return room;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }
    }

    static class BookingManager {

        private List<Room> rooms =
                new ArrayList<>();

        private List<Reservation> reservations =
                new ArrayList<>();

        public void addRoom(Room room) {
            rooms.add(room);
        }

        public Reservation book(
                Room room,
                Customer customer,
                LocalDate start,
                LocalDate end,
                LocalDateTime cancellationDeadline) {

            for (Reservation reservation :
                    reservations) {

                if (reservation.isActive()
                        && reservation.getRoom()
                            == room
                        && reservation.overlaps(
                                start,
                                end)) {

                    System.out.println(
                            "Booking failed: "
                                    + room.getCategory()
                                    + " Room "
                                    + room.getRoomNumber()
                                    + " is not available for "
                                    + start
                                    + " to "
                                    + end
                    );

                    return null;
                }
            }

            Reservation reservation =
                    new Reservation(
                            room,
                            customer,
                            start,
                            end,
                            cancellationDeadline
                    );

            reservations.add(reservation);

            System.out.println(
                    room.getCategory()
                            + " Room "
                            + room.getRoomNumber()
                            + " booked from "
                            + start
                            + " to "
                            + end
            );

            System.out.printf(
                    "Total price: $%.2f%n",
                    reservation.getPrice()
            );

            return reservation;
        }
    }

    public static void main(String[] args) {

        BookingManager manager =
                new BookingManager();

        Room deluxe =
                new Room(
                        "101",
                        "Deluxe",
                        new DeluxePricing()
                );

        Room standard =
                new Room(
                        "205",
                        "Standard",
                        new StandardPricing()
                );

        manager.addRoom(deluxe);
        manager.addRoom(standard);

        Customer customer =
                new Customer("John");

        LocalDateTime deadline =
                LocalDateTime.of(
                        2024,
                        11,
                        30,
                        23,
                        59
                );

        Reservation r1 =
                manager.book(
                        deluxe,
                        customer,
                        LocalDate.of(
                                2024, 12, 1
                        ),
                        LocalDate.of(
                                2024, 12, 5
                        ),
                        deadline
                );

        Reservation r2 =
                manager.book(
                        standard,
                        customer,
                        LocalDate.of(
                                2024, 12, 3
                        ),
                        LocalDate.of(
                                2024, 12, 7
                        ),
                        deadline
                );

        // Overlapping reservation: rejected.
        Reservation r3 =
                manager.book(
                        deluxe,
                        customer,
                        LocalDate.of(
                                2024, 12, 3
                        ),
                        LocalDate.of(
                                2024, 12, 7
                        ),
                        deadline
                );

        // Cancel before the deadline.
        if (r1 != null) {
            r1.cancel(
                    LocalDateTime.of(
                            2024,
                            11,
                            29,
                            12,
                            0
                    )
            );
        }

        // After cancellation, the room can be booked again.
        manager.book(
                deluxe,
                customer,
                LocalDate.of(
                        2024, 12, 3
                ),
                LocalDate.of(
                        2024, 12, 7
                ),
                deadline
        );
    }
}