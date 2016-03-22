package restaurant.bean.basic;

import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Awarded")
public class AwardedSalaryRule extends SalaryRule {


}
