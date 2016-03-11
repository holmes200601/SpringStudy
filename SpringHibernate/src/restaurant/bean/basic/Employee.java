package restaurant.bean.basic;

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
import javax.persistence.Id;
import javax.persistence.OneToMany;

import restaurant.bean.common.DayTime;
import restaurant.bean.common.PersonalInfo;
import restaurant.frw.RestaurantBean;

@Entity
@Access(AccessType.PROPERTY)
public class Employee extends RestaurantBean {
    public enum LeaveReasonEnum {
        FIRED, RUN_AWAY, FAMILY_ISSUE, ROW_BROKEN, OTHER
    }

    private Long id;
    private String employeeNumber;
    private PersonalInfo selfInfo;
    private PersonalInfo emergencyContactPerson;
    private Date onBoardDate;
    private Date leaveDate;
    private LeaveReasonEnum leaveReason;
    private List<SalaryRule> salaryRules = new ArrayList<SalaryRule>();
    private DayTime startWorkingTime;
    private DayTime endWorkingTime;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "self_idNumber", column = @Column(name = "idNum") ),
            @AttributeOverride(name = "self_firstName", column = @Column(name = "firstName") ),
            @AttributeOverride(name = "self_middleName", column = @Column(name = "lastName") ),
            @AttributeOverride(name = "self_country", column = @Column(name = "country") ),
            @AttributeOverride(name = "self_provinces", column = @Column(name = "provinces") ),
            @AttributeOverride(name = "self_state", column = @Column(name = "state") ),
            @AttributeOverride(name = "self_street", column = @Column(name = "street") ),
            @AttributeOverride(name = "self_postCode", column = @Column(name = "postCode") ),
            @AttributeOverride(name = "self_cellPhone", column = @Column(name = "cellPhone") ),
            @AttributeOverride(name = "self_wechatNum", column = @Column(name = "wechatNum") ),
            @AttributeOverride(name = "self_qqNum", column = @Column(name = "qqNum") ),
            @AttributeOverride(name = "self_email", column = @Column(name = "email") ),
            @AttributeOverride(name = "self_birthDay", column = @Column(name = "birthDay") ) })
    public PersonalInfo getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(PersonalInfo selfInfo) {
        this.selfInfo = selfInfo;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "emrPerson_idNumber", column = @Column(name = "idNum") ),
            @AttributeOverride(name = "emrPerson_firstName", column = @Column(name = "firstName") ),
            @AttributeOverride(name = "emrPerson_middleName", column = @Column(name = "lastName") ),
            @AttributeOverride(name = "emrPerson_country", column = @Column(name = "country") ),
            @AttributeOverride(name = "emrPerson_provinces", column = @Column(name = "provinces") ),
            @AttributeOverride(name = "emrPerson_state", column = @Column(name = "state") ),
            @AttributeOverride(name = "emrPerson_street", column = @Column(name = "street") ),
            @AttributeOverride(name = "emrPerson_postCode", column = @Column(name = "postCode") ),
            @AttributeOverride(name = "emrPerson_cellPhone", column = @Column(name = "cellPhone") ),
            @AttributeOverride(name = "emrPerson_wechatNum", column = @Column(name = "wechatNum") ),
            @AttributeOverride(name = "emrPerson_qqNum", column = @Column(name = "qqNum") ),
            @AttributeOverride(name = "emrPerson_email", column = @Column(name = "email") ),
            @AttributeOverride(name = "emrPerson_birthDay", column = @Column(name = "birthDay") ) })
    public PersonalInfo getEmergencyContactPerson() {
        return emergencyContactPerson;
    }

    public void setEmergencyContactPerson(PersonalInfo emergencyContactPerson) {
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

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="appliedEmployee")
    public List<SalaryRule> getSalaryRules() {
        return salaryRules;
    }

    public void setSalaryRules(List<SalaryRule> salaryRules) {
        this.salaryRules = salaryRules;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "startWorkingHour", column = @Column(name = "hour") ),
            @AttributeOverride(name = "startWorkingMinite", column = @Column(name = "minite") ) })
    public DayTime getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(DayTime startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "startWorkingHour", column = @Column(name = "hour") ),
            @AttributeOverride(name = "startWorkingMinite", column = @Column(name = "minite") ) })
    public DayTime getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(DayTime endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

}
