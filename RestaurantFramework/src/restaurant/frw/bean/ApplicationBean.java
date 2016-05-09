package restaurant.frw.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import restaurant.frw.common.BeanFacade;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class ApplicationBean extends ApplicationNode {
	private static final long serialVersionUID = -8339600186648331156L;

	@Transient
	private BeanFacade facade;

	public BeanFacade getFacade() {
		return facade;
	}

	public void setFacade(BeanFacade facade) {
		this.facade = facade;
	}

}
