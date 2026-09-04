package oop.assignment_problems;

import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("Loan ledger initialized.");
    }

    public LoanReceipt(
            String memberId,
            String[] bookIds) {

        if (memberId == null) {
            throw new IllegalArgumentException(
                    "Invalid member ID"
            );
        }

        if (bookIds == null) {
            throw new IllegalArgumentException(
                    "Book IDs cannot be null"
            );
        }

        for (String bookId : bookIds) {

            if (bookId == null
                    || !bookId.matches("BK-\\d{3}")) {

                throw new IllegalArgumentException(
                        "Invalid book ID"
                );
            }
        }

        this.memberId = memberId;

        this.bookIds =
                Arrays.copyOf(
                        bookIds,
                        bookIds.length
                );
    }

    public String[] getBookIds() {

        return Arrays.copyOf(
                bookIds,
                bookIds.length
        );
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0
                || index >= bookIds.length) {

            throw new IndexOutOfBoundsException(
                    "Invalid book ID index"
            );
        }

        if (newId == null
                || !newId.matches("BK-\\d{3}")) {

            throw new IllegalArgumentException(
                    "Invalid book ID"
            );
        }

        String[] corrected =
                getBookIds();

        corrected[index] = newId;

        return new LoanReceipt(
                memberId,
                corrected
        );
    }
}

class ReferenceOnlyLoanReceipt
        extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class F5 {

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + referenceOnly
                + " reference-only | "
                + regular
                + " regular";
    }

    public static void main(String[] args) {

        try {
            new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"}
            );

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "construction rejected"
            );
        }

        LoanReceipt r =
                new LoanReceipt(
                        "LIB-8841",
                        new String[]{"BK-100", "BK-101"}
                );

        String[] ids =
                r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
                r.getBookIds()[0]
        );

        LoanReceipt corrected =
                r.withCorrectedBookId(
                        0,
                        "BK-102"
                );

        System.out.println(
                corrected.getBookIds()[0]
        );

        LoanReceipt[] receipts = {
            new ReferenceOnlyLoanReceipt(
                    "LIB-001",
                    new String[]{"BK-200"},
                    "Reading Room 3"
            ),
            null,
            new LoanReceipt(
                    "LIB-002",
                    new String[]{"BK-201"}
            )
        };

        System.out.println(
                processNightlyCirculation(receipts)
        );
    }
}