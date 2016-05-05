package restaurant.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import restaurant.dish.bean.entity.Dish;
import restaurant.ro.dish.DishRO;

@RestController
@RequestMapping(value="/dish")
public class DishController extends ControllerBase {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DishRO getDish(@PathVariable Long id) {
		Dish entity = getBeanFacade().loadBean(Dish.class, id, true);
		
		DishRO result = getDozerMapper().map(entity, DishRO.class);
		
		return result;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public Long createDish(@RequestBody DishRO dish) {
		Dish entity = getDozerMapper().map(dish, Dish.class);
		
		Long result = getBeanFacade().saveBean(entity);
		
		return result;
	}
	
	@Override
	@RequestMapping("/example")
	public Object getInstance() {		
		return DishRO.example();
	}

}
