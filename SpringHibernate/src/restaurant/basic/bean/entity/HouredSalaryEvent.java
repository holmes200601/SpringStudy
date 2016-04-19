package restaurant.basic.bean.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import restaurant.utils.TimeUtils;

@Entity
@DiscriminatorValue(value = "Houred")
public class HouredSalaryEvent extends SalaryEvent {

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public void calculateAmount() {
        if (!(getSalaryRule() instanceof HouredSalaryRule)) {
            // Throw exception 
        }

        /* Calculate working hour */
        int workedHours = TimeUtils.calculateDiffHours(getStartTime(), getEndTime());        

        BigDecimal unit = ((HouredSalaryRule)getSalaryRule()).getAmountPerHour();

        this.setAmount(unit.multiply(BigDecimal.valueOf(workedHours)));
    }

}
