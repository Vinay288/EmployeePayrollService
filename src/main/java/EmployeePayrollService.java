import java.sql.Date;
import java.sql.SQLData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    private List<Employee> employeeList;

    public List<Employee> readEmployeeJoinedInRange(LocalDate startDate,LocalDate endDate) {
        return EmployeePayrollDBService.getDBServiceInstance().readEmployedJoinedRange(Date.valueOf(startDate),Date.valueOf(endDate));
    }

    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    ;

    public EmployeePayrollService() {
        this.employeeList = new ArrayList<Employee>();
    }

    public void writeEmployeePayrollData() {
        System.out.println("Writing employee data \n" + employeeList);
    }

    public void writeEmployeePayrollData(EmployeePayrollService.IOService ioservice) {
        if (ioservice == IOService.CONSOLE_IO)
            System.out.println("Writing employee data \n" + employeeList);
        else if (ioservice == IOService.FILE_IO)
            new EmployeePayrollFileIOService().writeData(employeeList);

    }

    public void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter employee name");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary");
        Double salary = consoleInputReader.nextDouble();
        employeeList.add(new Employee(id, name));
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return 0;
    }

    public List<Employee> readEmployeePayrollData(IOService ioservice, String... name) {
        if (ioservice.equals(IOService.FILE_IO))
            this.employeeList = new EmployeePayrollFileIOService().readData();
        else if (ioservice.equals(IOService.DB_IO))
            this.employeeList = EmployeePayrollDBService.getDBServiceInstance().readEmployeeDataFromDB(name[0]);
        return employeeList;
    }

    public void updatePayroll(String name, Double basicPay) {
        EmployeePayrollDBService.getDBServiceInstance().employeeList.get(0).getPayroll().setBasicPay(basicPay);
        EmployeePayrollDBService.getDBServiceInstance().updatePayroll(name, basicPay);
    }

    public static void main(String[] args) {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData();
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
        employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
        employeePayrollService.readEmployeePayrollData(IOService.FILE_IO);

    }

    public boolean compareUpdateSync(String name) {
        List<Employee> employeeList = EmployeePayrollDBService.getDBServiceInstance().employeeList;
        return employeeList.get(0).getPayroll().toString().equals(readEmployeePayrollData(IOService.DB_IO, name).get(0).getPayroll().toString());
    }
    public double getMathValueForGivenMathFunction(String function,String gender){
        return EmployeePayrollDBService.getDBServiceInstance().getMathValueForGivenMathFunction(function,gender);
    }
}
