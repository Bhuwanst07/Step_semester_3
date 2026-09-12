package oop.class_problems;

class EventTicket {

    private static int ticketsIssued = 0;

    private final String ticketId;

    protected double basePrice;
    protected double amountPaid;

    static {
        ticketsIssued = 0;
    }

    public EventTicket(double basePrice) {

        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Base price must be positive"
            );
        }

        ticketsIssued++;

        this.ticketId =
                "TCK-"
                        + (1000 + ticketsIssued);

        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    void pay(double amount) {

        if (amount > 0) {
            amountPaid += amount;
        }
    }

    void pay(double amount, String mode) {

        System.out.println(
                "Payment mode: " + mode
        );

        pay(amount);
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    static boolean isValidPromoCode(
            String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i <= 3; i++) {

            if (!Character.isDigit(
                    code.charAt(i))) {
                return false;
            }
        }

        return Character.isUpperCase(
                code.charAt(4)
        );
    }

    static int getTicketsIssued() {
        return ticketsIssued;
    }

    String getTicketId() {
        return ticketId;
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(
            double basePrice,
            int groupSize) {

        super(basePrice);

        if (groupSize <= 0) {
            throw new IllegalArgumentException(
                    "Group size must be positive"
            );
        }

        this.groupSize = groupSize;
    }
}

public class F5 {

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        for (EventTicket ticket : tickets) {

            if (ticket == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (ticket instanceof GroupTicket) {
                groupCount++;
            } else {
                individualCount++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + groupCount
                + " group | "
                + individualCount
                + " individual";
    }

    public static void main(String[] args) {

        EventTicket t1 =
                new EventTicket(500);

        System.out.println(
                t1.getTicketId()
        );

        System.out.println(
                EventTicket.getTicketsIssued()
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "F123A"
                )
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "F12A"
                )
        );

        System.out.println(
                EventTicket.isValidPromoCode(
                        "X123A"
                )
        );

        t1.pay(200);
        t1.pay(200, "UPI");

        System.out.println(
                t1.getBalanceDue()
        );

        EventTicket[] tickets = {
            new GroupTicket(2000, 5),
            null,
            new EventTicket(500)
        };

        System.out.println(
                processNightlySettlement(tickets)
        );
    }
}