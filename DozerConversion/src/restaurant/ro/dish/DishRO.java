package restaurant.ro.dish;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import restaurant.dto.association.DishInfo;
import restaurant.dto.association.UomInfo;

public class DishRO {
	private Long id;
	private String name;
	private String barCodeUrl;
	private String picUrl;
	private String description;
	private BigDecimal price;
	private BigDecimal cost;
	private List<DishIngredientRO> ingredient = new LinkedList<DishIngredientRO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarCodeUrl() {
		return barCodeUrl;
	}

	public void setBarCodeUrl(String barCodeUrl) {
		this.barCodeUrl = barCodeUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<DishIngredientRO> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<DishIngredientRO> ingredient) {
		this.ingredient = ingredient;
	}
	
	public static DishRO example() {
		DishRO dish = new DishRO();
		
		dish.setBarCodeUrl("bar code url");
		dish.setDescription("example dish description.");
		dish.setName("dish name");
		dish.setPicUrl("picture url");
		dish.setPrice(BigDecimal.valueOf(99));
		
		DishIngredientRO ingr = new DishIngredientRO();
		ingr.setAmount(BigDecimal.ONE);
		ingr.setCodeUrl("code url");
		ingr.setCost(BigDecimal.TEN);
		DishInfo di = new DishInfo();
		di.setId(dish.getId());
		di.setName(dish.getName());
		di.setBarCodeUrl(dish.getBarCodeUrl());
		ingr.setDishInfo(di);
		ingr.setName("ingredient Name");
		UomInfo uom = new UomInfo();
		uom.setId(1L);
		uom.setName("g");
		ingr.setUom(uom);
		
		dish.getIngredient().add(ingr);	
		
		return dish;
	}

}
