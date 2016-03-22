package restaurant.frw.bean;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import restaurant.frw.common.BeanFacade;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class ApplicationBean {
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
