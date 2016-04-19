package restaurant.srv.basic;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import restaurant.basic.bean.entity.Employee;
import restaurant.basic.bean.entity.SalaryRule;
import restaurant.frw.srv.BasicService;

@Service
public class EmployeeService extends BasicService{
    public BigDecimal calculateEmployeeSalary(Long employeeId, Date from, Date to) {
        BigDecimal result = BigDecimal.ZERO;
        
        if (employeeId == null) {
            // throw exception here
        }
        
        Employee employee = getBeanFacade().loadBean(Employee.class, employeeId);
        result = calculateEmployeeSalary(employee, from, to);
        
        return result;
    }
    
    public BigDecimal calculateEmployeeSalary(Employee employee, Date from, Date to) {
        BigDecimal result = BigDecimal.ZERO;
        
        if (employee == null) {
            // throw exception here
        }
        
        for (SalaryRule rule : employee.getSalaryRules()) {
            result = result.add(rule.calculateSalary(from, to));
        }
        
        return result;
    }
    
}
