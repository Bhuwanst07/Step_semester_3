package oop.assignment_problems;

import java.util.Arrays;

public class F3 {

    public static class RaceEntry {

        protected double entryFee;
        protected double amountPaid;

        private double[] lateFeeHistory;
        private int historyCount;

        public RaceEntry(
                String bibNumber,
                double entryFee,
                String category) {

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

            this.entryFee = entryFee;
            this.amountPaid = 0.0;

            this.lateFeeHistory = new double[10];
            this.historyCount = 0;
        }

        public void pay(double amount) {

            if (amount > 0) {
                amountPaid += amount;
            }
        }

        public double getBalanceDue() {

            double totalLateFee = 0.0;

            for (int i = 0; i < historyCount; i++) {
                totalLateFee += lateFeeHistory[i];
            }

            return entryFee
                    - amountPaid
                    + totalLateFee;
        }

        protected void applyLateFee(double amount) {

            if (amount <= 0) {
                return;
            }

            if (historyCount < lateFeeHistory.length) {

                lateFeeHistory[historyCount] =
                        amount;

                historyCount++;
            }
        }

        public double[] getLateFeeHistory() {

            return Arrays.copyOf(
                    lateFeeHistory,
                    historyCount
            );
        }
    }

    public static class RunnerEntry
            extends RaceEntry {

        private String category;

        public RunnerEntry(
                String bibNumber,
                double entryFee,
                String category) {

            super(
                    bibNumber,
                    entryFee,
                    category
            );

            this.category = category;
        }

        @Override
        protected void applyLateFee(double amount) {

            super.applyLateFee(
                    amount * 2
            );
        }
    }

    public static void main(String[] args) {

        RunnerEntry r =
                new RunnerEntry(
                        "BIB2001",
                        80,
                        "Open 10K"
                );

        r.pay(30);

        r.applyLateFee(20);

        System.out.println(
                r.getBalanceDue()
        );

        double[] history =
                r.getLateFeeHistory();

        System.out.println(
                Arrays.toString(history)
        );

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        r.getLateFeeHistory()
                )
        );
    }
}