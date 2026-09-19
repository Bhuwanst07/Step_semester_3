package oop.class_problems;

public class F5 {

    public static abstract class LibraryItem {

        private static int itemCounter = 1000;

        private final String itemId;

        public LibraryItem() {

            itemCounter++;

            itemId =
                    "LIB-" + itemCounter;
        }

        public abstract int getLoanPeriodDays();

        public String getItemId() {
            return itemId;
        }
    }

    public interface Renewable {
        String renew();
    }

    public interface Reservable {
        String reserve();
    }

    public static class Textbook
            extends LibraryItem
            implements Renewable, Reservable {

        private final String title;

        public Textbook(String title) {

            if (title == null
                    || title.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Title cannot be blank"
                );
            }

            this.title = title;
        }

        @Override
        public int getLoanPeriodDays() {
            return 14;
        }

        @Override
        public String renew() {
            return title + " renewed";
        }

        @Override
        public String reserve() {
            return title + " reserved";
        }
    }

    public static class Magazine
            extends LibraryItem
            implements Renewable {

        private final String title;

        public Magazine(String title) {

            if (title == null
                    || title.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Title cannot be blank"
                );
            }

            this.title = title;
        }

        @Override
        public int getLoanPeriodDays() {
            return 7;
        }

        @Override
        public String renew() {
            return title + " renewed";
        }
    }

    public static class DigitalPass
            implements Renewable {

        private final String resourceName;

        public DigitalPass(String resourceName) {

            if (resourceName == null
                    || resourceName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Resource name cannot be blank"
                );
            }

            this.resourceName = resourceName;
        }

        @Override
        public String renew() {
            return resourceName + " renewed";
        }
    }

    public static void processCheckouts(
            LibraryItem[] items) {

        for (LibraryItem item : items) {

            System.out.println(
                    item.getLoanPeriodDays()
            );
        }
    }

    public static String reserveIfSupported(
            Object o) {

        if (o instanceof Reservable) {

            Reservable reservable =
                    (Reservable) o;

            return reservable.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook t =
                new Textbook(
                        "Java Fundamentals"
                );

        System.out.println(
                t.getLoanPeriodDays()
        );

        System.out.println(
                t.renew()
        );

        System.out.println(
                t.reserve()
        );

        Magazine m =
                new Magazine(
                        "Tech Monthly"
                );

        System.out.println(
                reserveIfSupported(m)
        );

        DigitalPass d =
                new DigitalPass(
                        "E-Journal Access"
                );

        System.out.println(
                reserveIfSupported(d)
        );

        // Upcasting:
        // Textbook stored as LibraryItem.
        LibraryItem ref = t;

        System.out.println(
                reserveIfSupported(ref)
        );

        LibraryItem[] items = {
            t,
            m
        };

        processCheckouts(items);
    }
}