package sampson.jdbcdao;

import java.math.BigDecimal;

public class Customer extends Person{
    @Property
    private String customerCode;
    
    @Property
    private BigDecimal balance;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    
}
