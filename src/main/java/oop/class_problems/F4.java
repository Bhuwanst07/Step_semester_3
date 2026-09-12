package oop.class_problems;

class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    public EventTicket(double basePrice) {

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

    String printTicket() {
        return "Standard | Balance: "
                + getBalanceDue();
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(
            double basePrice,
            String track) {

        super(basePrice);
        this.track = track;
    }

    @Override
    String printTicket() {

        return "Workshop | Track: "
                + track
                + " | Balance: "
                + getBalanceDue();
    }

    String getTrack() {
        return track;
    }
}

public class F4 {

    static String batchPrint(
            EventTicket[] tickets) {

        StringBuilder report =
                new StringBuilder();

        for (EventTicket ticket : tickets) {

            report.append(
                    ticket.printTicket()
            );

            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) ticket;

                report.append(
                        " [Track via downcast: "
                                + workshop.getTrack()
                                + "]"
                );
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        EventTicket[] tickets = {
            new EventTicket(500),
            new WorkshopTicket(
                    1200,
                    "AI/ML"
            )
        };

        System.out.println(
                batchPrint(tickets)
        );
    }
}