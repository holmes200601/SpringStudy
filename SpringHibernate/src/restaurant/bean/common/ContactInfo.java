package restaurant.bean.common;

import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo {
    private String cellPhone;
    private String wechatNum;
    private String qqNum;
    private String email;
    
    public String getCellPhone() {
        return cellPhone;
    }
    
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    public String getWechatNum() {
        return wechatNum;
    }
    
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }
    
    public String getQqNum() {
        return qqNum;
    }
    
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
