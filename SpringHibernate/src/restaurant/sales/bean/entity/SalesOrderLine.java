package restaurant.sales.bean.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restaurant.dish.bean.entity.Dish;
import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.bean.SubApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class SalesOrderLine extends SubApplicationBean {
    private static final long serialVersionUID = -6501145732578190397L;

    @Id
    @GeneratedValue(generator = "SalesOrderLineSeq")
    @SequenceGenerator(name = "SalesOrderLineSeq", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId", nullable = false)
    private SalesOrder salesOrder;

    @OneToOne(optional = false)
    @JoinColumn(name = "dishId", nullable = false)
    private Dish dish;

    private Long quantity;

    private BigDecimal cost;

    private BigDecimal total;

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public ApplicationBean getParent() {
        return getSalesOrder();
    }

    @Override
    public void setParent(ApplicationBean parent) {
        setSalesOrder((SalesOrder) parent);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
