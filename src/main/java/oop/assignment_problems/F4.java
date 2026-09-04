package oop.assignment_problems;

final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(
            double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum surge percent cannot be negative"
            );
        }

        this.minimumSurgePercent =
                minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Order value and delay cannot be negative"
            );
        }

        // On-time orders never pay the surge floor.
        if (delayMinutes == 0) {
            return 0.0;
        }

        double surge = 0.0;

        // Minutes 1-5: 0.5% per minute.
        int firstTier =
                Math.min(delayMinutes, 5);

        surge += orderValue * 0.005 * firstTier;

        // Minutes 6-15: 1% per minute.
        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes - 5, 10);

            surge += orderValue * 0.01 * secondTier;
        }

        // Minute 16 onward: 2% per minute.
        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            surge += orderValue * 0.02 * thirdTier;
        }

        double minimumSurge =
                orderValue *
                (minimumSurgePercent / 100.0);

        return Math.max(surge, minimumSurge);
    }
}

public class F4 {

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1.0);

        System.out.println(
                "Rs " +
                calculator.calculateSurgeFee(500, 0)
        );

        System.out.println(
                "Rs " +
                calculator.calculateSurgeFee(500, 1)
        );

        System.out.println(
                "Rs " +
                calculator.calculateSurgeFee(500, 16)
        );
    }
}