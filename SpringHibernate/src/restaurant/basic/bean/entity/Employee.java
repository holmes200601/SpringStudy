package restaurant.basic.bean.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import restaurant.common.bean.component.DayTime;
import restaurant.common.bean.component.PersonalDetail;
import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class Employee extends ApplicationBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2908683198179239992L;

	public enum LeaveReasonEnum {
		FIRED, RUN_AWAY, FAMILY_ISSUE, LAW_BROKEN, LOW_SALARY, OTHER
	}

	@Id
	@GeneratedValue(generator = "EmployeeSeq")
	@SequenceGenerator(name = "EmployeeSeq", allocationSize = 1)
	private Long id;

	@Column(unique = true)
	private String employeeNumber;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "idNum", column = @Column(name = "self_idNumber")),
			@AttributeOverride(name = "name.firstName", column = @Column(name = "self_firstName")),
			@AttributeOverride(name = "name.middleName", column = @Column(name = "self_middleName")),
			@AttributeOverride(name = "name.lastName", column = @Column(name = "self_lastName")),
			@AttributeOverride(name = "address.country", column = @Column(name = "self_country")),
			@AttributeOverride(name = "address.provinces", column = @Column(name = "self_provinces")),
			@AttributeOverride(name = "address.state", column = @Column(name = "self_state")),
			@AttributeOverride(name = "address.street", column = @Column(name = "self_street")),
			@AttributeOverride(name = "address.postCode", column = @Column(name = "self_postCode")),
			@AttributeOverride(name = "contactInfo.cellPhone", column = @Column(name = "self_cellPhone")),
			@AttributeOverride(name = "contactInfo.wechatNum", column = @Column(name = "self_wechatNum")),
			@AttributeOverride(name = "contactInfo.qqNum", column = @Column(name = "self_qqNum")),
			@AttributeOverride(name = "contactInfo.email", column = @Column(name = "self_email")),
			@AttributeOverride(name = "birthDay", column = @Column(name = "self_birthDay")) })
	private PersonalDetail selfInfo;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "idNum", column = @Column(name = "emrg_idNumber")),
			@AttributeOverride(name = "name.firstName", column = @Column(name = "emrg_firstName")),
			@AttributeOverride(name = "name.middleName", column = @Column(name = "emrg_middleName")),
			@AttributeOverride(name = "name.lastName", column = @Column(name = "emrg_lastName")),
			@AttributeOverride(name = "address.country", column = @Column(name = "emrg_country")),
			@AttributeOverride(name = "address.provinces", column = @Column(name = "emrg_provinces")),
			@AttributeOverride(name = "address.state", column = @Column(name = "emrg_state")),
			@AttributeOverride(name = "address.street", column = @Column(name = "emrg_street")),
			@AttributeOverride(name = "address.postCode", column = @Column(name = "emrg_postCode")),
			@AttributeOverride(name = "contactInfo.cellPhone", column = @Column(name = "emrg_cellPhone")),
			@AttributeOverride(name = "contactInfo.wechatNum", column = @Column(name = "emrg_wechatNum")),
			@AttributeOverride(name = "contactInfo.qqNum", column = @Column(name = "emrg_qqNum")),
			@AttributeOverride(name = "contactInfo.email", column = @Column(name = "emrg_email")),
			@AttributeOverride(name = "birthDay", column = @Column(name = "emrg_birthDay")) })
	private PersonalDetail emergencyContactPerson;

	@Temporal(TemporalType.DATE)
	private Date onBoardDate;

	@Temporal(TemporalType.DATE)
	private Date leaveDate;
	private LeaveReasonEnum leaveReason;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "hour", column = @Column(name = "startWorkingHour_hour")),
			@AttributeOverride(name = "minite", column = @Column(name = "startWorkingMinite_minite")) })
	private DayTime startWorkingTime;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "hour", column = @Column(name = "endWorkingTime_hour")),
			@AttributeOverride(name = "minite", column = @Column(name = "endWorkingTime_minite")) })
	private DayTime endWorkingTime;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ownerEmployee")
	private List<SalaryRule> salaryRules = new ArrayList<SalaryRule>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public PersonalDetail getSelfInfo() {
		return selfInfo;
	}

	public void setSelfInfo(PersonalDetail selfInfo) {
		this.selfInfo = selfInfo;
	}

	public PersonalDetail getEmergencyContactPerson() {
		return emergencyContactPerson;
	}

	public void setEmergencyContactPerson(PersonalDetail emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}

	public Date getOnBoardDate() {
		return onBoardDate;
	}

	public void setOnBoardDate(Date onBoardDate) {
		this.onBoardDate = onBoardDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public LeaveReasonEnum getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(LeaveReasonEnum leaveReason) {
		this.leaveReason = leaveReason;
	}

	public List<SalaryRule> getSalaryRules() {
		return salaryRules;
	}

	public void setSalaryRules(List<SalaryRule> salaryRules) {
		this.salaryRules = salaryRules;
	}

	public DayTime getStartWorkingTime() {
		return startWorkingTime;
	}

	public void setStartWorkingTime(DayTime startWorkingTime) {
		this.startWorkingTime = startWorkingTime;
	}

	public DayTime getEndWorkingTime() {
		return endWorkingTime;
	}

	public void setEndWorkingTime(DayTime endWorkingTime) {
		this.endWorkingTime = endWorkingTime;
	}

}
