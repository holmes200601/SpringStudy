package restaurant.common.bean.component;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import restaurant.utils.TimeUtils;

@Embeddable
@Access(AccessType.PROPERTY)
public class PersonalDetail {
    private String idNum;
    private PersonName name;
    private Address address;
    private ContactDetail contactInfo;
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
    public ContactDetail getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactDetail contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Temporal(TemporalType.DATE)
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

        Calendar cal = TimeUtils.getCurrentCalendar();
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
