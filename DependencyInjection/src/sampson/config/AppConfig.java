package sampson.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

import sampson.bean.Address;
import sampson.bean.Customer;
import sampson.bean.Invoice;
import sampson.bean.Product;
import sampson.bean.Report;
import sampson.bean.SalesOrder;
import sampson.enumeration.Gender;


@Configuration
@ImportResource("file:E:\\SpringWorkspace\\DependencyInjection\\Bean.xml")
public class AppConfig {
	
	@Autowired
	private ApplicationContext ctx;	
	
	@Bean(name="invoice")
	//@Profile({"p1"})
	public Invoice createInvoice() {
		return new Invoice();
	}
	
	@Bean(name="report")
	//@Profile({"p2"})
	public Report createReport() {
		return new Report();
	}

	@Bean(name="fred")
	public Customer createCustomerFred() {
		Set<Address> addressList = new HashSet<Address>();
		addressList.add((Address)ctx.getBean("address1"));
		
		return new Customer("fred", "123", 40, Gender.MALE, 2L, addressList);
	}
}
