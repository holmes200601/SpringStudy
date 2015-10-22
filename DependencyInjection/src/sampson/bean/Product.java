package sampson.bean;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Product {
	private Long id;
	private String name;
	private BigDecimal price;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setProperties(Properties props) {		
		this.setId(Long.valueOf(props.getProperty("id")));
		this.setName(props.getProperty("name"));
		this.setPrice(BigDecimal.valueOf(Double.parseDouble(props.getProperty("price"))));
	}
	
}
