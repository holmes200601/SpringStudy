package sampson.hibernate;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {
    boolean vip;
    BigDecimal consumeAmount;

    
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public BigDecimal getConsumeAmount() {
        return consumeAmount;
    }
    
    public void setConsumeAmount(BigDecimal consumeAmount) {
        this.consumeAmount = consumeAmount;
    }    
}
