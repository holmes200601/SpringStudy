package sampson.jdbcdao;

import java.math.BigDecimal;

public class Employee extends Person {
    @Property
    private String employeeCode;
    
    @Property
    private BigDecimal salary;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    
}
