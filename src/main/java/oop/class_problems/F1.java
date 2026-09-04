package oop.class_problems;

class AccessRuleEngine {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED"
                    : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED"
                    : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED"
                    : "DENIED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            if (attempt == null || attempt.length != 2) {
                denied++;
                continue;
            }

            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    PatientRecord(
            String patientId,
            String wardCode,
            double vitalsScore,
            String facilityName) {

        if (patientId == null
                || patientId.trim().isEmpty()
                || patientId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid patient ID"
            );
        }

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class F1 {

    public static void main(String[] args) {

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "private",
                        "SAME_CLASS"
                )
        );

        System.out.println(
                AccessRuleEngine.classifyAccess(
                        "default",
                        "DIFFERENT_PACKAGE"
                )
        );

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessRuleEngine.summarizeBatch(attempts)
        );

        try {
            new PatientRecord(
                    "MT9",
                    "W3",
                    98.2,
                    "MediTrack Central"
            );

            System.out.println("PatientRecord created");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        try {
            new PatientRecord(
                    "MT94",
                    "W3",
                    98.2,
                    "MediTrack Central"
            );

            System.out.println("MT94 construction accepted");

        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }
    }
}