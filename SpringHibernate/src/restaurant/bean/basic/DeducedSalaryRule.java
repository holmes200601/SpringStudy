package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Deduced")
public class DeducedSalaryRule extends SalaryRule {

    @Override
    public BigDecimal calcuateSalaryForMonth(int year, int month) {
        BigDecimal result = BigDecimal.ZERO;
        
        return result;
    }

    @Override
    public BigDecimal calculateSalaryForYear(int year) {
        BigDecimal result = BigDecimal.ZERO;
        
        return result;
    }

}
