
package oop.assignment_problems;

public class W10A3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node mergeSorted(Node a, Node b) {

        // The only extra node created by the merge.
        Node dummy = new Node(0);
        Node tail = dummy;

        while (a != null && b != null) {

            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        // Attach whichever list still has remaining nodes.
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

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
        if (head == null) {
            System.out.println("Empty");
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

        Node a = createList(3, 8, 15);
        Node b = createList(5, 8, 12, 20);

        printList(mergeSorted(a, b));

        Node empty = null;
        Node single = createList(4);

        printList(mergeSorted(empty, single));
    }
}

/*
Time Complexity: O(m + n)
Auxiliary Space: O(1), excluding input lists.
Duplicates are retained.
*/
