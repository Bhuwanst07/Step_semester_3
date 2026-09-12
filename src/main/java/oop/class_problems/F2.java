package oop.class_problems;

class EventTicket {

    protected String attendeeId;
    protected double basePrice;
    protected double amountPaid;

    public EventTicket(
            String attendeeId,
            double basePrice) {

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

    void printTicket() {
        System.out.println(
                "Standard Event Ticket | Balance Due: "
                        + getBalanceDue()
        );
    }
}

class WorkshopTicket extends EventTicket {

    protected String track;

    public WorkshopTicket(
            String attendeeId,
            double basePrice,
            String track) {

        super(attendeeId, basePrice);
        this.track = track;
    }

    @Override
    void printTicket() {
        System.out.println(
                "Workshop Ticket | Track: "
                        + track
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

class PremiumWorkshopTicket
        extends WorkshopTicket {

    private double kitFee;

    public PremiumWorkshopTicket(
            String attendeeId,
            double basePrice,
            String track,
            double kitFee) {

        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    @Override
    double getBalanceDue() {
        return basePrice + kitFee - amountPaid;
    }

    @Override
    void printTicket() {
        System.out.println(
                "Premium Workshop Ticket | Track: "
                        + track
                        + " | Kit Fee: "
                        + kitFee
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

class HackathonTicket extends EventTicket {

    private String teamName;

    public HackathonTicket(
            String attendeeId,
            double basePrice,
            String teamName) {

        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    @Override
    void printTicket() {
        System.out.println(
                "Hackathon Ticket | Team: "
                        + teamName
                        + " | Balance Due: "
                        + getBalanceDue()
        );
    }
}

public class F2 {

    static String classifyGeneration(
            EventTicket ticket) {

        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        }

        if (ticket instanceof WorkshopTicket) {
            return "Single-inheritance descendant";
        }

        return "Base EventTicket";
    }

    static double getTotalBalanceDue(
            EventTicket[] tickets) {

        double total = 0.0;

        for (EventTicket ticket : tickets) {
            total += ticket.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        EventTicket standard =
                new EventTicket(
                        "STU1",
                        500
                );

        WorkshopTicket workshop =
                new WorkshopTicket(
                        "STU2",
                        1200,
                        "AI/ML"
                );

        PremiumWorkshopTicket premium =
                new PremiumWorkshopTicket(
                        "STU3",
                        2000,
                        "Cloud Native",
                        300
                );

        HackathonTicket hackathon =
                new HackathonTicket(
                        "STU4",
                        800,
                        "Byte Force"
                );

        standard.printTicket();
        workshop.printTicket();
        premium.printTicket();
        hackathon.printTicket();

        System.out.println(
                classifyGeneration(premium)
        );

        System.out.println(
                classifyGeneration(hackathon)
        );

        EventTicket[] tickets = {
            standard,
            workshop,
            premium,
            hackathon
        };

        System.out.println(
                getTotalBalanceDue(tickets)
        );
    }
}