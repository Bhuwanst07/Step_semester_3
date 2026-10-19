package oop.class_problems;

public class F3 {

    public static void main(String[] args) {

        String[] subjects = {
                "Math", "Sci", "Eng"
        };

        String[] students = {
                "Anu", "Ravi", "Meena"
        };

        Integer[][] marks = {
                {80, 90, 70},
                {60, 85, null},
                {95, 75, 88}
        };

        // Calculate total marks for every student.
        System.out.print("Totals ");

        for (int i = 0; i < students.length; i++) {

            int total = 0;

            for (int j = 0; j < subjects.length; j++) {

                if (marks[i][j] != null) {
                    total += marks[i][j];
                }
            }

            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(students[i] + " " + total);
        }

        System.out.println();

        // Find the topper in each subject.
        System.out.print("Toppers ");

        for (int j = 0; j < subjects.length; j++) {

            int highest = -1;
            int topperIndex = -1;

            for (int i = 0; i < students.length; i++) {

                if (marks[i][j] != null &&
                        marks[i][j] > highest) {

                    highest = marks[i][j];
                    topperIndex = i;
                }
            }

            if (j > 0) {
                System.out.print(", ");
            }

            System.out.print(
                    subjects[j] + " "
                            + students[topperIndex] + " "
                            + highest
            );
        }

        System.out.println();
    }
}