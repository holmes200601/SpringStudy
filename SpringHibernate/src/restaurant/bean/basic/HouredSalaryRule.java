package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Houred")
public class HouredSalaryRule extends SalaryRule {

    @Column(nullable=false)
    private BigDecimal amountPerHour;
    
    public BigDecimal getAmountPerHour() {
        return amountPerHour;
    }

    public void setAmountPerHour(BigDecimal amountPerHour) {
        this.amountPerHour = amountPerHour;
    }

    @Override
    public BigDecimal calcuateSalaryForMonth(int year, int month) {
        BigDecimal result = BigDecimal.ZERO;
        
        /* Load all SalaryEvent for this rule in the target month */
        
        /* Add up amount in each SalaryEvent */
        
        return result;
    }

    @Override
    public BigDecimal calculateSalaryForYear(int year) {
        BigDecimal result = BigDecimal.ZERO;
        
        /* Load all SalaryEvent for this rule in the target month */
        
        /* Add up amount in each SalaryEvent */
        
        return result;
    }

}
