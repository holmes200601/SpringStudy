package sampson.jdbcdao;

import java.math.BigDecimal;

public class Product {
    @Property(primary=true)
    private Long id;
    
    @Property
    private String code;
    
    @Property
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
