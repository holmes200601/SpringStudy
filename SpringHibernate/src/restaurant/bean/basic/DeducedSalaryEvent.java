package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Deduced")
public class DeducedSalaryEvent extends SalaryEvent {

    @Column(nullable=false)
    private BigDecimal deducedAmount;

    public BigDecimal getDeducedAmount() {
        return deducedAmount;
    }

    public void setDeducedAmount(BigDecimal deducedAmount) {
        this.deducedAmount = (deducedAmount != null && deducedAmount.signum() < 0) ? deducedAmount.negate() : deducedAmount;
    }

    @Override
    public void calculateAmount() {
        setAmount(getDeducedAmount().negate());        
    }

}
