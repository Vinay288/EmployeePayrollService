import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EmployeePayrollServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayroll[] arrayOfEmployees= {
                new EmployeePayroll(1,"Jeff Bezos",10000.0),
                new EmployeePayroll(2,"Bill Gates", 5000.0),
                new EmployeePayroll(3,"Mark Zuckerberg", 3000.0)
        };
        EmployeePayrollService employeePayService=new EmployeePayrollService();
        employeePayService=(EmployeePayrollService) Arrays.asList(arrayOfEmployees);
        employeePayService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assertions.assertEquals(3, entries);
    }
    @Test public void givenFilesOnReadingFromFilesShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService=new EmployeePayrollService();
        long entries=employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
    }
}
