package oop.class_problems;

final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(
            double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum penalty cannot be negative"
            );
        }

        this.minimumPenaltyPercent =
                minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Fare and minutes late cannot be negative"
            );
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penalty = 0.0;

        int firstTier = Math.min(minutesLate, 5);
        penalty += ticketFare * 0.005 * firstTier;

        if (minutesLate > 5) {
            int secondTier =
                    Math.min(minutesLate - 5, 10);

            penalty += ticketFare * 0.01 * secondTier;
        }

        if (minutesLate > 15) {
            int thirdTier = minutesLate - 15;

            penalty += ticketFare * 0.02 * thirdTier;
        }

        double floor =
                ticketFare *
                (minimumPenaltyPercent / 100.0);

        return Math.max(penalty, floor);
    }
}

public class F4 {

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1.0);

        System.out.println(
                "Penalty: Rs "
                + calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                "Penalty: Rs "
                + calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                "Penalty: Rs "
                + calculator.calculatePenalty(1000, 16)
        );
    }
}