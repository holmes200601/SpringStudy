package restaurant.ro.purchase;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import restaurant.dozer.custom.mapper.CustomMapping;
import restaurant.dto.association.EmployeeInfo;
import restaurant.dto.embeded.AddressInfo;
import restaurant.purchase.bean.entity.PurchaseOrder;

public class PurchaseOrderRO {
    private Long id;
    private EmployeeInfo employee;
    private Date postingTime;
    private Date orderTime;
    private AddressInfo address;
    private String phoneNumber;
    private BigDecimal total;
    private PurchaseOrder.Status status;
    @CustomMapping(value = "purchasedIngredient")
    private List<PurchaseOrderLineRO> ingredient = new LinkedList<PurchaseOrderLineRO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeInfo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeInfo employee) {
        this.employee = employee;
    }

    public Date getPostingTime() {
        return postingTime;
    }

    public void setPostingTime(Date postingTime) {
        this.postingTime = postingTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<PurchaseOrderLineRO> getIngredient() {
        return ingredient;
    }

    public void setIngredient(List<PurchaseOrderLineRO> ingredient) {
        this.ingredient = ingredient;
    }

    public PurchaseOrder.Status getStatus() {
        return status;
    }

    public void setStatus(PurchaseOrder.Status status) {
        this.status = status;
    }

}
