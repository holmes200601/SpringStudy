package restaurant.masterdata.bean.entity;

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

import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.bean.SubApplicationBean;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(AccessType.FIELD)
public class SupplierIngredientLine extends SubApplicationBean {

    private static final long serialVersionUID = 3713949435531874950L;

    @Id
    @GeneratedValue(generator = "SupplierIngredientLineSeq")
    @SequenceGenerator(name = "SupplierIngredientLineSeq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Supplier supplier;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Ingredient ingredient;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Uom supplingUom;

    @Column(nullable = false)
    private BigDecimal supplingPrice;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Uom getSupplingUom() {
        return supplingUom;
    }

    public void setSupplingUom(Uom supplingUom) {
        this.supplingUom = supplingUom;
    }

    public BigDecimal getSupplingPrice() {
        return supplingPrice;
    }

    public void setSupplingPrice(BigDecimal supplingPrice) {
        this.supplingPrice = supplingPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ApplicationBean getParent() {
        // TODO Auto-generated method stub
        return this.getSupplier();
    }

    @Override
    public void setParent(ApplicationBean parent) {
        this.setSupplier((Supplier) parent);

    }

    @Override
    public Long getId() {
        return id;
    }

}
