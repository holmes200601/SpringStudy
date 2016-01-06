package sampson.jdbcdao;

public class Eshop {
    @Property(primary=true)
    private Long id;
    
    @Property
    private String name;
    
    @Property
    private String location;
    
    @Property(type = "BLOB")
    private byte[] logoImage;
    
    @Property(type = "TEXT")
    private String description;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(byte[] logoImage) {
        this.logoImage = logoImage;
    }    
    
}
