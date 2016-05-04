package restaurant.ro.uom;

import restaurant.dto.association.UomInfo;

public class UomGroupRO {
	private Long id;
	private String name;
	private UomInfo baseUom;

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

	public UomInfo getBaseUom() {
		return baseUom;
	}

	public void setBaseUom(UomInfo baseUom) {
		this.baseUom = baseUom;
	}

}
