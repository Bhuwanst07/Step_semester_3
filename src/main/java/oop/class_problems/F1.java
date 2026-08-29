package oop.class_problems;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    // classAverage works on many students, so it belongs to the class.
    // isEligible depends on one student's data, so it is an instance method.
    static double classAverage(SrmStudent[] students) {
        int total = 0;

        for (SrmStudent student : students) {
            total += student.attendance;
        }

        return (double) total / students.length;
    }
}

public class F1 {
    public static void main(String[] args) {

        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA101", 82),
            new SrmStudent("Anitha", "RA102", 68),
            new SrmStudent("Karthik", "RA103", 91),
            new SrmStudent("Meera", "RA104", 74),
            new SrmStudent("Suresh", "RA105", 60)
        };

        for (SrmStudent student : students) {
            String status = student.isEligible()
                    ? "Eligible"
                    : "Detained";

            System.out.println(
                    student.name + " - "
                    + student.attendance + "% - "
                    + status
            );
        }

        System.out.printf(
                "Class average: %.1f%%%n",
                SrmStudent.classAverage(students)
        );
    }
}