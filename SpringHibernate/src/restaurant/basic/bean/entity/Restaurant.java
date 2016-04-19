package restaurant.basic.bean.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import restaurant.common.bean.component.ContactDetail;
import restaurant.frw.bean.ApplicationBean;

@Entity
@Access(AccessType.PROPERTY)
public class Restaurant extends ApplicationBean {
    /**
     * 
     */
    private static final long serialVersionUID = 8265177144990260203L;
    private Long id;
    private String name;
    private Employee manager;
    private String iconPath;
    private String comments;
    private List<String> photoGalary = new ArrayList<String>();
    private ContactDetail contactInfo;

    @Id
    @GeneratedValue(generator = "RestaurantSeq")
    @SequenceGenerator(name = "RestaurantSeq", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(targetEntity = Employee.class)
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ElementCollection
    @CollectionTable(name = "Restaurant_PhotoGalary", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "photo_path")
    public List<String> getPhotoGalary() {
        return photoGalary;
    }

    public void setPhotoGalary(List<String> photoGalary) {
        this.photoGalary = photoGalary;
    }

    public ContactDetail getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactDetail contactInfo) {
        this.contactInfo = contactInfo;
    }
}
