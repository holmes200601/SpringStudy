package restaurant.frw.bean;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class ApplicationNode implements Serializable {
	private static final long serialVersionUID = 487398936824609117L;

	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public abstract Long getId();
	
	@Override
	public boolean equals(Object o) {
		ApplicationNode rhs = (ApplicationNode) o;
		if (rhs != null && rhs.getId() != null && this.getId() != null) {
			return rhs.getId().compareTo(this.getId()) == 0;
		}
		
		return false;
	}
}
