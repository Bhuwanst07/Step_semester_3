package oop.class_problems;

public class F1 {

    public static abstract class PaymentMethod {

        private static int transactionCounter = 1000;

        private final String transactionId;

        public PaymentMethod() {
            transactionCounter++;
            transactionId = "TXN-" + transactionCounter;
        }

        public abstract String processPayment(double amount);

        public String processPayment(double amount, String note) {
            return processPayment(amount) + " (" + note + ")";
        }

        public String getTransactionId() {
            return transactionId;
        }
    }

    public static class CreditCardPayment
            extends PaymentMethod {

        private final String cardNumberLastFour;

        public CreditCardPayment(String cardNumberLastFour) {
            this.cardNumberLastFour = cardNumberLastFour;
        }

        @Override
        public String processPayment(double amount) {
            return "Charged $"
                    + amount
                    + " to card ending "
                    + cardNumberLastFour
                    + " - Txn "
                    + getTransactionId();
        }
    }

    public static class CashPayment
            extends PaymentMethod {

        public CashPayment() {
        }

        @Override
        public String processPayment(double amount) {
            return "Received $"
                    + amount
                    + " in cash - Txn "
                    + getTransactionId();
        }
    }

    public static void printConfirmation(
            PaymentMethod payment,
            double amount) {

        System.out.println(
                payment.processPayment(amount)
        );
    }

    static void testUpcasting() {

        CreditCardPayment cc =
                new CreditCardPayment("4471");

        // Upcasting:
        // CreditCardPayment object stored in a
        // PaymentMethod reference.
        PaymentMethod ref = cc;

        printConfirmation(ref, 250.0);
    }

    public static void main(String[] args) {

        CreditCardPayment cc =
                new CreditCardPayment("4471");

        System.out.println(
                cc.processPayment(250.0)
        );

        CashPayment cash =
                new CashPayment();

        System.out.println(
                cash.processPayment(40.0)
        );

        System.out.println(
                cc.processPayment(
                        250.0,
                        "Birthday gift"
                )
        );

        testUpcasting();

        // PaymentMethod cannot be instantiated directly
        // because it is abstract.
    }
}