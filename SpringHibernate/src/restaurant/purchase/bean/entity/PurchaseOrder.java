package restaurant.purchase.bean.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restaurant.basic.bean.entity.Employee;
import restaurant.common.bean.component.Address;
import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class PurchaseOrder extends ApplicationBean {
    private static final long serialVersionUID = -56008884661013998L;

    @Id
    @GeneratedValue(generator = "PurchaseOrderSeq")
    @SequenceGenerator(name = "PurchaseOrderSeq", allocationSize = 1)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private Date postingTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder", orphanRemoval = true)
    private List<PurchaseOrderLine> purchasedIngredient = new LinkedList<PurchaseOrderLine>();

    @Column(nullable = false)
    private Date orderTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(nullable = false)),
            @AttributeOverride(name = "provinces", column = @Column(nullable = false)),
            @AttributeOverride(name = "state", column = @Column(nullable = false)),
            @AttributeOverride(name = "street", column = @Column(nullable = false)),
            @AttributeOverride(name = "postCode", column = @Column(nullable = false)), })
    private Address address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private BigDecimal total;

    @Override
    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(Date postingTime) {
        this.postingTime = postingTime;
    }

    public List<PurchaseOrderLine> getPurchasedIngredient() {
        return purchasedIngredient;
    }

    public void setPurchasedIngredient(List<PurchaseOrderLine> purchasedIngredient) {
        this.purchasedIngredient = purchasedIngredient;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public enum Status {
        PAID, UNPAID, CANCEL
    }

}
