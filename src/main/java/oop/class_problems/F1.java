package oop.class_problems;

class EventTicket {

    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(String attendeeId, double basePrice) {

        if (attendeeId == null
                || attendeeId.trim().isEmpty()
                || attendeeId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid attendee ID"
            );
        }

        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Base price must be positive"
            );
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    void pay(double amount) {

        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    static String registerBatch(
            String[] attendeeIds,
            double basePrice) {

        int registered = 0;
        int rejected = 0;

        for (String attendeeId : attendeeIds) {

            try {
                new EventTicket(attendeeId, basePrice);
                registered++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: "
                + registered
                + " | Rejected: "
                + rejected;
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(
            String attendeeId,
            double basePrice,
            String track) {

        super(attendeeId, basePrice);
        this.track = track;
    }

    public void printTicket() {
        System.out.println(
                "Workshop Ticket | Track: "
                        + track
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

public class F1 {

    public static void main(String[] args) {

        try {
            new EventTicket("ST1", 500);
            System.out.println("construction accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        WorkshopTicket w =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        w.pay(500);

        System.out.println(
                w.getBalanceDue()
        );

        String[] ids = {
            "STU1",
            "ST1",
            "STU2",
            " ",
            "STU3"
        };

        System.out.println(
                EventTicket.registerBatch(ids, 500)
        );
    }
}