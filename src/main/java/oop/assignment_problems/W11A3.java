package oop.assignment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

public class W11A2 {

    public static String expandBanner(String code) {

        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack =
                new ArrayDeque<>();

        StringBuilder current = new StringBuilder();

        int i = 0;

        while (i < code.length()) {

            char ch = code.charAt(i);

            // Read a possibly multi-digit repeat count.
            if (Character.isDigit(ch)) {

                int count = 0;

                while (i < code.length() &&
                        Character.isDigit(code.charAt(i))) {

                    count = count * 10 +
                            (code.charAt(i) - '0');

                    i++;
                }

                if (i >= code.length() ||
                        code.charAt(i) != '[' ||
                        count <= 0) {

                    throw new IllegalArgumentException(
                            "Invalid repeat pattern"
                    );
                }

                countStack.push(count);
                stringStack.push(current);

                current = new StringBuilder();

                i++; // Skip '['
            }

            else if (ch == ']') {

                if (countStack.isEmpty() ||
                        stringStack.isEmpty()) {

                    throw new IllegalArgumentException(
                            "Invalid closing bracket"
                    );
                }

                int repeat = countStack.pop();

                StringBuilder previous =
                        stringStack.pop();

                StringBuilder expanded = previous;

                for (int j = 0; j < repeat; j++) {
                    expanded.append(current);
                }

                current = expanded;

                i++;
            }

            else if (ch == '[') {

                throw new IllegalArgumentException(
                        "Missing repeat count"
                );

            }

            else {

                // Normal character.
                current.append(ch);
                i++;
            }
        }

        if (!countStack.isEmpty() ||
                !stringStack.isEmpty()) {

            throw new IllegalArgumentException(
                    "Unmatched brackets"
            );
        }

        return current.toString();
    }

    public static void main(String[] args) {

        String code1 = "3[ab]2[c]";

        String code2 = "2[x3[y]]";

        System.out.println(
                expandBanner(code1)
        );

        System.out.println(
                expandBanner(code2)
        );
    }
}