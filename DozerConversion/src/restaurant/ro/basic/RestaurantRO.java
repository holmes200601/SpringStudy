package restaurant.ro.basic;

import java.util.List;

import restaurant.dto.association.EmployeeInfo;
import restaurant.dto.embeded.ContactInfo;

public class RestaurantRO {
    private Long id;
    private String name;
    private EmployeeInfo manager;
    private String iconPath;
    private String comments;
    private List<String> photoGalary;
    private ContactInfo contactInfo;

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

    public EmployeeInfo getManager() {
        return manager;
    }

    public void setManager(EmployeeInfo manager) {
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
