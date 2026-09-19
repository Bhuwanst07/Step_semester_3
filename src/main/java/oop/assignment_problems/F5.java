package oop.assignment_problems;

public class F5 {

    public static class RaceEntry {

        private static int bibCounter = 0;

        private final String entryCode;

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        static {
            bibCounter = 0;
        }

        public RaceEntry(
                String bibNumber,
                double entryFee) {

            if (bibNumber == null
                    || bibNumber.trim().isEmpty()
                    || bibNumber.trim().length() < 4) {

                throw new IllegalArgumentException(
                        "Invalid bib number"
                );
            }

            if (entryFee <= 0) {
                throw new IllegalArgumentException(
                        "Entry fee must be positive"
                );
            }

            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
            this.amountPaid = 0.0;

            bibCounter++;

            this.entryCode =
                    "BIB-"
                    + (1000 + bibCounter);
        }

        public void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public void pay(
                double amount,
                String mode) {

            System.out.println(
                    "Paying via " + mode
            );

            pay(amount);
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public static boolean isValidDiscountCode(
                String code) {

            if (code == null
                    || code.length() != 5) {

                return false;
            }

            if (code.charAt(0) != 'M') {
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

        public static int getBibCounter() {
            return bibCounter;
        }

        public String getEntryCode() {
            return entryCode;
        }
    }

    public static class RunnerEntry
            extends RaceEntry {

        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    public static class RelayTeamEntry
            extends RaceEntry {

        private int teamSize;

        public RelayTeamEntry(
                String bibNumber,
                double entryFee,
                int teamSize) {

            super(bibNumber, entryFee);

            if (teamSize <= 0) {
                throw new IllegalArgumentException(
                        "Team size must be positive"
                );
            }

            this.teamSize = teamSize;
        }
    }

    public static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            } else {
                individual++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + relay
                + " relay | "
                + individual
                + " individual";
    }

    public static void main(String[] args) {

        RaceEntry t1 =
                new RaceEntry(
                        "BIB2001",
                        500
                );

        System.out.println(
                t1.getEntryCode()
        );

        RunnerEntry eliteEntry =
                new RunnerEntry(
                        "BIB3001",
                        1000,
                        "Elite"
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        2000,
                        5
                );

        RaceEntry individualEntry =
                new RaceEntry(
                        "BIB5001",
                        500
                );

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M123A"
                )
        );

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "M12A"
                )
        );

        System.out.println(
                RaceEntry.isValidDiscountCode(
                        "X123A"
                )
        );

        t1.pay(10, "UPI");

        System.out.println(
                t1.getBalanceDue()
        );

        RaceEntry[] entries = {
            eliteEntry,
            null,
            relayEntry
        };

        System.out.println(
                settleNight(entries)
        );

        System.out.println(
                RaceEntry.getBibCounter()
        );
    }
}