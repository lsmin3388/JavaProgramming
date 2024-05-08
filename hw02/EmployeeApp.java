import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 직원 관리 프로그램
 * @date 2024-04-10
 * @author 이상민
 * @classNumber 2023012780
 */
public class EmployeeApp {
    public static void main(String[] args) {
        // 직원 정보 생성
        List<Employee> employees = getEmployees();

        // 메뉴 출력
        do {
            System.out.println("========================================");
            System.out.println("1. Display all positions");
            System.out.println("2. Display all salaries");
            System.out.println("3. Display all information");
            System.out.println("0. Quit");
            System.out.println("========================================");

            System.out.print("-> ");
            Scanner scanner = new Scanner(System.in);

            int choice = scanner.nextInt();
            // todo: 숫자가 아닌 문자열 입력 시 예외 처리

            switch (choice) {
                case 1 -> printPosition(employees);
                case 2 -> printSalary(employees);
                case 3 -> printEmployee(employees);
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.err.println("Wrong number. Try again!");
            }

        } while (true);
    }


    /**
     * 직원 정보를 생성하는 메소드
     * @return 직원 정보 리스트
     */
    private static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();

        Employee alan = new Employee();
        alan.setName("Alan", "Cooper");
        alan.setPosition("Senior Engineer");
        alan.setAge(34);
        alan.setSalary(6500000);
        employees.add(alan);

        Employee brendan = new Employee();
        brendan.setName("Brendan", "Eich");
        brendan.setPosition("Junior Engineer");
        brendan.setAge(26);
        brendan.setSalary(5000000);
        employees.add(brendan);

        Employee dennis = new Employee();
        dennis.setName("Dennis", "Richie");
        dennis.setPosition("Chief Engineer");
        dennis.setAge(38);
        dennis.setSalary(7800000);
        employees.add(dennis);

        Employee larry = new Employee();
        larry.setName("Larry", "Wall");
        larry.setPosition("Team Leader");
        larry.setAge(42);
        larry.setSalary(8200000);
        employees.add(larry);

        Employee richard = new Employee();
        richard.setName("Richard", "Stallman");
        richard.setPosition("Project Manager");
        richard.setAge(46);
        richard.setSalary(9000000);
        employees.add(richard);
        return employees;
    }

    /**
     * 직원의 직책을 출력하는 메소드
     * @param employees 직원 정보 리스트
     */
    private static void printPosition(List<Employee> employees) {
        System.out.println("----------------------------------------");
        System.out.printf("%-17s   %s\n", "Name", "Position");
        System.out.println("----------------------------------------");

        for (Employee employee : employees) {
            System.out.printf("%-17s : %s\n", employee.getName(), employee.getPosition());
        }
    }

    /**
     * 직원의 급여를 출력하는 메소드
     * @param employees 직원 정보 리스트
     */
    private static void printSalary(List<Employee> employees) {
        System.out.println("----------------------------------------");
        System.out.printf("%-17s   %s\n", "Name", "Salary");
        System.out.println("----------------------------------------");

        for (Employee employee : employees) {
            System.out.printf("%-17s : %,d\n", employee.getName(), employee.getSalary());
        }
    }

    /**
     * 직원의 모든 정보를 출력하는 메소드
     * @param employees 직원 정보 리스트
     */
    private static void printEmployee(List<Employee> employees) {
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-17s %4s %17s %10s\n", "Name", "Age", "Position", "Salary");
        System.out.println("---------------------------------------------------------------");

        for (Employee employee : employees) {
            employee.printEmployee();
        }
    }
}