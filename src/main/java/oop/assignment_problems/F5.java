package oop.assignment_problems;

import java.util.*;

public class F5 {

    interface PricingPlan {

        String name();

        double price(double originalPrice);
    }

    static class DayScholarPlan implements PricingPlan {

        public String name() {
            return "Day Scholar";
        }

        public double price(double originalPrice) {
            return originalPrice;
        }
    }

    static class HostellerPlan implements PricingPlan {

        public String name() {
            return "Hosteller";
        }

        public double price(double originalPrice) {
            return originalPrice * 0.90;
        }
    }

    static class StaffPlan implements PricingPlan {

        public String name() {
            return "Staff";
        }

        public double price(double originalPrice) {
            return originalPrice * 0.80;
        }
    }

    static class Transaction {

        double amount;
        String description;

        Transaction(
                double amount,
                String description) {

            this.amount = amount;
            this.description = description;
        }
    }

    static class Purchase {

        String item;
        double chargedAmount;
        boolean refunded;

        Purchase(
                String item,
                double chargedAmount) {

            this.item = item;
            this.chargedAmount = chargedAmount;
        }
    }

    static class SmartCard {

        private final String cardId;
        private final PricingPlan plan;

        private final List<Transaction> transactions =
                new ArrayList<>();

        private final List<Purchase> purchases =
                new ArrayList<>();

        private boolean blocked = false;

        SmartCard(
                String cardId,
                PricingPlan plan) {

            this.cardId = cardId;
            this.plan = plan;
        }

        private double balance() {

            double total = 0;

            for (Transaction transaction : transactions) {
                total += transaction.amount;
            }

            return total;
        }

        private boolean active() {

            if (blocked) {

                System.out.println(
                        "Operation rejected: Card "
                                + cardId
                                + " is blocked."
                );

                return false;
            }

            return true;
        }

        void topUp(double amount) {

            if (!active()) {
                return;
            }

            if (amount < 100) {

                System.out.println(
                        "Top-up failed: Minimum top-up is ₹100.00."
                );

                return;
            }

            if (balance() + amount > 5000) {

                System.out.println(
                        "Top-up failed: Maximum balance is ₹5000.00."
                );

                return;
            }

            transactions.add(
                    new Transaction(
                            amount,
                            "Top-up"
                    )
            );

            System.out.printf(
                    "%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                    cardId,
                    amount,
                    balance()
            );
        }

        Purchase purchase(
                String item,
                double originalPrice) {

            if (!active()) {
                return null;
            }

            double charge =
                    Math.round(
                            plan.price(originalPrice) * 100.0
                    ) / 100.0;

            if (charge > balance()) {

                System.out.printf(
                        "Purchase failed: Insufficient balance "
                                + "(required ₹%.2f, available ₹%.2f).%n",
                        charge,
                        balance()
                );

                return null;
            }

            transactions.add(
                    new Transaction(
                            -charge,
                            item
                    )
            );

            Purchase purchase =
                    new Purchase(
                            item,
                            charge
                    );

            purchases.add(purchase);

            System.out.printf(
                    "%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                    item,
                    charge,
                    balance()
            );

            return purchase;
        }

        void refund(Purchase purchase) {

            if (purchase == null) {

                System.out.println(
                        "Refund rejected: Invalid purchase."
                );

                return;
            }

            if (purchase.refunded) {

                System.out.println(
                        "Refund rejected: "
                                + purchase.item
                                + " has already been refunded."
                );

                return;
            }

            transactions.add(
                    new Transaction(
                            purchase.chargedAmount,
                            "Refund: " + purchase.item
                    )
            );

            purchase.refunded = true;

            System.out.printf(
                    "Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                    purchase.chargedAmount,
                    purchase.item,
                    balance()
            );
        }

        void block() {

            blocked = true;

            System.out.println(
                    cardId + " blocked."
            );
        }

        void unblock() {

            blocked = false;

            System.out.println(
                    cardId + " unblocked."
            );
        }

        void miniStatement() {

            StringBuilder statement =
                    new StringBuilder();

            for (int i = 0;
                 i < transactions.size();
                 i++) {

                double amount =
                        transactions.get(i).amount;

                if (i > 0) {
                    statement.append(", ");
                }

                statement.append(
                        String.format(
                                Locale.US,
                                "%s%.2f",
                                amount >= 0 ? "+" : "",
                                amount
                        )
                );
            }

            System.out.printf(
                    "Mini-statement for %s: %s = ₹%.2f.%n",
                    cardId,
                    statement,
                    balance()
            );
        }
    }

    public static void main(String[] args) {

        SmartCard card =
                new SmartCard(
                        "C-2045",
                        new HostellerPlan()
                );

        card.topUp(500);

        Purchase vegThali =
                card.purchase(
                        "Veg Thali",
                        120
                );

        card.purchase(
                "Cold Coffee",
                60
        );

        card.purchase(
                "Items worth ₹400",
                400
        );

        card.refund(vegThali);

        card.refund(vegThali);

        card.miniStatement();
    }
}