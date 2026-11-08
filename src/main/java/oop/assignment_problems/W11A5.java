package oop.assignment_problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class W11A5 {

    public static int[][] existDistance(char[][] plan) {

        int rows = plan.length;
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];

        Queue<int[]> queue = new ArrayDeque<>();

        // Initialize distances and add all exits.
        for (int i = 0; i < rows; i++) {

            Arrays.fill(distance[i], -1);

            for (int j = 0; j < cols; j++) {

                if (plan[i][j] == 'E') {

                    distance[i][j] = 0;

                    queue.offer(new int[]{i, j});
                }
            }
        }

        // Four possible directions.
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // Multi-source BFS.
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Check grid boundaries.
                if (nr < 0 || nr >= rows ||
                        nc < 0 || nc >= cols) {
                    continue;
                }

                // Skip walls and already visited rooms.
                if (plan[nr][nc] == '#' ||
                        distance[nr][nc] != -1) {
                    continue;
                }

                distance[nr][nc] =
                        distance[r][c] + 1;

                queue.offer(new int[]{nr, nc});
            }
        }

        return distance;
    }

    public static void main(String[] args) {

        char[][] plan = {
                ".#.E".toCharArray(),
                "...#".toCharArray(),
                "E#..".toCharArray()
        };

        int[][] result = existDistance(plan);

        for (int[] row : result) {

            for (int value : row) {
                System.out.print(value + " ");
            }

            System.out.println();
        }
    }
}

/*
Time Complexity: O(rows * columns)
Space Complexity: O(rows * columns)
Multi-source BFS.
*/