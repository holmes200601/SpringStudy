package restaurant.ro.purchase;

import java.math.BigDecimal;

import restaurant.dto.association.IngredientInfo;
import restaurant.dto.association.SupplierInfo;
import restaurant.dto.association.UomInfo;

public class PurchaseOrderLineRO {
    private Long id;
    private IngredientInfo ingredient;
    private UomInfo purchaseUom;
    private SupplierInfo supplier;
    private BigDecimal quantity;
    private BigDecimal purchasePrice;
    private BigDecimal purchaseCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IngredientInfo getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientInfo ingredient) {
        this.ingredient = ingredient;
    }

    public UomInfo getPurchaseUom() {
        return purchaseUom;
    }

    public void setPurchaseUom(UomInfo purchaseUom) {
        this.purchaseUom = purchaseUom;
    }

    public SupplierInfo getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierInfo supplier) {
        this.supplier = supplier;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

}
