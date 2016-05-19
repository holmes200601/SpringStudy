package restaurant.ro.sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import restaurant.dozer.custom.mapper.CustomMapping;
import restaurant.dto.association.CustomerInfo;
import restaurant.sales.bean.entity.SalesOrder;

public class SalesOrderRO {
    private Long id;
    private SalesOrder.Status status;
    private BigDecimal total;
    private BigDecimal membershipPaidAmount;
    private BigDecimal moneyPaidAmount;
    private BigDecimal cost;
    private Date orderTime;
    private Date paidTime;
    private CustomerInfo customer;
    @CustomMapping(value = "orderLine")
    private List<SalesOrderLineRO> orderLine = new LinkedList<SalesOrderLineRO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesOrder.Status getStatus() {
        return status;
    }

    public void setStatus(SalesOrder.Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getMembershipPaidAmount() {
        return membershipPaidAmount;
    }

    public void setMembershipPaidAmount(BigDecimal membershipPaidAmount) {
        this.membershipPaidAmount = membershipPaidAmount;
    }

    public BigDecimal getMoneyPaidAmount() {
        return moneyPaidAmount;
    }

    public void setMoneyPaidAmount(BigDecimal moneyPaidAmount) {
        this.moneyPaidAmount = moneyPaidAmount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerInfo customer) {
        this.customer = customer;
    }

    public List<SalesOrderLineRO> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<SalesOrderLineRO> orderLine) {
        this.orderLine = orderLine;
    }

}
