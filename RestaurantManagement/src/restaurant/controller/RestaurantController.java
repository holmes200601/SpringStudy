package restaurant.controller;

import org.dozer.DozerBeanMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.basic.bean.entity.Restaurant;
import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;
import restaurant.ro.basic.RestaurantRO;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestaurantRO getRestaurant(@PathVariable Long id) {
        BeanFacade bf = getBeanFacade();

        Restaurant loadObject = bf.loadBean(Restaurant.class, id);

        DozerBeanMapper mapper = bf.getSpringBean(DozerBeanMapper.class);

        RestaurantRO result = mapper.map(loadObject, RestaurantRO.class);

        return result;
    }

    private BeanFacade getBeanFacade() {
        return SpringContext.getBean(BeanFacade.class);
    }

}
