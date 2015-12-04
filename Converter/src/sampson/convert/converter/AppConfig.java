package sampson.convert.converter;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import sampson.test.ConverterTester;
import sampson.test.Tester;

@Configuration("converterConfig")
@ImportResource("classpath:bean.xml")
public class AppConfig {
    
    @Bean(name="converterTester")
    public Tester registerConverterTester() {
        return new ConverterTester();
    }
    
    @Bean(name="propertyEditorRegistor")
    public CustomEditorConfigurer getCustomerEditorConfigure() {
        
        CustomEditorConfigurer result = new CustomEditorConfigurer();
        // Set customEditor property
        Map<Class<?>, Class<? extends PropertyEditor>> customEditorMap = new HashMap<Class<?>, Class<? extends PropertyEditor>>();        
        customEditorMap.put(sampson.convert.bean.Wheel.class, sampson.convert.converter.WheelEditor.class);
        result.setCustomEditors(customEditorMap);
        
        // Set propertyEditorRegistrar
        List<PropertyEditorRegistrar> peRegistrarList = new ArrayList<PropertyEditorRegistrar>();
        peRegistrarList.add(new EngineEditor());
        result.setPropertyEditorRegistrars(peRegistrarList.toArray(new PropertyEditorRegistrar[] {}));
        
        return result;
    }
}
