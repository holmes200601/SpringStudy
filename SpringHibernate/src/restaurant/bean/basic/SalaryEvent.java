package restaurant.bean.basic;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import restaurant.frw.bean.ApplicationBean;

@Entity
@Table(name="SalaryEvent")
@Access(AccessType.FIELD)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="eventtype", discriminatorType=DiscriminatorType.STRING)
public class SalaryEvent extends ApplicationBean {
    @Id
    @GeneratedValue(generator="SalaryEventSeq")
    @SequenceGenerator(name="SalaryEventSeq", allocationSize=1)
    private Long id;

    @ManyToOne(targetEntity=SalaryRule.class)
    @JoinColumn(name="salaryRuleId")
    private SalaryRule salaryRule;

    private String comments;
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Temporal(TemporalType.DATE)
    private Date time;

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

    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    
    /* Other logics */
    public void calculateAmount() {
        this.setAmount(BigDecimal.ZERO);
    }

}
