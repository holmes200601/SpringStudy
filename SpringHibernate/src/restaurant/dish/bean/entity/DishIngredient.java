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
import restaurant.frw.bean.SubApplicationBean;
import restaurant.uom.bean.entity.Uom;

@Entity
@Access(value = AccessType.FIELD)
public class DishIngredient extends SubApplicationBean {
	private static final long serialVersionUID = 8650990742879299218L;

	@Id
	@GeneratedValue(generator = "DishIngredientSeq")
	@SequenceGenerator(name = "DishIngredientSeq", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private String name;
	private String codeUrl;

	@JoinColumn(nullable = false)
	@ManyToOne(optional = false)
	private Uom uom;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	private BigDecimal cost;

	@ManyToOne(optional = false)
	@JoinColumn(name = "dishId", nullable = false)
	private Dish dish;

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

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	@Override
	public ApplicationBean getParent() {
		return this.getDish();
	}

	@Override
	public void setParent(ApplicationBean parent) {
		if (parent instanceof Dish) {
			this.setDish((Dish) parent);
		} else {
			assert (false);
		}
	}

}
