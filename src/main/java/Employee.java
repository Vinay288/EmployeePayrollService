import java.time.LocalDate;

public class Employee {
    private  Integer companyId;
    private String gender;
    private String address;
    private Long phoneNumber;
    private LocalDate startDate;
    public int id;
    public String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee(int id, String name, String gender, String address, Long phoneNumber, LocalDate startDate,Integer companyId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.companyId=companyId;

    }

    @Override
    public String toString() {
        return "Employee{" +
                "companyId=" + companyId +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", startDate=" + startDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
