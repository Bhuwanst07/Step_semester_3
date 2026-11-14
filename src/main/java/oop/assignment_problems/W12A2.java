
package oop.assignment_problems;

import java.util.Scanner;

public class W12A2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node reverseInGroups(Node head, int k) {

        if (head == null || k == 1) {
            return head;
        }

        // Check whether at least k nodes are available
        Node check = head;

        for (int i = 0; i < k; i++) {
            if (check == null) {
                return head;
            }
            check = check.next;
        }

        // Reverse the current group
        Node prev = null;
        Node current = head;
        Node next = null;

        for (int i = 0; i < k; i++) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Connect the reversed group to the remaining list
        head.next = reverseInGroups(current, k);

        return prev;
    }

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

        // Create the linked list
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

        int k = sc.nextInt();

        head = reverseInGroups(head, k);

        printList(head);

        sc.close();
    }
}
