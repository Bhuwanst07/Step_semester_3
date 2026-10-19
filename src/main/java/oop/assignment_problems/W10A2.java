
package oop.assignment_problems;

public class W10A2 {

    static class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
        }
    }

    static class BrowserHistory {

        private Node current;

        BrowserHistory(String home) {
            current = new Node(home);
        }

        void visit(String url) {

            // Discard forward history.
            current.next = null;

            Node newPage = new Node(url);
            newPage.prev = current;
            current.next = newPage;

            current = newPage;
        }

        String back(int steps) {

            while (steps > 0 && current.prev != null) {
                current = current.prev;
                steps--;
            }

            return current.url;
        }

        String forward(int steps) {

            while (steps > 0 && current.next != null) {
                current = current.next;
                steps--;
            }

            return current.url;
        }

        String currentPage() {
            return current.url;
        }
    }

    public static void main(String[] args) {

        BrowserHistory history =
                new BrowserHistory("srm.edu");

        history.visit("a.com");
        history.visit("b.com");
        history.visit("c.com");

        System.out.println(history.back(1));
        System.out.println(history.back(1));
        System.out.println(history.forward(1));

        history.visit("d.com");

        System.out.println(history.forward(2));
        System.out.println(history.back(2));
        System.out.println(history.back(7));
    }
}

/*
visit: O(1)
back/forward: O(steps actually moved)
Space Complexity: O(number of retained pages)
*/
