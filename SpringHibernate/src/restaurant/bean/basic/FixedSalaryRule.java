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


}
