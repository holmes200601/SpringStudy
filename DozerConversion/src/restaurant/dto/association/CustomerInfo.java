package restaurant.dto.association;

import restaurant.dto.embeded.PersonNameInfo;

public class CustomerInfo {
    private PersonNameInfo name;

    public PersonNameInfo getName() {
        return name;
    }

    public void setName(PersonNameInfo name) {
        this.name = name;
    }

}
