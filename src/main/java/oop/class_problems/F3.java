package oop.class_problems;

import java.util.Arrays;

class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int historyCount;

    public EventTicket(double basePrice) {

        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Base price must be positive"
            );
        }

        this.basePrice = basePrice;
        this.amountPaid = 0.0;

        this.lateFeeHistory = new double[10];
        this.historyCount = 0;
    }

    void pay(double amount) {

        if (amount > 0) {
            amountPaid += amount;
        }
    }

    double getBalanceDue() {
        return basePrice - amountPaid;
    }

    protected void applyLateFee(double amount) {

        if (amount <= 0) {
            return;
        }

        basePrice += amount;

        if (historyCount < lateFeeHistory.length) {
            lateFeeHistory[historyCount] = amount;
            historyCount++;
        }
    }

    double[] getLateFeeHistory() {

        return Arrays.copyOf(
                lateFeeHistory,
                historyCount
        );
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class F3 {

    public static void main(String[] args) {

        WorkshopTicket w =
                new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
                w.getBalanceDue()
        );

        double[] history =
                w.getLateFeeHistory();

        System.out.println(
                Arrays.toString(history)
        );

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        w.getLateFeeHistory()
                )
        );
    }
}