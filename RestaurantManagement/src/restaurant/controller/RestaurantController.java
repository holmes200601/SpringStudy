package restaurant.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import restaurant.basic.bean.entity.Restaurant;
import restaurant.frw.common.BeanFacade;
import restaurant.ro.basic.RestaurantRO;
import restaurant.utils.ReflectionUtilsPlus;

@Controller
@RequestMapping(value = "/restaurant")
public class RestaurantController extends ControllerBase {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RestaurantRO> getRestaurant(@PathVariable Long id) {
        BeanFacade bf = getBeanFacade();

        Restaurant loadObject = bf.loadBean(Restaurant.class, id, true);

        RestaurantRO result = getDozerMapper().map(loadObject, RestaurantRO.class);

        return new ResponseEntity<RestaurantRO>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> updateRestaurant(@PathVariable Long id, HttpEntity<RestaurantRO> requestEntity) {
        Restaurant loadedObj = getBeanFacade().loadBean(Restaurant.class, id, true);
        getDozerMapper().map(requestEntity.getBody(), loadedObj);

        getBeanFacade().saveOrUpdate(loadedObj);

        return new ResponseEntity<Void>((Void) null, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> createRestaurant(@RequestBody(required = true) RestaurantRO restaurant) {
        BeanFacade bf = getBeanFacade();

        Restaurant bo = getDozerMapper().map(restaurant, Restaurant.class);

        Long id = bf.saveBean(bo);

        return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }

	@Override
	@RequestMapping(value="/template", method = RequestMethod.GET)
	public ResponseEntity<RestaurantRO> getInstance() {
		// TODO Auto-generated method stub
		return new ResponseEntity<RestaurantRO>(ReflectionUtilsPlus.newInstance(RestaurantRO.class), HttpStatus.OK);
	}

}
