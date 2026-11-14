
package oop.assignment_problems;

import java.util.Scanner;

public class W12A5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Reverse a linked list
    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;

        while (current != null) {

            Node next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // Check whether the linked list is palindrome
    static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Find the middle of the linked list
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        Node secondHalf = reverse(slow.next);

        // Compare both halves
        Node first = head;
        Node second = secondHalf;

        boolean result = true;

        while (second != null) {

            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original linked list
        slow.next = reverse(secondHalf);

        return result;
    }

    // Print the linked list
    static void printList(Node head) {

        Node current = head;

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        // Create linked list
        for (int i = 0; i < n; i++) {

            int value = sc.nextInt();

            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        boolean result = isPalindrome(head);

        System.out.println("Palindrome: " + result);

        // Print list after checking
        printList(head);

        sc.close();
    }
}
