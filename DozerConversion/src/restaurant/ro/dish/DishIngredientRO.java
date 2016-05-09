package restaurant.ro.dish;

import java.math.BigDecimal;

import org.dozer.Mapping;

import restaurant.dto.association.DishInfo;
import restaurant.dto.association.UomInfo;

public class DishIngredientRO {
	private Long id;
	private String name;
	private String codeUrl;
	private UomInfo uom;
	private BigDecimal amount;
	private BigDecimal cost;

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

	public UomInfo getUom() {
		return uom;
	}

	public void setUom(UomInfo uom) {
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
}
