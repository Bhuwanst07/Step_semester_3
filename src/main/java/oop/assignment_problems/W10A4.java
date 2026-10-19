
package oop.assignment_problems;

public class W10A4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    public static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        // Find the middle of the list.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // For odd lengths, skip the middle node.
        if (fast != null) {
            slow = slow.next;
        }

        // Reverse the second half.
        Node secondHalf = reverse(slow);

        Node left = head;
        Node right = secondHalf;
        boolean palindrome = true;

        while (right != null) {
            if (left.data != right.data) {
                palindrome = false;
                break;
            }

            left = left.next;
            right = right.next;
        }

        // Restore the original list before returning.
        reverse(secondHalf);

        return palindrome;
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

        Node first = createList(1, 2, 3, 2, 1);
        System.out.println(isPalindrome(first));
        printList(first);

        Node second = createList(1, 2, 3, 2);
        System.out.println(isPalindrome(second));
        printList(second);

        Node third = createList(1, 2, 2, 1);
        System.out.println(isPalindrome(third));
        printList(third);
    }
}

/*
Time Complexity: O(n)
Auxiliary Space: O(1)
The input list is restored after the check.
*/
