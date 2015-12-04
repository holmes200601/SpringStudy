package sampson.convert.converter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sampson.convert.bean.MaterialEnum;
import sampson.convert.bean.Wheel;
import sampson.string.StringUtil;

public class ConverterUtil {
    private static Logger logger = LoggerFactory.getLogger(ConverterUtil.class);
    
    private String textValue;
    private Object object;
    
    public ConverterUtil(String stringValue, Object obj) {
        this.object = obj;
        this.textValue = stringValue;        
    }
    
    public String getTextValue() {
        return this.textValue;
    }
    
    public Object getObject() {
        return this.object;
    }
    
    public void convert() {
        if (StringUtil.isNullString(textValue)) {
            return;
        }
        
        if (textValue == null || object == null) {
            return;
        }
        
        List<String> propertyList = StringUtil.split(textValue, ";");
        propertyList.stream().forEach(this::setPropertyValue);
    }
    
    @SuppressWarnings("unchecked")
    private void setPropertyValue(String nameValueStr) {
        Object obj = this.getObject();
        if (obj == null) {
            logger.error("Try to apply '{}' to wrong class '{}'", obj.getClass().getName());
            return;
        }
        
        String[] nameValuePair = nameValueStr.split(":");
        if (nameValuePair.length != 2) {
            logger.error("Wrong name-value pair format '{}' for class {}", nameValueStr, obj.getClass().getName());
            return;
        }
        
        try {
            Field field = obj.getClass().getDeclaredField(nameValuePair[0]);
            field.setAccessible(true);
            Object fieldValue = null;
            if (field.getType() == BigDecimal.class) {
                fieldValue = new BigDecimal(nameValuePair[1]);
            } else if (field.getType().isEnum()) {
                fieldValue = Enum.valueOf((Class<Enum>)field.getType(), nameValuePair[1]);
            }
            field.set(obj, fieldValue);
            
        } catch (NoSuchFieldException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
