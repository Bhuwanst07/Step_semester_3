package oop.class_problems;

class BrokenStudent {
    static String name;
    static String regNo;
    static int attendance;

    BrokenStudent(String name, String regNo, int attendance) {
        BrokenStudent.name = name;
        BrokenStudent.regNo = regNo;
        BrokenStudent.attendance = attendance;
    }
}

class FixedStudent {

    String name;
    String regNo;
    int attendance;

    static String university = "SRM University";
    static int admissionCount = 0;

    FixedStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        this.regNo =
                "RA2311003010"
                + String.format("%02d", admissionCount);
    }

    void printIdCard() {
        System.out.println(name + " | " + regNo);
    }

    static void printTotalAdmissions() {
        System.out.println(
                "Students admitted so far: " + admissionCount
        );
    }
}

public class F4 {
    public static void main(String[] args) {

        // Broken version:
        // name, regNo and attendance are student-specific,
        // so making them static causes every object to share them.
        System.out.println("Broken version:");

        BrokenStudent student1 =
                new BrokenStudent("Ravi", "RA001", 82);

        BrokenStudent student2 =
                new BrokenStudent("Meera", "RA002", 74);

        System.out.println(student1.name);
        System.out.println(student2.name);

        System.out.println("\nFixed version:");

        FixedStudent fixed1 =
                new FixedStudent("Ravi", 82);

        FixedStudent fixed2 =
                new FixedStudent("Meera", 74);

        fixed1.printIdCard();
        fixed2.printIdCard();

        FixedStudent.printTotalAdmissions();
    }
}