public class Payroll {
    private final Integer employeeId;
    private final double basicPay;
    private final double deductions;
    private final double taxablePay;
    private final double incomeTax;
    private final double netPay;

    Payroll(Integer employeeId,double basicPay,double deductions,double taxablePay,double incomeTax,double netPay){
        this.employeeId=employeeId;
        this.basicPay=basicPay;
        this.deductions=deductions;
        this.taxablePay=taxablePay;
        this.incomeTax=incomeTax;
        this.netPay=netPay;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "employeeId=" + employeeId +
                ", basicPay=" + basicPay +
                ", deductions=" + deductions +
                ", taxablePay=" + taxablePay +
                ", incomeTax=" + incomeTax +
                ", netPay=" + netPay +
                '}';
    }
}
