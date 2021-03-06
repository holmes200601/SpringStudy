package restaurant.uom.bean.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class UomGroup extends ApplicationBean {

    private static final long serialVersionUID = -777904883719785465L;

    @Id
    @GeneratedValue(generator = "UomGroupSeq")
    @SequenceGenerator(name = "UomGroupSeq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "baseUomId")
    private Uom baseUom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Uom getBaseUom() {
        return baseUom;
    }

    public void setBaseUom(Uom baseUom) {
        this.baseUom = baseUom;
    }

}
