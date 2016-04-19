package restaurant.basic.bean.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Deduced")
public class DeducedSalaryRule extends SalaryRule {

}
