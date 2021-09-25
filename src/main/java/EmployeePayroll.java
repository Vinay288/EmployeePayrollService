public class EmployeePayroll {
    public int id;
    public String name;
    public double salary;

    public EmployeePayroll(int id, String name, Double salary) {
        this.id=id;
        this.name=name;
        this.salary=salary;
    }

    public String toString() {
        return "id=" + id + ", name=\n"+ name + "\n" + "salary="+ salary;
    }
}
