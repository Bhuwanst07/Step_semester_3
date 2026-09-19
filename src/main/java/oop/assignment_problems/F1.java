package oop.assignment_problems;

public class F1 {

    public static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(String bibNumber, double entryFee) {

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
        }

        public void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {
            return entryFee - amountPaid;
        }

        public static String registerBatch(
                String[] bibNumbers,
                double entryFee) {

            int registered = 0;
            int rejected = 0;

            for (String bibNumber : bibNumbers) {

                try {
                    new RaceEntry(bibNumber, entryFee);
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

    public static class RunnerEntry extends RaceEntry {

        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(bibNumber, entryFee);
            this.category = category;
        }
    }

    public static void main(String[] args) {

        try {
            new RaceEntry("B1", 50);
            System.out.println("construction accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        System.out.println(
                r.getBalanceDue()
        );

        String[] bibNumbers = {
            "BIB1",
            "B1",
            "BIB2",
            " ",
            "BIB3"
        };

        System.out.println(
                RaceEntry.registerBatch(
                        bibNumbers,
                        80
                )
        );
    }
}