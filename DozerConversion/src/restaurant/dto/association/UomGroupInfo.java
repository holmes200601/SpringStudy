package restaurant.dto.association;

import org.dozer.Mapping;

public class UomGroupInfo extends AssociationInfo {
	@Mapping("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
