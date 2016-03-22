package restaurant.bean.common;

import javax.persistence.Embeddable;

@Embeddable
public class PersonName {
    private String firstName;
    private String middleName;
    private String lastName;

    public PersonName(String f, String m, String l) {
        this.firstName = f;
        this.middleName = m;
        this.lastName = l;
    }

    public PersonName() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
