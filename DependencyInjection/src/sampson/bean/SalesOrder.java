package sampson.bean;

import java.math.BigDecimal;
import java.util.Map;

public class SalesOrder {
	private Long id;
	private Map<Product, BigDecimal> saledProduct;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Map<Product, BigDecimal> getSaledProduct() {
		return saledProduct;
	}
	public void setSaledProduct(Map<Product, BigDecimal> saledProduct) {
		this.saledProduct = saledProduct;
	}
	
	public BigDecimal orderTotal() {
		BigDecimal result = BigDecimal.ZERO;
		
		for (Map.Entry<Product, BigDecimal> entry : this.getSaledProduct().entrySet()) {
			result = result.add(entry.getKey().getPrice().multiply(entry.getValue()));
		}
		
		return result;
	}
	
}
