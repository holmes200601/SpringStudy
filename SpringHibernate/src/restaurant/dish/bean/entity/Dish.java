package restaurant.dish.bean.entity;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Formula;

import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(value = AccessType.FIELD)
public class Dish extends ApplicationBean {
	private static final long serialVersionUID = -5922769018980431064L;

	@Id
	@GeneratedValue(generator = "DishSeq")
	@SequenceGenerator(name = "DishSeq", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String name;
	private String barCodeUrl;
	private String picUrl;

	@Lob
	private String description;
	private BigDecimal price;

	@Formula("SELECT SUM(di.cost) FROM DishIngredient di WHERE di.dishId = id")
	private BigDecimal cost;
	// Following two properties need to be calculated by user or in front page
	// private BigDecimal profit;
	// private BigDecimal profitPercentage;
	@OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DishIngredient> ingredientList = new LinkedList<DishIngredient>();

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

	public List<DishIngredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<DishIngredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

}
