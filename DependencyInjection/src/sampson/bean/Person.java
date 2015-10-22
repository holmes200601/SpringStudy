package sampson.bean;

import sampson.enumeration.Gender;

public abstract class Person {
	private String name;
	private String phone; // Need validation
	private Integer age;
	private Gender gender;
	
	public Person(String name, String phone, Integer age, Gender gender) {
		this.setName(name);
		this.setPhone(phone);
		this.setAge(age);
		this.setGender(gender);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}	
	
	
}
