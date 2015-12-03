package sampson.convert.converter;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import sampson.convert.bean.Engine;

public class EngineEditor extends PropertyEditorSupport implements PropertyEditorRegistrar {
    private static Logger logger = LoggerFactory.getLogger(EngineEditor.class);
    
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        EngineEditor engineEditor = new EngineEditor();
        
        registry.registerCustomEditor(sampson.convert.bean.Engine.class, engineEditor);
    }
    
    @Override
    public void setAsText(String stringValue) {
        ConverterUtil cu = new ConverterUtil(stringValue, new Engine());
        cu.convert();
        setValue(cu.getObject());
    }

}
