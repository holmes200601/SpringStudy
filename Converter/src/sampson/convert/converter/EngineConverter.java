package sampson.convert.converter;

import org.springframework.core.convert.converter.Converter;

import sampson.convert.bean.Engine;

public class EngineConverter implements Converter<String, Engine> {

    @Override
    public Engine convert(String source) {
        ConverterUtil cu = new ConverterUtil(source, new Engine());
        cu.convert();
        
        return (Engine) cu.getObject();
    }

}
