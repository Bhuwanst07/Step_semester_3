
package oop.class_problems;

public class F1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node removeNthFromEnd(Node head, int n) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Move fast pointer n steps ahead.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the last node.
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Remove the N-th node from the end.
        slow.next = slow.next.next;

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

    static void display(Node head) {

        if (head == null) {
            System.out.println("(empty list)");
            return;
        }

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

        // Sample 1
        Node head1 = createList(1, 2, 3, 4, 5);

        head1 = removeNthFromEnd(head1, 2);

        display(head1);

        // Sample 2: Removing the head itself
        Node head2 = createList(1);

        head2 = removeNthFromEnd(head2, 1);

        display(head2);
    }
}

/*
Time Complexity: O(n)
Space Complexity: O(1)
Single traversal using two pointers.
*/
