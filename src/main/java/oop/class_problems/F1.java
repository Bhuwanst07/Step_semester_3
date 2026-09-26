package oop.class_problems;

import java.util.*;

public class F1 {

    // Abstract question allows future question types.
    static abstract class Question {
        protected int questionNumber;
        protected String questionText;

        public Question(int questionNumber, String questionText) {
            this.questionNumber = questionNumber;
            this.questionText = questionText;
        }

        public abstract boolean evaluate(String answer);
    }

    static class MultipleChoiceQuestion extends Question {

        private String correctAnswer;

        public MultipleChoiceQuestion(
                int questionNumber,
                String questionText,
                String correctAnswer) {

            super(questionNumber, questionText);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    static class TrueFalseQuestion extends Question {

        private boolean correctAnswer;

        public TrueFalseQuestion(
                int questionNumber,
                String questionText,
                boolean correctAnswer) {

            super(questionNumber, questionText);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {

            if (answer == null) {
                return false;
            }

            boolean value =
                    Boolean.parseBoolean(answer);

            return value == correctAnswer;
        }
    }

    static class Student {

        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Attempt startExamination(
                Examination examination) {

            return examination.startAttempt(this);
        }
    }

    static class Attempt {

        private Student student;
        private Examination examination;

        private Map<Question, String> answers =
                new LinkedHashMap<>();

        private boolean submitted = false;
        private int score = -1;

        public Attempt(
                Student student,
                Examination examination) {

            this.student = student;
            this.examination = examination;
        }

        public void answer(
                Question question,
                String answer) {

            if (submitted) {
                System.out.println(
                        "Cannot change answer after submission."
                );
                return;
            }

            answers.put(question, answer);

            System.out.println(
                    "Question "
                            + question.questionNumber
                            + " answered with '"
                            + answer
                            + "'."
            );
        }

        public void submit() {

            if (submitted) {
                System.out.println(
                        "Attempt already submitted."
                );
                return;
            }

            submitted = true;

            score = 0;

            for (Question question :
                    examination.getQuestions()) {

                String answer =
                        answers.get(question);

                if (answer != null
                        && question.evaluate(answer)) {

                    score++;
                }
            }

            System.out.println(
                    "Examination '"
                            + examination.getTitle()
                            + "' submitted successfully."
            );

            System.out.println(
                    "Result for '"
                            + examination.getTitle()
                            + "' attempt: "
                            + score
                            + "/"
                            + examination.getQuestions().size()
                            + " correct"
            );
        }

        public boolean isSubmitted() {
            return submitted;
        }
    }

    static class Examination {

        private String title;

        private List<Question> questions =
                new ArrayList<>();

        private Map<Student, Attempt> attempts =
                new HashMap<>();

        public Examination(String title) {
            this.title = title;
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public String getTitle() {
            return title;
        }

        public List<Question> getQuestions() {
            return questions;
        }

        public Attempt startAttempt(Student student) {

            Attempt existing =
                    attempts.get(student);

            if (existing != null) {

                System.out.println(
                        "Student already has an attempt."
                );

                return existing;
            }

            Attempt attempt =
                    new Attempt(
                            student,
                            this
                    );

            attempts.put(
                    student,
                    attempt
            );

            System.out.println(
                    "Examination '"
                            + title
                            + "' started by "
                            + student.getName()
                            + "."
            );

            return attempt;
        }
    }

    static void showConfirmation(
            Attempt attempt) {

        attempt.submit();
    }

    public static void main(String[] args) {

        Examination exam =
                new Examination("Math Quiz");

        Question q1 =
                new MultipleChoiceQuestion(
                        1,
                        "2 + 2 = ?",
                        "A"
                );

        Question q2 =
                new MultipleChoiceQuestion(
                        2,
                        "Capital of France?",
                        "B"
                );

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Student student =
                new Student("Student");

        Attempt attempt =
                student.startExamination(exam);

        attempt.answer(q1, "A");
        attempt.answer(q2, "C");

        showConfirmation(attempt);

        // This answer cannot be changed now.
        attempt.answer(q2, "B");

        // This also demonstrates one attempt per
        // student for this examination.
        student.startExamination(exam);
    }
}