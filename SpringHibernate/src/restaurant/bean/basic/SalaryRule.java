package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restaurant.frw.RestaurantBean;

@Entity
@Access(AccessType.FIELD)
public class SalaryRule extends RestaurantBean {
    
    @Id
    @GeneratedValue(generator="SalaryRuleSeq")
    @SequenceGenerator(name="SalaryRuleSeq", allocationSize=1)
    private Long id;
    
    private SalaryRuleTypeEnum ruleType;
    
    private BigDecimal amount;
    
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "ownerEmployeeId")
    private Employee owner;

    public enum SalaryRuleTypeEnum {
        FIXED, DEDUCED, AWARD, WORK_LOAD, TEMP_HOUR
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalaryRuleTypeEnum getRuleType() {
        return ruleType;
    }

    public void setRuleType(SalaryRuleTypeEnum ruleType) {
        this.ruleType = ruleType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

}
