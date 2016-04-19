package restaurant.dto.association;

import org.dozer.Mapping;

public class EmployeeInfo extends AssociationInfo {
    @Mapping("employeeNumber")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
