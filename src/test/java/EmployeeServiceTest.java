import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EmployeeServiceTest {
    @Test
    public void given3EmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        Employee[] arrayOfEmployees = {
                new Employee(1, "asdf"),
                new Employee(2, "qwerty"),
                new Employee(3, "zxcv")
        };
        EmployeePayrollService employeePayService;
        employeePayService = (EmployeePayrollService) Arrays.asList(arrayOfEmployees);
        employeePayService.writeEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO);
        long entries = employeePayService.countEntries(EmployeePayrollService.IOService.FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void givenFilesOnReadingFromFilesShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.FILE_IO).size();
    }

    @Test
    public void readFromEmployeePayroll_readGivenEmployee() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long size = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO, "vinay").size();
        Assertions.assertEquals(1, size);
    }

    @Test
    public void updateBasicPayInPayroll_CheckIfUpdated() {
        String name = "terissa";
        Double basicPay = 300.00;
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<Employee> employeeList = employeePayrollService.readEmployeePayrollData(EmployeePayrollService.IOService.DB_IO, name);
        employeePayrollService.updatePayroll(name, basicPay);
        boolean result = employeePayrollService.compareUpdateSync(name);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenDateRange_WhenCorrect_RetrieveAllEMployeeJoined() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        LocalDate startDate = LocalDate.of(2020, 4, 19);
        LocalDate endDate = LocalDate.of(2020, 6, 19);
        List<Employee> employeeList = employeePayrollService.readEmployeeJoinedInRange(startDate, endDate);
        Assertions.assertEquals(2, employeeList.size());
    }

    @Test
    public void givenMathFunction_WhenCorrect_RetrieveTheResult() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Double result = employeePayrollService.getMathValueForGivenMathFunction("sum", "F");
        System.out.println(result);
    }

    @Test
    public void givenNewEmployeePayrollData_WhenCorrect_InsertToEMployeeAndPayrollTable() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        Employee employee = new Employee(77, "vish", "F", "18th main road", 97845978L, LocalDate.of(2020, 8, 20), 123);
        Department department = new Department(1, "engineer");
        employee.setDepartmentList(List.of(department));
        Payroll updatedPayroll = employeePayrollService.insertEmployeePayrollValues(employee, 20000.00);
        boolean result = employeePayrollService.compareEmployeePayrollInsertSync(employee.getName(), updatedPayroll);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenEmployeeName_WhenCorrect_DeleteEmployeeBySettingIs_Actove_TO_False() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        boolean result = employeePayrollService.deleteEmployee("vish");
        Assertions.assertFalse(result);
    }
}
