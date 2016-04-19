package restaurant.basic.bean.entity;

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

}
