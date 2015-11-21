package sampson.converter;

import java.math.BigDecimal;

public class Engine {
    private BigDecimal power = BigDecimal.ZERO;
    private ProducerEnum producer = ProducerEnum.DEFAULT;
    
    public BigDecimal getPower() {
        return power;
    }
    public void setPower(BigDecimal power) {
        this.power = power;
    }
    public ProducerEnum getProducer() {
        return producer;
    }
    public void setProducer(ProducerEnum producer) {
        this.producer = producer;
    }
    
    @Override
    public String toString() {
        return String.format("Engine: producer-%1$s, power-%2$s", getProducer(), getPower());
    }
    
}
