package oop.assignment_problems;

import java.util.*;

public class F4 {

    interface CreditPolicy {

        String type();

        int maxCredits();
    }

    static class RegularPolicy implements CreditPolicy {

        public String type() {
            return "Regular";
        }

        public int maxCredits() {
            return 24;
        }
    }

    static class HonorsPolicy implements CreditPolicy {

        public String type() {
            return "Honors";
        }

        public int maxCredits() {
            return 28;
        }
    }

    static class ExchangePolicy implements CreditPolicy {

        public String type() {
            return "Exchange";
        }

        public int maxCredits() {
            return 20;
        }
    }

    static class Student {

        String name;
        int currentCredits;
        CreditPolicy policy;

        Set<Elective> enrolled =
                new HashSet<>();

        Set<Elective> waitingFor =
                new HashSet<>();

        Student(
                String name,
                CreditPolicy policy,
                int currentCredits) {

            this.name = name;
            this.policy = policy;
            this.currentCredits = currentCredits;
        }

        int maxCredits() {
            return policy.maxCredits();
        }
    }

    static class Enrollment {

        Student student;
        Elective elective;

        Enrollment(
                Student student,
                Elective elective) {

            this.student = student;
            this.elective = elective;
        }
    }

    static class Elective {

        String name;
        int credits;
        int capacity;

        List<Enrollment> enrolled =
                new ArrayList<>();

        private Queue<Student> waitlist =
                new ArrayDeque<>();

        Elective(
                String name,
                int credits,
                int capacity) {

            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        boolean isFull() {
            return enrolled.size() >= capacity;
        }

        boolean contains(Student student) {
            return student.enrolled.contains(this)
                    || student.waitingFor.contains(this);
        }

        void addEnrollment(Student student) {

            enrolled.add(
                    new Enrollment(
                            student,
                            this
                    )
            );

            student.enrolled.add(this);
            student.waitingFor.remove(this);
            student.currentCredits += credits;
        }

        void addToWaitlist(Student student) {

            waitlist.add(student);
            student.waitingFor.add(this);
        }

        int waitlistPosition(Student student) {

            int position = 1;

            for (Student s : waitlist) {

                if (s == student) {
                    return position;
                }

                position++;
            }

            return -1;
        }

        Student nextWaitlisted() {
            return waitlist.poll();
        }

        void dropEnrollment(Student student) {

            enrolled.removeIf(
                    e -> e.student == student
            );

            student.enrolled.remove(this);
            student.currentCredits -= credits;
        }
    }

    static class EnrollmentService {

        boolean enroll(
                Student student,
                Elective elective) {

            if (elective.contains(student)) {

                System.out.println(
                        "Enrollment failed: "
                                + student.name
                                + " is already enrolled or waitlisted for "
                                + elective.name
                                + "."
                );

                return false;
            }

            // Credit check happens before seat availability.
            if (student.currentCredits + elective.credits
                    > student.maxCredits()) {

                System.out.printf(
                        "Enrollment failed: %s would exceed the %s credit limit (%d/%d).%n",
                        student.name,
                        student.policy.type(),
                        student.currentCredits + elective.credits,
                        student.maxCredits()
                );

                return false;
            }

            if (elective.isFull()) {

                elective.addToWaitlist(student);

                System.out.printf(
                        "%s added to waitlist (position %d).%n",
                        student.name,
                        elective.waitlistPosition(student)
                );

                return true;
            }

            elective.addEnrollment(student);

            System.out.printf(
                    "%s enrolled in %s (credits: %d/%d).%n",
                    student.name,
                    elective.name,
                    student.currentCredits,
                    student.maxCredits()
            );

            return true;
        }

        boolean drop(
                Student student,
                Elective elective) {

            if (!student.enrolled.contains(elective)) {

                System.out.println(
                        "Drop failed: "
                                + student.name
                                + " is not enrolled in "
                                + elective.name
                                + "."
                );

                return false;
            }

            elective.dropEnrollment(student);

            System.out.printf(
                    "%s dropped %s (credits: %d/%d).%n",
                    student.name,
                    elective.name,
                    student.currentCredits,
                    student.maxCredits()
            );

            promote(elective);

            return true;
        }

        private void promote(Elective elective) {

            while (!elective.isFull()) {

                Student student =
                        elective.nextWaitlisted();

                if (student == null) {
                    return;
                }

                student.waitingFor.remove(elective);

                // Re-check credit limit during promotion.
                if (student.currentCredits + elective.credits
                        <= student.maxCredits()) {

                    elective.addEnrollment(student);

                    System.out.printf(
                            "%s promoted from waitlist and enrolled in %s (credits: %d/%d).%n",
                            student.name,
                            elective.name,
                            student.currentCredits,
                            student.maxCredits()
                    );

                    return;
                }

                System.out.printf(
                        "%s skipped from waitlist: credit limit would be exceeded.%n",
                        student.name
                );
            }
        }
    }

    public static void main(String[] args) {

        Elective cloud =
                new Elective(
                        "Cloud Computing",
                        4,
                        2
                );

        EnrollmentService service =
                new EnrollmentService();

        Student asha =
                new Student(
                        "Asha",
                        new RegularPolicy(),
                        20
                );

        Student ravi =
                new Student(
                        "Ravi",
                        new HonorsPolicy(),
                        22
                );

        Student neha =
                new Student(
                        "Neha",
                        new ExchangePolicy(),
                        12
                );

        Student kiran =
                new Student(
                        "Kiran",
                        new RegularPolicy(),
                        22
                );

        service.enroll(asha, cloud);

        service.enroll(ravi, cloud);

        System.out.println(
                "Cloud Computing is full."
        );

        service.enroll(neha, cloud);

        service.enroll(kiran, cloud);

        service.drop(asha, cloud);
    }
}