package restaurant.bean.basic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import restaurant.bean.utils.TimeUtils;
import restaurant.frw.bean.ApplicationBean;

@MappedSuperclass
@Access(AccessType.FIELD)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="eventtype", discriminatorType=DiscriminatorType.STRING)
public abstract class SalaryEvent extends ApplicationBean {
    @Id
    @GeneratedValue(generator="SalaryEventSeq")
    @SequenceGenerator(name="SalaryEventSeq", allocationSize=1)
    private Long id;
    
    @ManyToOne(targetEntity=SalaryRule.class)
    @JoinColumn(name="salaryRuleId")
    private SalaryRule salaryRule;
    
    @Temporal(TemporalType.DATE)
    private Date validFrom;
    
    @Temporal(TemporalType.DATE)
    private Date validTo;
    private String comments;
    private BigDecimal amount;
    
    public SalaryEvent() {
        init();
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public SalaryRule getSalaryRule() {
        return salaryRule;
    }
    public void setSalaryRule(SalaryRule salaryRule) {
        this.salaryRule = salaryRule;
    }
       
    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /* Other logics */
    public abstract void calculateAmount();
    private void init() {
        this.setAmount(BigDecimal.ZERO);
        Calendar cal = TimeUtils.getCurrentCalendar();
        this.setValidFrom(cal.getTime());
        
        cal.set(2038, 12, 30);
        this.setValidTo(cal.getTime());
        
    }
    
}
