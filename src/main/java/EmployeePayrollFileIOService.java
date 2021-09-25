import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollFileIOService {
    public static String PAYROLL_FILE_NAME = "payroll-file.txt";

    public void writeData(List<Employee> employeeList) {
        StringBuffer empBuffer = new StringBuffer();
        employeeList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });

        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public List<Employee> readData() {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath())
                    .map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
    public Payroll updatePayroll(){
        Payroll updatedpayroll;
        String query="select * from employee e,payroll p where p.employee_id=e.employee_id and e.employe_name=\"terissa\"";
        String updateQuery="update payroll set basic_pay=30000000 where employee_id in (select employee_id from employee where employe_name=\"terissa\")";
        try {
            Connection connection=EmployeePayrollDBService.getConnection();
            System.out.println("connected");
            Statement statement=connection.createStatement();
            statement.executeUpdate(updateQuery);
            ResultSet resultSet=statement.executeQuery(query);
            resultSet.next();
                updatedpayroll =new Payroll(resultSet.getInt("employee_id"), resultSet.getDouble("basic_pay"), resultSet.getDouble("deductions"), resultSet.getDouble("taxable_pay"), resultSet.getDouble("income_tax"), resultSet.getDouble("net_pay"));
            System.out.println(updatedpayroll);
        }catch (Exception e) {
            throw new DBException(e.getMessage());
        }
        return updatedpayroll;
    }

    public List<Employee> readDataFromDB() {
        List<Employee> employeeList = new ArrayList<Employee>();
        String query="select * from employee e,payroll p where p.employee_id=e.employee_id";
        try {
            Connection connection=EmployeePayrollDBService.getConnection();
            System.out.println("connected");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()) {
                employeeList.add(new Employee(resultSet.getInt("employee_id"), resultSet.getString("employe_name"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getLong("phone_number"), resultSet.getDate("start_date").toLocalDate(), resultSet.getInt("company_id")));
            }
            System.out.println(employeeList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  employeeList;
    }
}
