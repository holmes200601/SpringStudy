package restaurant.frw.bean;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import restaurant.frw.common.BeanFacade;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class ApplicationBean implements Serializable {
    private static final long serialVersionUID = -8339600186648331156L;

    private BeanFacade facade;

    private Long version;

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Transient
    public BeanFacade getFacade() {
        return facade;
    }

    public void setFacade(BeanFacade facade) {
        this.facade = facade;
    }

}
