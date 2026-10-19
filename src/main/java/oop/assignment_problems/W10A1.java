
package oop.assignment_problems;

public class W10A1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node removeKthFromEnd(Node head, int k) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trail = dummy;

        // Move lead k + 1 steps ahead.
        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        // Move both pointers until lead reaches the end.
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        // Remove the target node.
        trail.next = trail.next.next;

        return dummy.next;
    }

    static Node createList(int... values) {
        Node dummy = new Node(0);
        Node tail = dummy;

        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Node first = createList(10, 20, 30, 40, 50);
        printList(removeKthFromEnd(first, 2));

        Node second = createList(10, 20, 30, 40, 50);
        printList(removeKthFromEnd(second, 5));
    }
}

/*
Time Complexity: O(n)
Auxiliary Space: O(1), excluding the input list.
*/
