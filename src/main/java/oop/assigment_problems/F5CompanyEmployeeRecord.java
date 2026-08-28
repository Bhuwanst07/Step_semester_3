package oop.assignment_problems;

public class F5CompanyEmployeeRecord {

    static class Employee {
        private int empId;
        private String empName;
        private double salary;

        Employee(int empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        public double getSalary() {
            return salary;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        public double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class ParkingSlot {
        private String slotNo;
        private int capacity;
        private int occupiedCount;

        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupiedCount = occupiedCount;
        }

        public String getSlotNo() {
            return slotNo;
        }
    }

    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    static int totalRecords = 0;

    F5CompanyEmployeeRecord(String name, String empId,
                            Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotInfo;

        if (slot == null) {
            slotInfo = "no parking assigned";
        } else {
            slotInfo = slot.getSlotNo();
        }

        return name + " | Pay: Rs " + pay + " | Slot: " + slotInfo;
    }

    public static void main(String[] args) {

        Employee divyaEmployee =
                new ManagerEmployee(101, "Divya", 70000, 8000);

        Employee karanEmployee =
                new Employee(102, "Karan", 40000);

        Employee meeraEmployee =
                new Employee(103, "Meera", 10000);

        ParkingSlot slotA1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slotA2 = new ParkingSlot("A2", 5, 4);

        F5CompanyEmployeeRecord record1 =
                new F5CompanyEmployeeRecord(
                        "Divya",
                        "EMP-101",
                        divyaEmployee,
                        slotA1
                );

        F5CompanyEmployeeRecord record2 =
                new F5CompanyEmployeeRecord(
                        "Karan",
                        "EMP-102",
                        karanEmployee,
                        slotA2
                );

        F5CompanyEmployeeRecord record3 =
                new F5CompanyEmployeeRecord(
                        "Meera",
                        "EMP-103",
                        meeraEmployee,
                        null
                );

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: " + totalRecords);
    }
}