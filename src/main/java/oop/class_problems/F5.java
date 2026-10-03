package oop.class_problems;

import java.util.Arrays;

public class F5 {

    public static int maxSumSubarray(
            int[] sales,
            int k) {

        if (sales == null ||
                k <= 0 ||
                k > sales.length) {

            throw new IllegalArgumentException(
                    "Invalid value of k"
            );
        }

        int currentSum = 0;

        // Calculate first window
        for (int i = 0; i < k; i++) {
            currentSum += sales[i];
        }

        int maxSum = currentSum;

        // Slide the window
        for (int i = k; i < sales.length; i++) {

            currentSum += sales[i];
            currentSum -= sales[i - k];

            maxSum = Math.max(
                    maxSum,
                    currentSum
            );
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] sales = {
                2, 1, 5, 1, 3, 2
        };

        int k = 3;

        System.out.println(
                "Sales: " + Arrays.toString(sales)
        );

        System.out.println(
                "K: " + k
        );

        System.out.println(
                "Maximum Sum: " +
                        maxSumSubarray(sales, k)
        );

        /*
         * Naive approach:
         * O(n * k)
         *
         * Sliding Window:
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
    }
}