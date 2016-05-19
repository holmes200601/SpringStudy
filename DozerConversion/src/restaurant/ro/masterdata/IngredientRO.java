package restaurant.ro.masterdata;

import java.math.BigDecimal;

import restaurant.dto.association.UomInfo;

public class IngredientRO {
    private Long id;
    private String name;
    private UomInfo inventoryUom;
    private BigDecimal inventoryQuantity;
    private BigDecimal boughtPrice;
    private BigDecimal estimatedValue;

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

    public UomInfo getInventoryUom() {
        return inventoryUom;
    }

    public void setInventoryUom(UomInfo inventoryUom) {
        this.inventoryUom = inventoryUom;
    }

    public BigDecimal getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(BigDecimal inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public BigDecimal getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(BigDecimal boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public BigDecimal getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(BigDecimal estimatedValue) {
        this.estimatedValue = estimatedValue;
    }

}
