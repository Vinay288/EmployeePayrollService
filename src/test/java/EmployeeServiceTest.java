import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class EmployeeServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        Employee[] arrayOfEmployees= {
                new Employee(1,"Jeff Bezos"),
                new Employee(2,"Bill Gates"),
                new Employee(3,"Mark Zuckerberg")
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
