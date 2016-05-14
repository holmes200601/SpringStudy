package restaurant.ro.basic;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import restaurant.dozer.custom.mapper.CustomMapping;
import restaurant.dto.association.SalaryRuleInfo;
import restaurant.dto.embeded.PersonalInfo;

public class EmployeeRO {
    private Long id;
    private String employeeNumber;
    private PersonalInfo selfInfo;
    @CustomMapping(value = "emergencyContactPerson")
    private PersonalInfo emergencyContact;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date onBoardDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;
    private String leaveReason;      // Need custome enum converter
    @CustomMapping(value = "startWorkingTime")
    private String dailyWorkingFrom;
    @CustomMapping(value = "endWorkingTime")
    private String dailyWorkingTo;
    private List<SalaryRuleInfo> salaryRules;

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

    public PersonalInfo getSelfInfo() {
        return selfInfo;
    }

    public void setSelfInfo(PersonalInfo selfInfo) {
        this.selfInfo = selfInfo;
    }

    public PersonalInfo getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(PersonalInfo emergencyContact) {
        this.emergencyContact = emergencyContact;
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

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getDailyWorkingFrom() {
        return dailyWorkingFrom;
    }

    public void setDailyWorkingFrom(String dailyWorkingFrom) {
        this.dailyWorkingFrom = dailyWorkingFrom;
    }

    public String getDailyWorkingTo() {
        return dailyWorkingTo;
    }

    public void setDailyWorkingTo(String dailyWorkingTo) {
        this.dailyWorkingTo = dailyWorkingTo;
    }

    public List<SalaryRuleInfo> getSalaryRules() {
        return salaryRules;
    }

    public void setSalaryRules(List<SalaryRuleInfo> salaryRules) {
        this.salaryRules = salaryRules;
    }

}
