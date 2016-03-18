package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Awarded")
public class AwardedSalaryEvent extends SalaryEvent {

    @Column(nullable=false)
    private BigDecimal awardAmount;

    
    public BigDecimal getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(BigDecimal awardAmount) {
        this.awardAmount = awardAmount;
    }

    @Override
    public void calculateAmount() {
        setAmount(getAwardAmount());       
    }

}
