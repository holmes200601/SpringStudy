package sampson.util;

import java.util.List;
import java.util.Set;

import sampson.bean.Address;
import sampson.bean.Customer;
import sampson.bean.SalesOrder;
import sampson.enumeration.Gender;

public class ObjectFactory {
	/* create customer */
	public static Customer createCustomer(String name, String phone, Integer age, 
			Gender gender, Long id, Set<Address> ads) {
		return new Customer(name, phone, age, gender, id, ads);
	}
}
