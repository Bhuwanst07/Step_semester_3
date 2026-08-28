package oop.assigment_problems;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class F2Employee {
    public static void main(String[] args) {

        Employee plain = new Employee(101, "Rahul", 40000);
        Employee manager = new ManagerEmployee(102, "Priya", 70000, 8000);
        Employee intern = new InternEmployee(103, "Aman", 12000, 10000);

        System.out.println("Plain employee pay: Rs " + plain.getSalary());

        if (manager instanceof ManagerEmployee) {
            ManagerEmployee m = (ManagerEmployee) manager;
            System.out.println("Manager effective pay: Rs " + m.effectiveSalary());
        }

        if (intern instanceof InternEmployee) {
            InternEmployee i = (InternEmployee) intern;
            System.out.println("Intern effective pay: Rs " + i.effectiveSalary());
        }
    }
}