package restaurant.dto.association;

import restaurant.dozer.custom.mapper.CustomMapping;

public class EmployeeInfo extends AssociationInfo {
	@CustomMapping(value="employeeNumber")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
