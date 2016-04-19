package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.basic.bean.entity.Restaurant;
import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurant(@PathVariable Long id) {
        BeanFacade bf = getBeanFacade();

        Restaurant result = bf.loadBean(Restaurant.class, id);

        return result;
    }

    private BeanFacade getBeanFacade() {
        return SpringContext.getBean(BeanFacade.class);
    }

}
