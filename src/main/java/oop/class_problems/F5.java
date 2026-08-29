package oop.class_problems;

class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;
    protected double paidAmount;

    static {
        System.out.println("Fleet reconciliation initialized.");
    }

    public BusTicketAccount(
            String bookingId,
            double ticketFare) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException(
                    "Ticket fare cannot be negative"
            );
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        this.paidAmount = 0;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Minutes late cannot be negative"
            );
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int first = Math.min(minutesLate, 5);
        penalty += ticketFare * 0.005 * first;

        if (minutesLate > 5) {
            int second = Math.min(minutesLate - 5, 10);
            penalty += ticketFare * 0.01 * second;
        }

        if (minutesLate > 15) {
            int third = minutesLate - 15;
            penalty += ticketFare * 0.02 * third;
        }

        return penalty;
    }

    public void pay(double amount) {

        if (amount > 0) {
            paidAmount += amount;
        }
    }
}

class SleeperAccount extends BusTicketAccount {

    public SleeperAccount(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }

    public double sleeperAdjustment(double amount) {
        return amount * 0.95;
    }
}

public class F5 {

    static double grandTotalPenalty = 0;
    static int processed = 0;
    static int nullSkipped = 0;
    static int sleeperCount = 0;
    static int regularCount = 0;

    static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null) {
            nullSkipped++;
            return;
        }

        if (amount < 0 || minutesLate < 0) {
            return;
        }

        account.pay(amount);

        double penalty =
                account.calculatePenalty(minutesLate);

        if (account instanceof SleeperAccount) {

            SleeperAccount sleeper =
                    (SleeperAccount) account;

            amount = sleeper.sleeperAdjustment(amount);
            sleeperCount++;

        } else {

            regularCount++;
        }

        grandTotalPenalty += penalty;
        processed++;
    }

    static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        int length = Math.min(
                accounts.length,
                Math.min(
                        amounts.length,
                        minutesLateArray.length
                )
        );

        for (int i = 0; i < length; i++) {

            processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
            );
        }
    }

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200, 900, 700
        };

        int[] minutesLate = {
            10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                minutesLate
        );

        System.out.println(
                processed + " processed | "
                + nullSkipped + " null skipped | "
                + sleeperCount + " sleeper | "
                + regularCount + " regular | "
                + "grand total penalties = "
                + grandTotalPenalty
        );
    }
}