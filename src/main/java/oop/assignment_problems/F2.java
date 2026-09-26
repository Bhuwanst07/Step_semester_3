package oop.assignment_problems;

import java.util.*;

public class F2 {

    enum Status {
        BOOKED,
        PICKED_UP,
        IN_TRANSIT,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    interface ShippingType {
        double calculateCharge(double weightKg);
        String getName();
    }

    static class StandardShipping implements ShippingType {

        public double calculateCharge(double weightKg) {
            return 40 + 10 * weightKg;
        }

        public String getName() {
            return "Standard";
        }
    }

    static class ExpressShipping implements ShippingType {

        public double calculateCharge(double weightKg) {
            return 80 + 15 * weightKg;
        }

        public String getName() {
            return "Express";
        }
    }

    static class FragileShipping implements ShippingType {

        private final ShippingType standard =
                new StandardShipping();

        public double calculateCharge(double weightKg) {
            return standard.calculateCharge(weightKg) + 50;
        }

        public String getName() {
            return "Fragile";
        }
    }

    interface NotificationChannel {
        void notify(String parcelId, Status status);
    }

    static class SmsChannel implements NotificationChannel {

        public void notify(String parcelId, Status status) {
            System.out.println(
                    "[SMS] " + parcelId
                            + " is now " + status + "."
            );
        }
    }

    static class EmailChannel implements NotificationChannel {

        public void notify(String parcelId, Status status) {
            System.out.println(
                    "[Email] " + parcelId
                            + " is now " + status + "."
            );
        }
    }

    static class Customer {
        String name;

        Customer(String name) {
            this.name = name;
        }
    }

    static class Parcel {

        String id;
        double weightKg;
        ShippingType shippingType;

        Status status = Status.BOOKED;
        boolean cancelled = false;

        List<NotificationChannel> channels =
                new ArrayList<>();

        Parcel(
                String id,
                double weightKg,
                ShippingType shippingType) {

            this.id = id;
            this.weightKg = weightKg;
            this.shippingType = shippingType;
        }

        void subscribe(NotificationChannel channel) {
            channels.add(channel);
        }

        void notifyChannels() {
            for (NotificationChannel channel : channels) {
                channel.notify(id, status);
            }
        }

        boolean moveTo(Status next) {

            if (cancelled) {
                System.out.println(
                        "Invalid transition: Parcel is cancelled."
                );
                return false;
            }

            if (next.ordinal() != status.ordinal() + 1) {

                System.out.println(
                        "Invalid transition: "
                                + status
                                + " → "
                                + next
                                + " is not allowed."
                );

                return false;
            }

            status = next;
            notifyChannels();

            return true;
        }

        boolean cancel() {

            if (status != Status.BOOKED) {

                System.out.println(
                        "Cancellation failed: "
                                + id
                                + " can be cancelled only while BOOKED."
                );

                return false;
            }

            cancelled = true;

            System.out.println(
                    "Parcel " + id + " cancelled."
            );

            return true;
        }
    }

    static class ParcelService {

        Parcel book(
                Customer customer,
                String id,
                double weightKg,
                ShippingType shippingType) {

            Parcel parcel =
                    new Parcel(
                            id,
                            weightKg,
                            shippingType
                    );

            System.out.printf(
                    "Parcel %s booked (%s, %.0f kg).%n",
                    id,
                    shippingType.getName(),
                    weightKg
            );

            System.out.printf(
                    "Charge: ₹%.2f%n",
                    shippingType.calculateCharge(weightKg)
            );

            return parcel;
        }
    }

    public static void main(String[] args) {

        ParcelService service =
                new ParcelService();

        Customer customer =
                new Customer("Asha");

        Parcel parcel =
                service.book(
                        customer,
                        "P101",
                        2,
                        new ExpressShipping()
                );

        parcel.subscribe(new SmsChannel());
        parcel.subscribe(new EmailChannel());

        parcel.notifyChannels();

        parcel.moveTo(Status.PICKED_UP);

        parcel.cancel();

        parcel.moveTo(Status.IN_TRANSIT);

        parcel.moveTo(Status.DELIVERED);
    }
}