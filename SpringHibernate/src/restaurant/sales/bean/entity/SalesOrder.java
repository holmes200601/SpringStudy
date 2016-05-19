package restaurant.sales.bean.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restaurant.frw.bean.ApplicationBean;
import restaurant.masterdata.bean.entity.Customer;

@Entity
@Access(AccessType.FIELD)
public class SalesOrder extends ApplicationBean {
    private static final long serialVersionUID = 4082521010078284745L;

    @Id
    @GeneratedValue(generator = "SalesOrderSeq")
    @SequenceGenerator(name = "SalesOrderSeq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Order document total
    private BigDecimal total;

    // The amount paid by customer membership point
    private BigDecimal membershipPaidAmount;

    // The amount paid by other means, rather than membership
    private BigDecimal moneyPaidAmount;

    // The cost of this order
    private BigDecimal cost;

    // The time when the order was placed
    private Date orderTime;

    // The time when the order was paid
    private Date paidTime;

    @OneToOne(optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrder", orphanRemoval = true)
    private List<SalesOrderLine> orderLine = new LinkedList<SalesOrderLine>();

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SalesOrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(List<SalesOrderLine> orderLine) {
        this.orderLine = orderLine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Status {
        PAID, UNPAID, CANCELED
    }

}
