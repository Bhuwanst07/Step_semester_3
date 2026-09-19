package oop.assignment_problems;

public class F4 {

    public static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

        public RaceEntry(
                String bibNumber,
                double entryFee) {

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

        public String announce() {

            return "Runner Entry | Bib: "
                    + bibNumber
                    + " | Balance: "
                    + getBalanceDue();
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

        @Override
        public String announce() {

            return "Runner Entry | Bib: "
                    + bibNumber
                    + " | Category: "
                    + category
                    + " | Balance: "
                    + getBalanceDue();
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

        @Override
        public String announce() {

            return "Relay Team | Bib: "
                    + bibNumber
                    + " | Team Size: "
                    + teamSize
                    + " | Balance: "
                    + getBalanceDue();
        }

        public int getTeamSize() {
            return teamSize;
        }
    }

    public static String announceAll(
            RaceEntry[] entries) {

        StringBuilder result =
                new StringBuilder();

        for (RaceEntry entry : entries) {

            result.append(
                    entry.announce()
            );

            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entry;

                result.append(
                        " [Team size via downcast: "
                                + relay.getTeamSize()
                                + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        RaceEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        90,
                        "Open 10K"
                );

        RaceEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        RaceEntry[] entries = {
            runnerEntry,
            relayEntry
        };

        System.out.println(
                announceAll(entries)
        );
    }
}