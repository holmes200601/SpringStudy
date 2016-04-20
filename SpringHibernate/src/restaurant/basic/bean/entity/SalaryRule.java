package restaurant.basic.bean.entity;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
import restaurant.utils.TimeUtils;

@Entity
@Table(name = "SalaryRule")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ruletype", discriminatorType = DiscriminatorType.STRING)
public class SalaryRule extends ApplicationBean {

    /**
     * 
     */
    private static final long serialVersionUID = -8696299616210526417L;

    @Id
    @GeneratedValue(generator = "SalaryRuleSeq")
    @SequenceGenerator(name = "SalaryRuleSeq", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "ownerEmployeeId", nullable = false)
    private Employee ownerEmployee;

    @Column(nullable = false)
    private String ruleName;

    @Temporal(TemporalType.DATE)
    private Date validFrom;

    @Temporal(TemporalType.DATE)
    private Date validTo;

    public SalaryRule() {
        init();
    }

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

    private void init() {
        Calendar cal = TimeUtils.getCurrentCalendar();
        this.setValidFrom(cal.getTime());

        cal.set(2038, 12, 30);
        this.setValidTo(cal.getTime());

    }

    /* Other calculation logic */
    public BigDecimal calculateSalary(Date from, Date to) {
        BigDecimal result = BigDecimal.ZERO;

        String query = buildQuery();
        Map<String, Object> paramMap = buildQueryParamMapForSalary(from, to);

        List<SalaryEvent> salaryEventList = getFacade().loadBeans(SalaryEvent.class, query, paramMap);

        for (SalaryEvent se : salaryEventList) {
            result = result.add(se.getAmount());
        }

        return result;
    };

    public BigDecimal calculateSalaryForMonth(int year, int month) {
        Date start = TimeUtils.calMonthStart(year, month);
        Date end = TimeUtils.calMonthEnd(year, month);

        return calculateSalary(start, end);
    }

    public BigDecimal calculateSalaryForYear(int year) {
        Date start = TimeUtils.calMonthStart(year, 1);
        Date end = TimeUtils.calMonthEnd(year, 12);

        return calculateSalary(start, end);
    }

    protected String buildQuery() {
        String query = "SELECT se FROM SalaryEvent se INNER JOIN se.salaryRule sr "
                + "WHERE sr.id = :srId AND se.time BETWEEN :from AND :to";

        return query;
    }

    protected Map<String, Object> buildQueryParamMapForSalary(Date from, Date to) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("from", from);
        paramMap.put("to", to);
        paramMap.put("srId", getId());

        return paramMap;
    }

}
