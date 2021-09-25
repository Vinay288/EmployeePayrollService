import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeePayrollDBService {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service_db?useSSL=false";
        String userName = "root";
        String password = "1234";
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
            System.out.println("Connecting to database"+ jdbcURL);
            connection= DriverManager.getConnection(jdbcURL,userName,password);
            System.out.println("Connection is successfull"+ connection);
        } catch (Exception e) {
            throw new IllegalStateException("lol", e);
        }

    }
}

