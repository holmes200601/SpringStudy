package sampson.convert.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import sampson.convert.bean.Car;

public class CarConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> result = new HashSet<ConvertiblePair>();
        result.add(new ConvertiblePair(String.class, Car.class));

        return result;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        
        return null;
    }

}
