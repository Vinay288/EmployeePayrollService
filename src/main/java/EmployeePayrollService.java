import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    private List<EmployeePayroll> employeePayrollList;
    public enum IOService{CONSOLE_IO, FILE_IO, DB_IO, REST_IO};
    public EmployeePayrollService() {
        this.employeePayrollList = new ArrayList<EmployeePayroll>();
    }
    private void writeEmployeePayrollData() {
        System.out.println("Writing employee data \n"+ employeePayrollList);
    }
    private void writeEmployeePayrollData(EmployeePayrollService.IOService ioservice) {
        if(ioservice==IOService.CONSOLE_IO)
            System.out.println("Writing employee data \n"+ employeePayrollList);
        else if(ioservice==IOService.FILE_IO)
            new EmployeePayrollFileIOService().writeData(employeePayrollList);

    }
    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter employee name");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary");
        Double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayroll(id, name, salary));
    }

    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData();
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);

    }
}
