package restaurant.ro.dish;

import java.math.BigDecimal;

import restaurant.dto.association.UomInfo;

public class DishIngredientLineRO {
    private Long id;
    private String name;
    private String codeUrl;
    private UomInfo salesUom;
    private BigDecimal quantity;
    private BigDecimal cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public UomInfo getSalesUom() {
        return salesUom;
    }

    public void setSalesUom(UomInfo salesUom) {
        this.salesUom = salesUom;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
