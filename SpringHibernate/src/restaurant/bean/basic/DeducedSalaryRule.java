package restaurant.bean.basic;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Deduced")
public class DeducedSalaryRule extends SalaryRule {

}
