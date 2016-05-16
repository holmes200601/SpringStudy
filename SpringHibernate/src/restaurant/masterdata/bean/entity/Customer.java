package restaurant.masterdata.bean.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import restaurant.common.bean.component.Address;
import restaurant.common.bean.component.ContactDetail;
import restaurant.common.bean.component.PersonName;
import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(value = AccessType.FIELD)
public class Customer extends ApplicationBean {

    private static final long serialVersionUID = 5739105794030439755L;

    @Id
    @GeneratedValue(generator = "CustomerSeq")
    @SequenceGenerator(name = "CustomerSeq", allocationSize = 1)
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private ContactDetail contactInfo;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private Long membershipPoint;

    private Date birthDay;

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public ContactDetail getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactDetail contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getMembershipPoint() {
        return membershipPoint;
    }

    public void setMembershipPoint(Long membershipPoint) {
        this.membershipPoint = membershipPoint;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

}
