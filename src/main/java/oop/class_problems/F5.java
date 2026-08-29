package oop.class_problems;

class MiniFeeAccount {
    private double totalFee;
    private double amountPaid;

    MiniFeeAccount(double totalFee, double amountPaid) {
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

class MiniHostelFeeAccount extends MiniFeeAccount {

    MiniHostelFeeAccount(double totalFee, double amountPaid) {
        super(totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}

class MiniHostelRoom {
    String roomNo;
    int beds;
    int occupied;

    MiniHostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
        }
    }
}

class SrmStudent {
    String name;
    String regNo;
    MiniHostelFeeAccount feeAccount;
    MiniHostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo,
               MiniHostelFeeAccount feeAccount,
               MiniHostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    String fullStatus() {

        String roomNumber;

        if (room == null) {
            roomNumber = "unallotted";
        } else {
            roomNumber = room.roomNo;
        }

        return name
                + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomNumber;
    }
}

public class F5 {
    public static void main(String[] args) {

        MiniHostelRoom room1 =
                new MiniHostelRoom("C-214", 3, 2);

        MiniHostelRoom room2 =
                new MiniHostelRoom("C-507", 2, 1);

        room1.allot("Ravi");
        room2.allot("Anitha");

        MiniHostelFeeAccount fee1 =
                new MiniHostelFeeAccount(200000, 60000);

        MiniHostelFeeAccount fee2 =
                new MiniHostelFeeAccount(200000, 20000);

        MiniHostelFeeAccount fee3 =
                new MiniHostelFeeAccount(200000, 0);

        fee1.pay(0);

        fee2.pay(-500);

        fee3.pay(0);

        SrmStudent student1 =
                new SrmStudent(
                        "Ravi", "RA101", fee1, room1);

        SrmStudent student2 =
                new SrmStudent(
                        "Anitha", "RA102", fee2, room2);

        SrmStudent student3 =
                new SrmStudent(
                        "Karthik", "RA103", fee3, null);

        System.out.println(student1.fullStatus());
        System.out.println(student2.fullStatus());
        System.out.println(student3.fullStatus());

        System.out.println(
                "Total students: " + SrmStudent.totalStudents
        );
    }
}