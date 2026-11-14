
package oop.assignment_problems;

import java.util.Scanner;

public class W12A4 {

    static class Node {
        String title;
        int dueDay;
        Node next;

        Node(String title, int dueDay) {
            this.title = title;
            this.dueDay = dueDay;
            this.next = null;
        }
    }

    // Find the middle of the linked list
    static Node getMiddle(Node head) {

        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Merge two sorted linked lists
    static Node merge(Node first, Node second) {

        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (first != null && second != null) {

            // <= preserves original order for equal due days
            if (first.dueDay <= second.dueDay) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }

            tail = tail.next;
        }

        if (first != null) {
            tail.next = first;
        } else {
            tail.next = second;
        }

        return dummy.next;
    }

    // Merge sort on linked list
    static Node sortByDueDay(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        // Divide the list into two halves
        Node middle = getMiddle(head);

        Node secondHalf = middle.next;
        middle.next = null;

        // Sort both halves
        Node left = sortByDueDay(head);
        Node right = sortByDueDay(secondHalf);

        // Merge the sorted halves
        return merge(left, right);
    }

    // Display the sorted linked list
    static void printList(Node head) {

        Node current = head;

        while (current != null) {

            System.out.print(
                current.title + ":" + current.dueDay
            );

            current = current.next;

            if (current != null) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        for (int i = 0; i < n; i++) {

            String input = sc.next();

            String[] parts = input.split(":");

            String title = parts[0];
            int day = Integer.parseInt(parts[1]);

            Node newNode = new Node(title, day);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        head = sortByDueDay(head);

        printList(head);

        sc.close();
    }
}
