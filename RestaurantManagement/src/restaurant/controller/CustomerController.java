package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.masterdata.bean.entity.Customer;
import restaurant.ro.crm.CustomerRO;
import restaurant.utils.ReflectionUtilsPlus;

@RestController
@RequestMapping(value = "customer")
public class CustomerController extends ControllerBase {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CustomerRO getCustomer(@PathVariable Long id) {
        Customer entity = getBeanFacade().loadBean(Customer.class, id, true);
        CustomerRO result = getDozerMapper().map(entity, CustomerRO.class);

        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Long createCustomer(@RequestBody CustomerRO customer) {
        Customer entity = getDozerMapper().map(customer, Customer.class);

        Long result = getBeanFacade().saveBean(entity);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void updateCustomer(@RequestBody CustomerRO customer, @PathVariable("id") Long id) {
        Customer entity = getBeanFacade().loadBean(Customer.class, id, true);
        getDozerMapper().map(customer, entity);

        getBeanFacade().saveOrUpdate(entity);
    }

    @Override
    @RequestMapping("/example")
    public Object getInstance() {
        return ReflectionUtilsPlus.newInstance(CustomerRO.class);
    }

}