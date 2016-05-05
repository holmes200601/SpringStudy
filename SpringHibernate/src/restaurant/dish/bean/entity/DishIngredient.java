package restaurant.dish.bean.entity;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import restaurant.frw.bean.ApplicationBean;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(value= AccessType.PROPERTY)
public class DishIngredient extends ApplicationBean {
	private static final long serialVersionUID = 8650990742879299218L;

	private Long id;
	private String name;
	private String codeUrl;
	private Uom uom;
	private BigDecimal amount;
	private BigDecimal cost;
	private Dish dish;

	@Id
	@GeneratedValue(generator = "DishIngredientSeq")
	@SequenceGenerator(name = "DishIngredientSeq", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	@Column(nullable=false)
	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	@Column(nullable=false)
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(nullable=false)
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="dishId", nullable=false)
	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

}
