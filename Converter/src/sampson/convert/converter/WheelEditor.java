package sampson.convert.converter;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sampson.convert.bean.Wheel;

public class WheelEditor extends PropertyEditorSupport {
    private static Logger logger = LoggerFactory.getLogger(WheelEditor.class);

    @Override
    public void setAsText(String textValue) {
        ConverterUtil cu = new ConverterUtil(textValue, new Wheel());
        cu.convert();
        setValue(cu.getObject());
    }
}
