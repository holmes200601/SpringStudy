package restaurant.ro.masterdata;

import java.util.Set;
import java.util.TreeSet;

import restaurant.dozer.custom.mapper.CustomMapping;
import restaurant.dto.embeded.ContactInfo;
import restaurant.dto.embeded.PersonNameInfo;

public class SupplierRO {
    private Long id;
    private PersonNameInfo name;
    private ContactInfo contactInfo;
    @CustomMapping(value = "ingredientLine")
    private Set<SupplierIngredientLineRO> ingredient = new TreeSet<SupplierIngredientLineRO>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonNameInfo getName() {
        return name;
    }

    public void setName(PersonNameInfo name) {
        this.name = name;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Set<SupplierIngredientLineRO> getIngredient() {
        return ingredient;
    }

    public void setIngredient(Set<SupplierIngredientLineRO> ingredient) {
        this.ingredient = ingredient;
    }

}
