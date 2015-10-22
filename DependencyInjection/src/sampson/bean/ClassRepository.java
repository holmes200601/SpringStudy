package sampson.bean;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository(value="repository")
public class ClassRepository {

	@Bean(name="product3")
	public static Product createProduct() {
		Product p3 = new Product();
		
		p3.setId(Long.valueOf(3));
		p3.setName("P3");
		p3.setPrice(BigDecimal.valueOf(300));
		
		return p3;
	}
	
}
