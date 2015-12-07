package sampson.convert.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;

public class EnumConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> result = new HashSet<ConvertiblePair>();
        result.add(new ConvertiblePair(String.class, Enum.class));

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return constructDefaultTarget(targetType);
        } else {
            return Enum.valueOf((Class<Enum>) (targetType.getObjectType()), (String) source);
        }
    }

    private Object constructDefaultTarget(TypeDescriptor targetType) {
        Object result = null;

        try {
            result = targetType.getObjectType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
