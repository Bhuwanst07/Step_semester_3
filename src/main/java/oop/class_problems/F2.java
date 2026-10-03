package oop.class_problems;

import java.util.Arrays;

public class F2 {

    public static void warehouseSummary(int[][] grid) {

        int total = 0;

        int maxValue = -1;
        int maxRow = -1;
        int maxCol = -1;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                total += grid[i][j];

                if (grid[i][j] > maxValue) {
                    maxValue = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Grid:");

        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("Total = " + total);

        System.out.println(
                "Max Coordinate = (" +
                        maxRow +
                        ", " +
                        maxCol +
                        ")"
        );

        /*
         * Time Complexity: O(m * n)
         * Space Complexity: O(1)
         */
    }

    public static void main(String[] args) {

        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}