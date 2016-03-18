package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Fixed")
public class FixedSalaryRule extends SalaryRule {
    @Column(nullable = false)
    private BigDecimal baseAmount;

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    @Override
    public BigDecimal calcuateSalaryForMonth(int year, int month) {
        /* Load all SalaryEvent for in the target month */
        
        /* Add up all amount in loaded SalaryEvent */
        
        return getBaseAmount();
    }

    @Override
    public BigDecimal calculateSalaryForYear(int year) {
        /* Load all SalaryEvent for in the target year */
        
        /* Add up all amount in the loaded SalaryEvent */
        
        return getBaseAmount().multiply(BigDecimal.valueOf(12));
    }
 
    
}
