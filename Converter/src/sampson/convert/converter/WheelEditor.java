package sampson.convert.converter;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import sampson.convert.bean.MaterialEnum;
import sampson.convert.bean.Wheel;
import sampson.string.StringUtil;

public class WheelEditor extends PropertyEditorSupport {
    private static Logger logger = LoggerFactory.getLogger(WheelEditor.class);
    
    @Override
    public void setAsText(String textValue) {
        ConverterUtil cu = new ConverterUtil(textValue, new Wheel());
        cu.convert();
        setValue(cu.getObject());
    }
}
