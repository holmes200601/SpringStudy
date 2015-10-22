package sampson.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import sampson.bean.Customer;
import sampson.bean.Invoice;
import sampson.bean.Product;
import sampson.bean.Report;
import sampson.config.AppConfig;

public class Boost {
	public static void main(String[] args) {
		// Initialize spring container
		AnnotationConfigApplicationContext  ctx = null;
		try {
			ctx = new AnnotationConfigApplicationContext(AppConfig.class);
			// "sampson" profile
			ctx.getEnvironment().setActiveProfiles("p1");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Invoice invoice = (Invoice) ctx.getBean("invoice");
			System.out.printf("***Sampson Profile***\n");
			System.out.printf("\tInvoice: %1$s\n", invoice);
			
			// "fred" profile
			ctx.getEnvironment().setActiveProfiles("p2");
			Report report = (Report) ctx.getBean("report");
			System.out.printf("***Fred Profile***\n");
			System.out.printf("\tReport: %1$s\n", report);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ctx.close();
		}
	}
}
