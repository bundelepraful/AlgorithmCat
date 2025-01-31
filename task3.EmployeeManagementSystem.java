import java.util.*;

public class EmployeeManagementSystem {

    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary;
        }
    }

    static class EmployeeManager {
        private final Map<Integer, Employee> employees = new HashMap<>();

        public void addEmployee(Employee employee) {
            employees.put(employee.getId(), employee);
            System.out.println("Employee added successfully.");
        }

        public void updateEmployee(int id, String name, String department, double salary) {
            Employee employee = employees.get(id);
            if (employee != null) {
                employee.setName(name);
                employee.setDepartment(department);
                employee.setSalary(salary);
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        }

        public void deleteEmployee(int id) {
            if (employees.remove(id) != null) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        }

        public void listEmployees() {
            if (employees.isEmpty()) {
                System.out.println("No employees to display.");
            } else {
                for (Employee employee : employees.values()) {
                    System.out.println(employee);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();

        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. List Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Department: ");
                String department = scanner.nextLine();
                System.out.print("Enter Salary: ");
                double salary = scanner.nextDouble();
                manager.addEmployee(new Employee(id, name, department, salary));
            } else if (choice == 2) {
                System.out.print("Enter ID of employee to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter new Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new Department: ");
                String department = scanner.nextLine();
                System.out.print("Enter new Salary: ");
                double salary = scanner.nextDouble();
                manager.updateEmployee(id, name, department, salary);
            } else if (choice == 3) {
                System.out.print("Enter ID of employee to delete: ");
                int id = scanner.nextInt();
                manager.deleteEmployee(id);
            } else if (choice == 4) {
                manager.listEmployees();
            } else if (choice == 5) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
