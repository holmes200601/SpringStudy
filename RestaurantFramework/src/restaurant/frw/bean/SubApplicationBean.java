package restaurant.frw.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class SubApplicationBean extends ApplicationNode {
	private static final long serialVersionUID = -626449932570878514L;

	abstract public ApplicationBean getParent();
	
	abstract public void setParent(ApplicationBean parent);
	
}
