package oop.assigment_problems;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        return daysOverdue * 5;
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}

public class F1BookIssue {
    public static void main(String[] args) {

        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Meera", 0),
            new BookIssue("DSA Handbook", "Karan", 21),
            new BookIssue("Design Patterns", "Divya", 9)
        };

        for (BookIssue issue : issues) {
            String status = "OK";

            if (issue.isSeverelyOverdue()) {
                status = "Severely overdue";
            }

            System.out.println(issue.title + " - " +
                    issue.daysOverdue + " days - " + status);
        }

        System.out.println("Total fine collected: Rs " +
                BookIssue.totalFineCollected(issues));
    }
}