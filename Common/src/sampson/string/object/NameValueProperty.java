package sampson.string.object;

public class NameValueProperty {
    String name;
    String value;
    
    public NameValueProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.value;
    }
}
