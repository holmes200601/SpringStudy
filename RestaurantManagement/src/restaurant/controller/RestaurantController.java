package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.basic.bean.entity.Restaurant;
import restaurant.frw.common.BeanFacade;
import restaurant.ro.basic.RestaurantRO;

@RestController
@RequestMapping(value = "/restaurant")
public class RestaurantController extends ControllerBase {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RestaurantRO getRestaurant(@PathVariable Long id) {
        BeanFacade bf = getBeanFacade();

        Restaurant loadObject = bf.loadBean(Restaurant.class, id, true);

        RestaurantRO result = getDozerMapper().map(loadObject, RestaurantRO.class);

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public void updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRO restaurant) {
        Restaurant loadedObj = getBeanFacade().loadBean(Restaurant.class, id, true);
        getDozerMapper().map(restaurant, loadedObj);

        int i = 1;

    }

    @RequestMapping(method = RequestMethod.POST)
    public Long createRestaurant(@RequestBody(required = true) RestaurantRO restaurant) {
        BeanFacade bf = getBeanFacade();

        Restaurant bo = getDozerMapper().map(restaurant, Restaurant.class);

        Long id = bf.saveBean(bo);

        return id;
    }

}
