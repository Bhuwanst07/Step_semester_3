package oop.assignment_problems;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }

    void printMember() {
        System.out.println(name);
    }
}

class LibraryMember {

    // These should NOT be static because every member has separate data.
    String name;
    String memberId;
    int booksIssued;

    // These are static because they are shared by all members.
    static String libraryName = "Central Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class F4LibraryMember {

    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        member1.printMember();
        member2.printMember();

        System.out.println("\nFixed version:");

        LibraryMember member3 =
                new LibraryMember("Aditi", 2);

        LibraryMember member4 =
                new LibraryMember("Rohan", 3);

        member3.printMemberCard();
        member4.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}