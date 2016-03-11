package restaurant.bean.common;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

@Embeddable
@Access(AccessType.PROPERTY)
public class PersonalInfo {
    private String idNum;
    private PersonName name;
    private Address address;
    private ContactInfo contactInfo;
    private Date birthDay;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Embedded
    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded   
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    @Transient
    public Long getAge() {
        if (getBirthDay() == null) {
            return 0L;
        }

        Calendar cal = Calendar.getInstance();
        int currYear = cal.get(Calendar.YEAR);
        int currDay = cal.get(Calendar.DAY_OF_YEAR);
        cal.setTime(getBirthDay());
        int birthYear = cal.get(Calendar.YEAR);
        int birthDay = cal.get(Calendar.DAY_OF_YEAR);

        return Long.valueOf((currDay >= birthDay) ? (currYear - birthYear) : (currYear - birthYear - 1));
    }

    public void setAge(Long age) {
        // Do nothing for virtual property
    }

}
