package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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

import restaurant.frw.bean.ApplicationBean;

@MappedSuperclass
@Access(AccessType.FIELD)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ruletype", discriminatorType=DiscriminatorType.STRING)
public abstract class SalaryRule extends ApplicationBean {
    
    @Id
    @GeneratedValue(generator="SalaryRuleSeq")
    @SequenceGenerator(name="SalaryRuleSeq", allocationSize=1)
    private Long id;
    
    
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "ownerEmployeeId", nullable = false)
    private Employee ownerEmployee;
    
    @Column(nullable=false)
    private String ruleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getOwnerEmployee() {
        return ownerEmployee;
    }

    public void setOwnerEmployee(Employee ownerEmployee) {
        this.ownerEmployee = ownerEmployee;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /* Other calculation logic */
    abstract public BigDecimal calcuateSalaryForMonth(int year, int month);
    
    abstract public BigDecimal calculateSalaryForYear(int year);

}
