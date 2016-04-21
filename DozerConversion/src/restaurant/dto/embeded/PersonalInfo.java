package restaurant.dto.embeded;

import java.util.Date;

import org.dozer.Mapping;

public class PersonalInfo extends EmbededInfo {
    @Mapping("idNum")
    private String idNumber;
    private PersonNameInfo name;
    private AddressInfo address;
    private ContactInfo contactInfo;
    private Date birthday;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public PersonNameInfo getName() {
        return name;
    }

    public void setName(PersonNameInfo name) {
        this.name = name;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
