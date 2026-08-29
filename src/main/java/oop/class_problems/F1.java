package oop.class_problems;

class BusTicket {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null ||
            passengerName.trim().isEmpty() ||
            !passengerName.matches("[A-Za-z]+")) {

            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (destination == null ||
            destination.trim().isEmpty()) {

            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    public void markCheckedIn() {

        if (!checkedIn) {
            checkedIn = true;
        }
    }

    public static void processBatch(String[][] rawBookings) {

        String[][] accepted = new String[rawBookings.length][2];
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length != 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket =
                        new BusTicket(booking[0], booking[1]);

                boolean duplicate = false;

                for (int i = 0; i < valid; i++) {

                    if (accepted[i][0].equals(ticket.passengerName)
                            && accepted[i][1].equals(ticket.destination)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    duplicates++;
                } else {
                    accepted[valid][0] = ticket.passengerName;
                    accepted[valid][1] = ticket.destination;
                    valid++;
                }

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println(
                "Valid: " + valid
                + " | Rejected: " + rejected
                + " | Duplicates skipped: " + duplicates
        );
    }
}

public class F1 {

    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}