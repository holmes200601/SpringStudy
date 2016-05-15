package restaurant.ro.crm;

import java.util.Date;

import restaurant.dto.embeded.AddressInfo;
import restaurant.dto.embeded.ContactInfo;
import restaurant.dto.embeded.PersonNameInfo;

public class CustomerRO {
	private Long id;
	private PersonNameInfo name;
	private ContactInfo contactInfo;
	private AddressInfo address;
	private Long membershipPoint;
	private Date birthDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonNameInfo getName() {
		return name;
	}

	public void setName(PersonNameInfo name) {
		this.name = name;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

	public Long getMembershipPoint() {
		return membershipPoint;
	}

	public void setMembershipPoint(Long membershipPoint) {
		this.membershipPoint = membershipPoint;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
