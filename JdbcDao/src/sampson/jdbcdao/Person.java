package sampson.jdbcdao;

import java.io.InputStream;

public class Person {
    @Property(primary=true)
    private Long id;
    
    @Property
    private String name;
    
    @Property(type = "LONGBLOB")
    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
}
