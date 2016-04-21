package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.basic.bean.entity.Employee;
import restaurant.ro.basic.EmployeeRO;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends ControllerBase {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeRO getEmployee(@PathVariable Long id) {
        Employee loadEmployee = getBeanFacade().loadBean(Employee.class, id, true);
        EmployeeRO result = getDozerMapper().map(loadEmployee, EmployeeRO.class);

        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long createEmployee(@RequestBody EmployeeRO employee) {
        Employee employeeEntity = getDozerMapper().map(employee, Employee.class);
        Long result = getBeanFacade().saveBean(employeeEntity);

        return result;
    }
}
