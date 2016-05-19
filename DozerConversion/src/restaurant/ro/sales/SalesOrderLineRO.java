package restaurant.ro.sales;

import java.math.BigDecimal;

import restaurant.dto.association.DishInfo;

public class SalesOrderLineRO {
    private Long id;
    private Long quantity;
    private BigDecimal cost;
    private BigDecimal total;
    private DishInfo dish;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DishInfo getDish() {
        return dish;
    }

    public void setDish(DishInfo dish) {
        this.dish = dish;
    }

}
