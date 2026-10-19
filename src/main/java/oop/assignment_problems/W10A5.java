
package oop.assignment_problems;

public class W10A5 {

    static class Node {
        int coefficient;
        int exponent;
        Node next;

        Node(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    public static Node addPolynomials(Node p, Node q) {

        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {

            if (p.exponent > q.exponent) {

                if (p.coefficient != 0) {
                    tail.next = new Node(
                            p.coefficient, p.exponent
                    );
                    tail = tail.next;
                }

                p = p.next;

            } else if (p.exponent < q.exponent) {

                if (q.coefficient != 0) {
                    tail.next = new Node(
                            q.coefficient, q.exponent
                    );
                    tail = tail.next;
                }

                q = q.next;

            } else {

                int sum = p.coefficient + q.coefficient;

                // Do not include zero-coefficient terms.
                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {

            if (p.coefficient != 0) {
                tail.next = new Node(
                        p.coefficient, p.exponent
                );
                tail = tail.next;
            }

            p = p.next;
        }

        while (q != null) {

            if (q.coefficient != 0) {
                tail.next = new Node(
                        q.coefficient, q.exponent
                );
                tail = tail.next;
            }

            q = q.next;
        }

        return dummy.next;
    }

    static Node createPolynomial(int[][] terms) {

        Node dummy = new Node(0, 0);
        Node tail = dummy;

        for (int[] term : terms) {
            if (term[0] != 0) {
                tail.next = new Node(term[0], term[1]);
                tail = tail.next;
            }
        }

        return dummy.next;
    }

    static String formatPolynomial(Node head) {

        if (head == null) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        while (head != null) {

            long coefficient = head.coefficient;
            long absolute = Math.abs(coefficient);

            if (first) {
                if (coefficient < 0) {
                    result.append("-");
                }
            } else {
                result.append(
                        coefficient < 0 ? " - " : " + "
                );
            }

            if (head.exponent == 0) {

                result.append(absolute);

            } else {

                if (absolute != 1) {
                    result.append(absolute);
                }

                result.append("x");

                if (head.exponent != 1) {
                    result.append("^").append(head.exponent);
                }
            }

            first = false;
            head = head.next;
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Node p = createPolynomial(new int[][] {
                {5, 3}, {4, 2}, {2, 0}
        });

        Node q = createPolynomial(new int[][] {
                {5, 2}, {-3, 1}, {1, 0}
        });

        System.out.println(
                formatPolynomial(addPolynomials(p, q))
        );

        Node p2 = createPolynomial(new int[][] {
                {3, 2}, {1, 0}
        });

        Node q2 = createPolynomial(new int[][] {
                {-3, 2}, {4, 1}
        });

        System.out.println(
                formatPolynomial(addPolynomials(p2, q2))
        );
    }
}

/*
Time Complexity: O(m + n)
Output Space: O(m + n) in the worst case.
Each input list is traversed once.
*/
