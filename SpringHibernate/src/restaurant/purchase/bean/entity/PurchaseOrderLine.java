package restaurant.purchase.bean.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Formula;

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.bean.SubApplicationBean;
import restaurant.masterdata.bean.entity.Ingredient;
import restaurant.masterdata.bean.entity.Supplier;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(AccessType.FIELD)
public class PurchaseOrderLine extends SubApplicationBean {
    private static final long serialVersionUID = -2602319272752646151L;

    @Id
    @GeneratedValue(generator = "PurchaseOrderLineSeq")
    @SequenceGenerator(name = "PurchaseOrderLineSeq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "purchaseOrderId", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToOne
    @JoinColumn(name = "ingredientId", nullable = false)
    private Ingredient ingredient;

    @OneToOne
    @JoinColumn(name = "purchaseUom", nullable = false)
    private Uom purchaseUom;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @OneToOne
    @JoinColumn(name = "supplier", nullable = false)
    private Supplier supplier;

    @Column(nullable = false)
    private BigDecimal purchasePrice;

    @Formula("purchasePrice * quantity")
    private BigDecimal purchaseCost;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Uom getPurchaseUom() {
        return purchaseUom;
    }

    public void setPurchaseUom(Uom purchaseUom) {
        this.purchaseUom = purchaseUom;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ApplicationBean getParent() {
        return this.getPurchaseOrder();
    }

    @Override
    public void setParent(ApplicationBean parent) {
        this.setPurchaseOrder((PurchaseOrder) parent);
    }

    @Override
    public Long getId() {
        return id;
    }

}
