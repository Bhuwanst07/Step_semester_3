package oop.class_problems;

public class F3 {

    static class Book {

        String isbn;
        String title;

        Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    public static String findBook(
            Book[] catalog,
            String targetIsbn) {

        int left = 0;
        int right = catalog.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int comparison =
                    catalog[mid].isbn.compareTo(targetIsbn);

            if (comparison == 0) {
                return catalog[mid].title;
            }

            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {

        Book[] catalog = {
                new Book(
                        "0001112223",
                        "Introduction to Algebra"
                ),
                new Book(
                        "0002223334",
                        "Beginning Python"
                ),
                new Book(
                        "0003334445",
                        "Classic Mythology"
                ),
                new Book(
                        "0004445556",
                        "Data and Society"
                ),
                new Book(
                        "0005556667",
                        "European History"
                )
        };

        String target1 = "0003334445";
        String target2 = "0009998887";

        System.out.println(
                "Search: " + target1
        );

        System.out.println(
                "Result: " +
                        findBook(catalog, target1)
        );

        System.out.println();

        System.out.println(
                "Search: " + target2
        );

        System.out.println(
                "Result: " +
                        findBook(catalog, target2)
        );

        /*
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         *
         * Binary search is suitable because
         * queries are much more frequent than updates.
         */
    }
}