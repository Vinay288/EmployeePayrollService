import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EmployeeServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        Employee[] arrayOfEmployees = {
                new Employee(1, "Jeff Bezos"),
                new Employee(2, "Bill Gates"),
                new Employee(3, "Mark Zuckerberg")
        };
        EmployeePayrollService employeePayService = new EmployeePayrollService();
        employeePayService = (EmployeePayrollService) Arrays.asList(arrayOfEmployees);
        employeePayService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void givenFilesOnReadingFromFilesShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
    }

    @Test
    public void readFromEmployeePayroll_readAllEmployee() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long size = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO);
        Assertions.assertEquals(2, size);
    }

    @Test
    public void updateBasicPayInPayroll_CheckIfUpdated() {
        try {
            EmployeePayrollService employeePayrollService = new EmployeePayrollService();
            Payroll actualPayroll = employeePayrollService.updatePayroll();
            Payroll expectedPayroll = new Payroll(2, 30000000, 600, 500, 250, 7000);
            Assertions.assertEquals(expectedPayroll.toString(), actualPayroll.toString());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

}
