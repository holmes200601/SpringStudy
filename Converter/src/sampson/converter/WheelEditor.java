package sampson.converter;

import java.beans.PropertyEditorSupport;
import java.util.List;

import sampson.string.StringUtil;

public class WheelEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String textValue) {
        if (StringUtil.isNullString(textValue)) {
            return;
        }
        
        List<String> propertyList = StringUtil.split(textValue, ";");
        propertyList.stream().forEach(this::setPropertyValue);
    }
    
    protected void setPropertyValue(String nameValueStr) {
        
    }
}
