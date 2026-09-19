package oop.assignment_problems;

public class F2 {

    private static int totalExports = 0;

    public interface Exportable {
        String exportData();
    }

    public static class ReportGenerator
            implements Exportable {

        private final String reportName;

        public ReportGenerator(String reportName) {

            if (reportName == null
                    || reportName.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Report name cannot be blank"
                );
            }

            this.reportName = reportName;
        }

        @Override
        public String exportData() {

            totalExports++;

            return "Exported report: "
                    + reportName;
        }
    }

    public static class UserProfile
            implements Exportable {

        private final String username;

        public UserProfile(String username) {

            if (username == null
                    || username.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Username cannot be blank"
                );
            }

            this.username = username;
        }

        @Override
        public String exportData() {

            totalExports++;

            return "Exported profile: "
                    + username;
        }
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void exportAll(
            Exportable[] items) {

        for (Exportable item : items) {
            System.out.println(
                    item.exportData()
            );
        }
    }

    public static void main(String[] args) {

        ReportGenerator r =
                new ReportGenerator("Sales Q1");

        System.out.println(
                r.exportData()
        );

        UserProfile u =
                new UserProfile("jane_doe");

        System.out.println(
                u.exportData()
        );

        // Upcasting:
        // ReportGenerator stored as Exportable.
        Exportable ref = r;

        exportAll(
                new Exportable[]{
                    ref,
                    u
                }
        );

        System.out.println(
                getTotalExports()
        );
    }
}