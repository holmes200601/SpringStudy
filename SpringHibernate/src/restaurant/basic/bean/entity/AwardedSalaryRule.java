package restaurant.basic.bean.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Awarded")
public class AwardedSalaryRule extends SalaryRule {

}
