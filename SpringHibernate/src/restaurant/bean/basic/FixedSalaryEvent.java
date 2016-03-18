package restaurant.bean.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Fixed")
public class FixedSalaryEvent extends SalaryEvent {

    @Override
    public void calculateAmount() {
        if (!(getSalaryRule() instanceof FixedSalaryRule)) {
            // throw exception here
        }
        
        this.setAmount(((FixedSalaryRule)(getSalaryRule())).getBaseAmount());
    }

}
