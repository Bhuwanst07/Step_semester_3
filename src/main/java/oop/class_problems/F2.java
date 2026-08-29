package oop.class_problems;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment rejected.");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class ScholarshipFeeAccount extends FeeAccount {
    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {
        super(regNo, totalFee, amountPaid);
        this.scholarshipPercent = scholarshipPercent;
    }

    double effectiveDue() {
        return getDue() * (1 - scholarshipPercent / 100);
    }
}

public class F2 {
    public static void main(String[] args) {

        FeeAccount plain =
                new FeeAccount("R101", 150000, 100000);

        HostelFeeAccount hostel =
                new HostelFeeAccount("R102", 200000, 60000);

        ScholarshipFeeAccount scholarship =
                new ScholarshipFeeAccount("R103", 180000, 0, 20);

        plain.pay(50000);

        hostel.payInTwoInstallments(0);

        scholarship.pay(1000);
        scholarship.pay(-500);

        System.out.println(
                "Plain account due: Rs " + plain.getDue()
        );

        if (hostel instanceof HostelFeeAccount) {
            System.out.println(
                    "Hostel account due: Rs " + hostel.getDue()
            );
        }

        if (scholarship instanceof ScholarshipFeeAccount) {
            System.out.println(
                    "Scholarship account effective due: Rs "
                    + scholarship.effectiveDue()
            );
        }
    }
}