
package oop.class_problems;

public class F1 {

    // Order class
    static class Order {

        private LineItem[] items = new LineItem[100];
        private int size = 0;

        // Static nested class
        static class LineItem {

            String productName;
            int quantity;
            double unitPrice;

            LineItem(String productName, int quantity,
                     double unitPrice) {

                if (quantity < 1 || quantity > 1000) {
                    throw new IllegalArgumentException(
                            "Quantity must be between 1 and 1000"
                    );
                }

                this.productName = productName;
                this.quantity = quantity;
                this.unitPrice = unitPrice;
            }

            double total() {
                return quantity * unitPrice;
            }
        }

        // Add line item to internal array
        void addItem(String productName, int quantity,
                     double unitPrice) {

            if (size == items.length) {
                throw new IllegalStateException(
                        "Order cannot contain more than 100 items"
                );
            }

            items[size] = new LineItem(
                    productName, quantity, unitPrice
            );

            size++;
        }

        // Member inner class
        class Cursor {

            private int index = 0;

            boolean hasNext() {
                return index < size;
            }

            LineItem next() {

                if (!hasNext()) {
                    throw new java.util.NoSuchElementException(
                            "No more line items"
                    );
                }

                return items[index++];
            }

            void reset() {
                index = 0;
            }
        }

        // Create a cursor
        Cursor getCursor() {
            return new Cursor();
        }

        // Display order using cursor
        void displayOrder() {

            Cursor cursor = getCursor();

            double orderTotal = 0;

            System.out.println("Order Details:");

            while (cursor.hasNext()) {

                LineItem item = cursor.next();

                double itemTotal = item.total();

                System.out.printf(
                        "%s | Quantity: %d | Unit Price: %.2f | Total: %.2f%n",
                        item.productName,
                        item.quantity,
                        item.unitPrice,
                        itemTotal
                );

                orderTotal += itemTotal;
            }

            System.out.printf(
                    "Order Total: %.2f%n",
                    orderTotal
            );

            System.out.println();

            System.out.println(
                    "LineItem is static nested because it does not need an Order instance."
            );

            System.out.println(
                    "Cursor is a member inner class because it accesses the Order's internal array and size."
            );
        }
    }

    public static void main(String[] args) {

        Order order = new Order();

        order.addItem("Laptop", 1, 50000);
        order.addItem("Mouse", 2, 500);
        order.addItem("Keyboard", 1, 1500);

        order.displayOrder();

        // Demonstrate cursor reset
        Order.Cursor cursor = order.getCursor();

        cursor.next();
        cursor.reset();

        System.out.println(
                "After reset, first item: "
                        + cursor.next().productName
        );
    }
}
