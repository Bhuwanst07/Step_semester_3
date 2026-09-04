package oop.assignment_problems;

class DeliveryAccount {

    protected String studentId;
    protected double orderValue;
    protected double settledAmount;

    static {
        System.out.println("Nightly reconciliation initialized.");
    }

    public DeliveryAccount(
            String studentId,
            double orderValue) {

        if (orderValue < 0) {
            throw new IllegalArgumentException(
                    "Order value cannot be negative"
            );
        }

        this.studentId = studentId;
        this.orderValue = orderValue;
        this.settledAmount = 0.0;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(
            int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative"
            );
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        // Reusing Problem 4's tiered surge-fee version.
        double surge = 0.0;

        int firstTier =
                Math.min(delayMinutes, 5);

        surge += orderValue * 0.005 * firstTier;

        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes - 5, 10);

            surge += orderValue * 0.01 * secondTier;
        }

        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            surge += orderValue * 0.02 * thirdTier;
        }

        return surge;
    }

    void settleRegular(double amount) {
        if (amount >= 0) {
            settledAmount += amount;
        }
    }
}

class PremiumAccount extends DeliveryAccount {

    PremiumAccount(
            String studentId,
            double orderValue) {

        super(studentId, orderValue);
    }

    void settlePremium(double amount) {
        if (amount >= 0) {
            // Premium settlement is handled separately.
            settledAmount += amount;
        }
    }
}

public class F5 {

    static int processed = 0;
    static int nullSkipped = 0;
    static int premiumCount = 0;
    static int regularCount = 0;
    static double grandTotalSurgeFees = 0.0;

    static void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        if (account == null) {
            nullSkipped++;
            return;
        }

        if (amount < 0 || delayMinutes < 0) {
            return;
        }

        if (account instanceof PremiumAccount) {

            PremiumAccount premium =
                    (PremiumAccount) account;

            premium.settlePremium(amount);
            premiumCount++;

        } else {

            account.settleRegular(amount);
            regularCount++;
        }

        grandTotalSurgeFees +=
                account.calculateSurgeFee(delayMinutes);

        processed++;
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        // Process only the indexes that exist in all three arrays.
        // This prevents mismatched arrays from pairing wrong data.
        int length =
                Math.min(
                        accounts.length,
                        Math.min(
                                amounts.length,
                                delayMinutesArray.length
                        )
                );

        for (int i = 0; i < length; i++) {

            processAccount(
                    accounts[i],
                    amounts[i],
                    delayMinutesArray[i]
            );
        }
    }

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {
            new PremiumAccount("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {
            500, 400, 300
        };

        int[] delays = {
            10, 5, 0
        };

        processBatch(
                accounts,
                amounts,
                delays
        );

        System.out.println(
                processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + premiumCount
                + " premium | "
                + regularCount
                + " regular | "
                + "grand total surge fees = "
                + grandTotalSurgeFees
        );
    }
}