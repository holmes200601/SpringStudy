package restaurant.dto.association;

import restaurant.dto.embeded.PersonNameInfo;

public class SupplierInfo extends AssociationInfo {
    private PersonNameInfo name;

    public PersonNameInfo getName() {
        return name;
    }

    public void setName(PersonNameInfo name) {
        this.name = name;
    }

}
