package sampson.converter;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;

import sampson.string.StringUtil;

public class WheelEditor extends PropertyEditorSupport {
    private static Logger logger = LoggerFactory.getLogger(WheelEditor.class);
    
    @Autowired
    private ConfigurableApplicationContext context;
    
    @Override
    public void setAsText(String textValue) {
        if (StringUtil.isNullString(textValue)) {
            return;
        }
        
        List<String> propertyList = StringUtil.split(textValue, ";");
        propertyList.stream().forEach(this::setPropertyValue);
    }
    
    protected void setPropertyValue(String nameValueStr) {
        Object obj = this.getValue();
        if (obj == null || !(obj instanceof Wheel)) {
            logger.error("Try to apply '{}' to wrong class '{}'", obj.getClass().getName());
            return;
        }
        
        Wheel wheel = (Wheel) obj;
        String[] nameValuePair = nameValueStr.split(":");
        if (nameValuePair.length != 2) {
            logger.error("Wrong name-value pair format '{}' for class {}", nameValueStr, wheel.getClass().getName());
            return;
        }
        
        try {
            Field field = wheel.getClass().getDeclaredField(nameValuePair[0]);
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            PropertyEditorRegistry peRegistry = null;
            beanFactory.copyRegisteredEditorsTo(peRegistry);
            PropertyEditor fieldEditor = peRegistry.findCustomEditor(field.getType(), null);
            if (fieldEditor == null) {
                logger.error("No property editor was found for class '{}'", field.getType());
            }
            fieldEditor.setValue(field.get(wheel));
            fieldEditor.setAsText(nameValuePair[1]);
            field.set(wheel, fieldEditor.getValue());
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
