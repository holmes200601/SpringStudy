package sampson.convert.converter;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sampson.string.object.NameValueProperty;
import sampson.string.object.StringObjectResolver;
import sampson.string.util.StringUtil;

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

        List<NameValueProperty> propertyList = clarifyProperties(textValue);
        propertyList.stream().forEach(this::setPropertyValue);
    }

    @SuppressWarnings("unchecked")
    private void setPropertyValue(NameValueProperty nameValuePair) {
        Object obj = this.getObject();
        if (obj == null) {
            logger.error("Try to apply '{}' to wrong class '{}'");
            return;
        }

        try {
            Field field = obj.getClass().getDeclaredField(nameValuePair.getName());
            field.setAccessible(true);
            Object fieldValue = null;
            if (field.getType() == BigDecimal.class) {
                fieldValue = new BigDecimal(nameValuePair.getValue());
            } else if (field.getType().isEnum()) {
                fieldValue = Enum.valueOf((Class<Enum>) field.getType(), nameValuePair.getValue());
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
        StringObjectResolver resolver = new StringObjectResolver(objStr);
        resolver.resolve();

        return resolver.getResult();
    }
}
