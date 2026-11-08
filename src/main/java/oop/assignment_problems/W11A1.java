package oop.assignment_problems;

import java.util.Arrays;
import java.util.Stack;

public class W11A1 {

    public static int[] priceStreak(int[] price) {

        int n = price.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            // Remove all previous days whose prices
            // are less than or equal to today's price.
            while (!stack.isEmpty() &&
                    price[stack.peek()] <= price[i]) {

                stack.pop();
            }

            // Calculate the streak.
            if (stack.isEmpty()) {
                result[i] = i + 1;
            } else {
                result[i] = i - stack.peek();
            }

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] price = {
                100, 80, 60, 70, 60, 75, 85
        };

        System.out.println(
                Arrays.toString(priceStreak(price))
        );

        int[] price2 = {
                10, 20, 30
        };

        System.out.println(
                Arrays.toString(priceStreak(price2))
        );
    }
}

/*
Time Complexity: O(n)
Space Complexity: O(n)
*/