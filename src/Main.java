import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeProcessor processor = new EmployeeProcessor();
        processor.readFromFile("employees.txt"); // Файл с данными

        System.out.println("Исходные данные:");
        processor.printEmployees();

        processor.sortEmployeesBySalary();
        System.out.println("\nСотрудники, отсортированные по зарплате:");
        processor.printEmployees();

        List<Employee> itEmployees = processor.filterEmployeesByDepartment("IT");
        System.out.println("\nСотрудники из IT отдела:");
        for (Employee employee : itEmployees) {
            System.out.println(employee);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nВведите данные нового сотрудника (имя, возраст, отдел, зарплата через запятую):");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        if (parts.length == 4) {
            String name = parts[0];
            int age = Integer.parseInt(parts[1].trim());
            String department = parts[2];
            double salary = Double.parseDouble(parts[3].trim());
            processor.addEmployee(new Employee(name, age, department, salary));
            processor.saveToFile("employees.txt");
            System.out.println("Данные сохранены в файл.");
        } else {
            System.out.println("Некорректный ввод.");
        }
    }
}

