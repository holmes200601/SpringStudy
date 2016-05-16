package restaurant.dish.bean.entity;

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
import restaurant.masterdata.bean.entity.Ingredient;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(value = AccessType.FIELD)
public class DishIngredientLine extends SubApplicationBean {
    private static final long serialVersionUID = 8650990742879299218L;

    @Id
    @GeneratedValue(generator = "DishIngredientSeq")
    @SequenceGenerator(name = "DishIngredientSeq", allocationSize = 1)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ingredientId", nullable = false)
    private Ingredient ingredient;

    @JoinColumn(nullable = false)
    @ManyToOne(optional = false)
    private Uom salesUom;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(nullable = false)
    private BigDecimal cost;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dishId", nullable = false)
    private Dish dish;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Uom getSalesUom() {
        return salesUom;
    }

    public void setSalesUom(Uom salesUom) {
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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public ApplicationBean getParent() {
        return this.getDish();
    }

    @Override
    public void setParent(ApplicationBean parent) {
        if (parent instanceof Dish) {
            this.setDish((Dish) parent);
        } else {
            assert (false);
        }
    }

}
