package sampson.convert.converter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sampson.string.StringUtil;

public class ConverterUtil {
    private static Logger logger = LoggerFactory.getLogger(ConverterUtil.class);
    
    private String textValue;
    private Object object;
    
    private class NameValueProperty {
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
        
        List<NameValueProperty> propertyList = clarifyProperties(textValue);
        propertyList.stream().forEach(this::setPropertyValue);
    }
    
    @SuppressWarnings("unchecked")
    private void setPropertyValue(NameValueProperty nameValuePair) {
        Object obj = this.getObject();
        if (obj == null) {
            logger.error("Try to apply '{}' to wrong class '{}'", obj.getClass().getName());
            return;
        }
        
        try {
            Field field = obj.getClass().getDeclaredField(nameValuePair.getName());
            field.setAccessible(true);
            Object fieldValue = null;
            if (field.getType() == BigDecimal.class) {
                fieldValue = new BigDecimal(nameValuePair.getValue());
            } else if (field.getType().isEnum()) {
                fieldValue = Enum.valueOf((Class<Enum>)field.getType(), nameValuePair.getValue());
            } else {
                ConverterUtil cu = new ConverterUtil(nameValuePair.getValue(), field.getType().newInstance());
                cu.convert();
                fieldValue = cu.getObject();
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
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private List<NameValueProperty> clarifyProperties(String objStr) {
        List<NameValueProperty> result = new ArrayList<NameValueProperty>();
        
        int start = 0;
        while (start < objStr.length()) {
            start = calculatePropertyStartIndex(start, objStr);
            if (start >= objStr.length()) {
                logger.error("Invalid object string '{}' for class '{}'", objStr, getObject().getClass().getName());
                throw new IllegalArgumentException();
            }
            int end = calculatePropertyEndIndex(start, objStr);
            result.add(objStr.substring(start, end));
            start = end + 1;
        }
        
        return result;
    }
    
    private int calculatePropertyStartIndex(int start, String objStr) {
        int realStart = 0;
        
        for (realStart = start; (realStart < objStr.length()) && !isPropertyStartChar(objStr.charAt(realStart)); ++realStart) {}
        
        return realStart;
    }
    
    private boolean isPropertyStartChar(char ch) {
        return ((ch >= 'a' && ch <='z') || (ch >= 'A' && ch <= 'Z'));
    }
    
    private int calculatePropertyEndIndex(int start, String objStr) {
        int end = start;
        
        
        
        return end;
    }
}
