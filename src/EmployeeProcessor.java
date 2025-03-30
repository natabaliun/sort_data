import java.io.*;
import java.util.*;

public class EmployeeProcessor {

    private List<Employee> employees;

    public EmployeeProcessor() {
        employees = new ArrayList<>();
    }

    public void readFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1].trim());
                    String department = parts[2];
                    double salary = Double.parseDouble(parts[3].trim());
                    employees.add(new Employee(name, age, department, salary));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void sortEmployeesBySalary() {
        employees.sort(Comparator.comparingDouble(Employee::getSalary));
    }

    public List<Employee> filterEmployeesByDepartment(String department) {
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getDepartment().equalsIgnoreCase(department)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public void printEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Employee employee : employees) {
                bw.write(employee.getName() + "," + employee.getAge() + "," + employee.getDepartment() + "," + employee.getSalary());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}