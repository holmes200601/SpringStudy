package restaurant.dto.association;

import restaurant.dozer.custom.mapper.CustomMapping;

public class UomGroupInfo extends AssociationInfo {
	@CustomMapping(value = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
