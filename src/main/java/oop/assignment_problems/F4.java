package oop.assignment_problems;

class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        this(null, null);
    }

    public LibraryMemberBean(String name) {
        this(null, name);
    }

    public LibraryMemberBean(
            String membershipId,
            String name) {

        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
        this.securityAnswer = null;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {

        if (answer == null) {
            throw new IllegalArgumentException(
                    "Security answer cannot be null"
            );
        }

        // Deterministic one-way transformation.
        this.securityAnswer =
                Integer.toHexString(answer.hashCode());
    }
}

public class F4 {

    public static void main(String[] args) {

        LibraryMemberBean m1 =
                new LibraryMemberBean("Priya Nair");

        System.out.println(
                m1.getMembershipId()
        );

        LibraryMemberBean m2 =
                new LibraryMemberBean(
                        "LIB-8841",
                        "Priya Nair"
                );

        System.out.println(
                m2.getMembershipId()
        );

        LibraryMemberBean m3 =
                new LibraryMemberBean();

        m3.setMembershipId("LIB-8841");
        m3.setMembershipId("FAKE-0000");

        System.out.println(
                m3.getMembershipId()
        );

        m3.setPremiumMember(true);

        System.out.println(
                m3.isPremiumMember()
        );

        m3.setSecurityAnswer("blue");

        System.out.println(
                "Security answer set."
        );
    }
}