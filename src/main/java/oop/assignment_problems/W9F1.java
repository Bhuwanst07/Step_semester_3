
package oop.assignment_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class W9F1 {

    public static List<Long> footfallReport(
            int[] visitors, int[][] queries) {

        int n = visitors.length;

        // prefix[i] stores the sum of the first i elements.
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        List<Long> result = new ArrayList<>();

        // Each query contains an inclusive start and end index.
        for (int[] query : queries) {

            int start = query[0];
            int end = query[1];

            if (start < 0 || end < start || end >= n) {
                throw new IllegalArgumentException(
                        "Invalid query range: "
                                + start + " to " + end
                );
            }

            long sum = prefix[end + 1] - prefix[start];
            result.add(sum);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
                {0, 2},
                {2, 5},
                {4, 6},
                {3, 3}
        };

        System.out.println(
                "Visitors: " + Arrays.toString(visitors)
        );

        System.out.println(
                "Footfall Report: "
                        + footfallReport(visitors, queries)
        );
    }
}
