package restaurant.ro.masterdata;

import java.math.BigDecimal;

import restaurant.dto.association.IngredientInfo;
import restaurant.dto.association.SupplierInfo;
import restaurant.dto.association.UomInfo;

public class SupplierIngredientLineRO {
    private Long id;
    private SupplierInfo supplier;
    private IngredientInfo ingredient;
    private UomInfo supplingUom;
    private BigDecimal supplingPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SupplierInfo getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierInfo supplier) {
        this.supplier = supplier;
    }

    public IngredientInfo getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientInfo ingredient) {
        this.ingredient = ingredient;
    }

    public UomInfo getSupplingUom() {
        return supplingUom;
    }

    public void setSupplingUom(UomInfo supplingUom) {
        this.supplingUom = supplingUom;
    }

    public BigDecimal getSupplingPrice() {
        return supplingPrice;
    }

    public void setSupplingPrice(BigDecimal supplingPrice) {
        this.supplingPrice = supplingPrice;
    }

}
