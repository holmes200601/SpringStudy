package restaurant.ro.uom;

import restaurant.dto.association.UomGroupInfo;

public class UomRO {
	private Long id;
	private String name;
	private Boolean isBaseUom;
	private UomGroupInfo group;

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

	public Boolean getIsBaseUom() {
		return isBaseUom;
	}

	public void setIsBaseUom(Boolean isBaseUom) {
		this.isBaseUom = isBaseUom;
	}

	public UomGroupInfo getGroup() {
		return group;
	}

	public void setGroup(UomGroupInfo group) {
		this.group = group;
	}

}
