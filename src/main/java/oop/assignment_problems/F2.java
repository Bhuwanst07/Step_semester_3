package oop.assignment_problems;

public class F2 {

    public static class RaceEntry {

        protected String bibNumber;
        protected double entryFee;
        protected double amountPaid;

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

            return "Race Entry | Bib: "
                    + bibNumber
                    + " | Balance: "
                    + getBalanceDue();
        }
    }

    public static class RunnerEntry
            extends RaceEntry {

        protected String category;

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

    public static class EliteRunnerEntry
            extends RunnerEntry {

        private double sponsorBonus;

        public EliteRunnerEntry(
                String bibNumber,
                double entryFee,
                String category,
                double sponsorBonus) {

            super(bibNumber, entryFee, category);
            this.sponsorBonus = sponsorBonus;
        }

        @Override
        public String announce() {

            return "Elite Runner | Bib: "
                    + bibNumber
                    + " | Category: "
                    + category
                    + " | Sponsor Bonus: "
                    + sponsorBonus
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
    }

    public static String classifyGeneration(
            RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        }

        if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        }

        if (entry instanceof RunnerEntry) {
            return "Single-inheritance descendant";
        }

        return "Base RaceEntry";
    }

    public static double getTotalBalanceDue(
            RaceEntry[] entries) {

        double total = 0.0;

        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        RaceEntry runnerEntry =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        EliteRunnerEntry eliteEntry =
                new EliteRunnerEntry(
                        "BIB3001",
                        150,
                        "Elite Full Marathon",
                        500
                );

        RelayTeamEntry relayEntry =
                new RelayTeamEntry(
                        "BIB4001",
                        300,
                        4
                );

        System.out.println(
                runnerEntry.announce()
        );

        System.out.println(
                eliteEntry.announce()
        );

        System.out.println(
                relayEntry.announce()
        );

        System.out.println(
                classifyGeneration(eliteEntry)
        );

        System.out.println(
                classifyGeneration(relayEntry)
        );

        RaceEntry[] entries = {
            runnerEntry,
            eliteEntry,
            relayEntry
        };

        System.out.println(
                getTotalBalanceDue(entries)
        );
    }
}