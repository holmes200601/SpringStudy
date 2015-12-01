package sampson.convert.bean;

import java.math.BigDecimal;

public class Wheel {
    private BigDecimal radius = BigDecimal.ZERO;
    private MaterialEnum material = MaterialEnum.DEFAULT;
    
    public BigDecimal getRadius() {
        return radius;
    }
    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
    public MaterialEnum getMaterial() {
        return material;
    }
    public void setMaterial(MaterialEnum material) {
        this.material = material;
    }
    
    @Override
    public String toString() {
        return String.format("Wheel: radius-%1$s, material-%2$s", getRadius(), getMaterial());
    }
}
