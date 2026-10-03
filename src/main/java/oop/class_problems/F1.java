package oop.class_problems;

import java.util.Arrays;

public class F1 {

    public static String pairSumSorted(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int sum = nums[left] + nums[right];

            if (sum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {

        int[] nums1 = {-4, -1, 0, 3, 5, 9};
        int target1 = 4;

        int[] nums2 = {1, 2, 3};
        int target2 = 100;

        System.out.println(
                "Array: " + Arrays.toString(nums1)
        );

        System.out.println(
                "Target: " + target1
        );

        System.out.println(
                "Output: " + pairSumSorted(nums1, target1)
        );

        System.out.println();

        System.out.println(
                "Array: " + Arrays.toString(nums2)
        );

        System.out.println(
                "Target: " + target2
        );

        System.out.println(
                "Output: " + pairSumSorted(nums2, target2)
        );

        /*
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         *
         * Brute force would take O(n^2).
         */
    }
}