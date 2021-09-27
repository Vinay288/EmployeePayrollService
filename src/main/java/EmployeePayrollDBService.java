import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    private PreparedStatement preparedStatementForEmployeeData;
    private PreparedStatement updatePayrollStatement;
    private static EmployeePayrollDBService employeePayrollDBService;
    List<Employee> employeeList;

    private EmployeePayrollDBService() {

    }

    public static EmployeePayrollDBService getDBServiceInstance() {
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();
        return employeePayrollDBService;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service_db?useSSL=false";
        String userName = "root";
        String password = "1234";
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        return connection;
    }

    public List<Employee> readEmployeeDataFromDB(String name) {
        if (preparedStatementForEmployeeData == null) {
            this.preparedStatementToReadEmployeeData();
        }
        try {
            preparedStatementForEmployeeData.setString(1, name);
            ResultSet resultSet = preparedStatementForEmployeeData.executeQuery();
            employeeList = this.getEmployeeDataList(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private List<Employee> getEmployeeDataList(ResultSet resultSet) {
        employeeList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                employeeList.add(new Employee(resultSet.getInt("employee_id"), resultSet.getString("employe_name"), resultSet.getString("gender"), resultSet.getString("address"), resultSet.getLong("phone_number"), resultSet.getDate("start_date").toLocalDate(), new Payroll(resultSet.getInt("employee_id"), resultSet.getDouble("basic_pay"), resultSet.getDouble("deductions"), resultSet.getDouble("taxable_pay"), resultSet.getDouble("income_tax"), resultSet.getDouble("net_pay")), new Company(resultSet.getInt("company_id"), resultSet.getString("company_name"))));
            }
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
        return employeeList;
    }

    private void preparedStatementToReadEmployeeData() {
        try (Connection connection = this.getConnection()) {
            String query = "select * from employee e, payroll p,company c where e.employee_id=p.employee_id and e.company_id=c.company_id and employe_name= ?";
            preparedStatementForEmployeeData = connection.prepareStatement(query);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    private void preparedStatementToUpdatePayroll() {
        try (Connection connection = this.getConnection()) {
            String updateQuery = "update payroll set basic_pay=? where employee_id in (select employee_id from employee where employe_name=?)";
            updatePayrollStatement = connection.prepareStatement(updateQuery);
        } catch (Exception e) {
            throw new DBException(e.getMessage());
        }
    }

    public void updatePayroll(String name, Double basicPay) {
        if (updatePayrollStatement == null) {
            this.preparedStatementToUpdatePayroll();
        }
        try (Connection connection = this.getConnection()) {
            updatePayrollStatement.setDouble(1, basicPay);
            updatePayrollStatement.setString(2, name);
            updatePayrollStatement.executeUpdate();
        } catch (Exception e) {

            throw new DBException(e.getMessage());
        }
    }


}

