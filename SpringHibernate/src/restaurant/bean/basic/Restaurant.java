package restaurant.bean.basic;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import restaurant.bean.common.ContactInfo;
import restaurant.frw.RestaurantBean;

@Entity
@Access(AccessType.PROPERTY)
public class Restaurant extends RestaurantBean {
    private Long id;
    private String name;
    private Employee manage;
    private String iconPath;
    private String comments;
    private List<String> photoGalary;
    private ContactInfo contactInfo;

    @Id
    @GeneratedValue(generator="RestaurantSeq")
    @SequenceGenerator(name="RestaurantSeq", allocationSize=1)
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

    public Employee getManage() {
        return manage;
    }

    public void setManage(Employee manage) {
        this.manage = manage;
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

    public List<String> getPhotoGalary() {
        return photoGalary;
    }

    public void setPhotoGalary(List<String> photoGalary) {
        this.photoGalary = photoGalary;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
