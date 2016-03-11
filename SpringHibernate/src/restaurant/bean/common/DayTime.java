package restaurant.bean.common;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.PROPERTY)
public class DayTime {
    int hour;
    int minite;
    
    public int getHour() {
        return hour;
    }
    
    public void setHour(int hour) {
        this.hour = hour;
    }
    
    public int getMinite() {
        return minite;
    }
    
    public void setMinite(int minite) {
        this.minite = minite;
    }
    
    
}
