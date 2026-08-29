package oop.class_problems;

class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                    "Passenger count must be positive"
            );
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (passengerCount == 0) {
            return result;
        }

        double base = Math.floor(
                (totalFare / passengerCount) * 100.0
        ) / 100.0;

        double remaining =
                Math.round(
                        (totalFare - base * passengerCount) * 100.0
                ) / 100.0;

        for (int i = 0; i < passengerCount; i++) {
            result[i] = base;
        }

        // Extra paisa goes to the last passenger.
        result[passengerCount - 1] += remaining;

        return result;
    }

    public boolean isConfirmationOverdue(
            int confirmed, int expected) {

        return confirmed < expected;
    }
}

public class F2 {

    public static void main(String[] args) {

        FareSplitter split =
                new FareSplitter("TRIP001", 100000, 3);

        double[] result = split.fareBreakdown();

        System.out.println(
                "[" + result[0] + ", "
                + result[1] + ", "
                + result[2] + "]"
        );

        FareSplitter provisional =
                new FareSplitter("TRIP003");

        double[] provisionalResult =
                provisional.fareBreakdown();

        System.out.println(
                "[" + provisionalResult[0] + ", "
                + provisionalResult[1] + "]"
        );
    }
}