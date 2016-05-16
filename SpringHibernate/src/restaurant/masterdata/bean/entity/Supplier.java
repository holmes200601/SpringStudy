package restaurant.masterdata.bean.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import restaurant.common.bean.component.ContactDetail;
import restaurant.common.bean.component.PersonName;
import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.FIELD)
public class Supplier extends ApplicationBean {
    private static final long serialVersionUID = 4651937326259028807L;

    @Id
    @GeneratedValue(generator = "SupplierSeq")
    @SequenceGenerator(name = "SupplierSeq", allocationSize = 1)
    private Long id;

    @Embedded
    private PersonName name;

    @Embedded
    private ContactDetail contactInfo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", orphanRemoval = true)
    private Set<SupplierIngredientLine> ingredientLine = new TreeSet<SupplierIngredientLine>();

    @Override
    public Long getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name;
    }

    public ContactDetail getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactDetail contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<SupplierIngredientLine> getIngredientLine() {
        return ingredientLine;
    }

    public void setIngredientLine(Set<SupplierIngredientLine> ingredientLine) {
        this.ingredientLine = ingredientLine;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
