package sampson.hibernate;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Formula;

@Entity
@Access(AccessType.PROPERTY)
public class Person {
    private Long id;
    private String name;
    private Long age;
    private BigDecimal balance;
    private BigDecimal debt;
    private BigDecimal total;
    private Long version;
    
    @Id
    @GeneratedValue(generator="PersonSeq", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="PersonSeq", sequenceName="person_seq")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Version
    public Long getVersion() {
        return version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }    
    
    public BigDecimal getDebt() {
        return debt;
    }
    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    @Formula("balance - debt")
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
}
