package sampson.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Report {
	@Autowired
	@Qualifier("fred")
	private Customer customer;
	
	@Autowired
	@Qualifier("fred")
	private List<SalesOrder> orderList;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<SalesOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<SalesOrder> orderList) {
		this.orderList = orderList;
	}
	
}
