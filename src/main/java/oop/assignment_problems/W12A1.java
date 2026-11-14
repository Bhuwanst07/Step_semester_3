package main.java.oop.assignment_problems;

import java.util.Scanner;

public class W12A1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static String detectLoop(Node head) {

        Node slow = head;
        Node fast = head;

        // Detect whether a loop exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {

                // Find the starting node of the loop
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                Node loopStart = slow;

                // Calculate the loop length
                int length = 1;
                Node temp = loopStart.next;

                while (temp != loopStart) {
                    length++;
                    temp = temp.next;
                }

                return "Loop: yes, starts at stop "
                        + loopStart.data + ", length " + length;
            }
        }

        return "Loop: no";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Node[] nodes = new Node[n];

        // Create all nodes
        for (int i = 0; i < n; i++) {
            int stop = sc.nextInt();
            nodes[i] = new Node(stop);
        }

        // Connect nodes
        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        int pos = sc.nextInt();

        // Connect last node to the given position
        if (pos != -1) {
            nodes[n - 1].next = nodes[pos];
        }

        System.out.println(detectLoop(nodes[0]));

        sc.close();
    }
}
