package sampson.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Formula;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Person {
    private Long id;
    private String name;
    private Long age;
    private BigDecimal balance;
    private BigDecimal debt;
    private BigDecimal total;
    private Long version;
    private List<Customer> teamMembers = new ArrayList<Customer>();
    
    @Id
    @GeneratedValue(generator="TableGen", strategy=GenerationType.TABLE)
    @TableGenerator(name="TableGen", table="ID_GEN_TABLE", pkColumnName="PK_ID", valueColumnName="PK_NEXT", pkColumnValue="person", allocationSize=1)
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
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    public List<Customer> getTeamMembers() {
        return teamMembers;
    }
    
    public void setTeamMembers(List<Customer> teamMembers) {
        this.teamMembers = teamMembers;
    }
    
    
    
}
