package restaurant.masterdata.bean.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Formula;

import restaurant.frw.bean.ApplicationBean;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(AccessType.FIELD)
public class Ingredient extends ApplicationBean {

    private static final long serialVersionUID = 5922493932466357567L;

    @Id
    @GeneratedValue(generator = "IngredientSeq")
    @SequenceGenerator(name = "IngredientSeq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "inventoryUom", nullable = false)
    private Uom inventoryUom;

    private BigDecimal inventoryQuantity;

    private BigDecimal boughtPrice;

    @Formula("boughtPrice * inventoryPrice")
    private BigDecimal estimatedValue;

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uom getInventoryUom() {
        return inventoryUom;
    }

    public void setInventoryUom(Uom inventoryUom) {
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

    public void setId(Long id) {
        this.id = id;
    }

}
