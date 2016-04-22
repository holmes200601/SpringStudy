package restaurant.dozer.custom.converter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.Mapping;
import org.dozer.converters.PrimitiveOrWrapperConverter;
import org.springframework.util.ReflectionUtils;

import restaurant.dto.association.AssociationInfo;
import restaurant.frw.bean.ApplicationBean;
import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;
import restaurant.utils.CollectionUtils;
import restaurant.utils.ReflectionUtilsPlus;

public class AssociationConverter implements MapperAware, CustomConverter {
    private Mapper mapper;
    private PrimitiveOrWrapperConverter primitiveMapper = new PrimitiveOrWrapperConverter();
    private List<CollectionUtils.Pair> fieldValuePairs = new LinkedList<CollectionUtils.Pair>();

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
            Class<?> sourceClass) {
        Object result = null;
        if (sourceClass.getGenericSuperclass().getTypeName().equals(ApplicationBean.class.getName())) {
            result = Bean2Info(sourceClass, destinationClass, (ApplicationBean) sourceFieldValue,
                    (AssociationInfo) existingDestinationFieldValue);
        } else if (sourceClass.getGenericSuperclass().getTypeName().equals(AssociationInfo.class.getName())) {
            result = Info2Bean(sourceClass, destinationClass, (AssociationInfo) sourceFieldValue,
                    (ApplicationBean) existingDestinationFieldValue);
        } else {
            assert (false);
        }

        return result;
    }

    public AssociationInfo Bean2Info(Class<?> sourceClass, Class<?> destClass, ApplicationBean source,
            AssociationInfo destination) {
        if (source == null) {
            return destination;
        }

        if (destination == null) {
            destination = (AssociationInfo) ReflectionUtilsPlus.newInstance(destClass);
        }

        ReflectionUtils.doWithFields(destination.getClass(), new ReflectionUtils.FieldCallback() {

            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                String mappedSrcFieldName = field.getName();
                Mapping mappingAnnotation = field.getAnnotation(Mapping.class);
                if (mappingAnnotation != null) {
                    mappedSrcFieldName = mappingAnnotation.value();
                }

                Field mappedSrcField = ReflectionUtils.findField(source.getClass(), mappedSrcFieldName);
                assert (mappedSrcField != null);

                // map src value to dest value
                mappedSrcField.setAccessible(true);
                Object srcValue = ReflectionUtils.getField(mappedSrcField, source);
                Object destValue = null;
                if (usePrimitiveMapper(mappedSrcField.getType()) && usePrimitiveMapper(field.getType())) {
                    destValue = primitiveMapper.convert(srcValue, field.getType(), null);
                } else {
                    destValue = mapper.map(srcValue, field.getType());
                }
                fieldValuePairs.add(new CollectionUtils.Pair(field, destValue));
            }

        });

        for (CollectionUtils.Pair entry : fieldValuePairs) {
            ((Field) entry.getKey()).setAccessible(true);
            ReflectionUtils.setField((Field) entry.getKey(), destination, entry.getValue());
        }

        return destination;
    }

    public ApplicationBean Info2Bean(Class<?> sourceClass, Class<?> destClass, AssociationInfo source,
            ApplicationBean destination) {
        if (source == null) {
            return destination;
        }

        // Load bean
        BeanFacade facade = SpringContext.getBean(BeanFacade.class);
        destination = (ApplicationBean) facade.loadBean(destClass, source.getId(), false);

        return destination;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    private Boolean usePrimitiveMapper(Class<?> clazz) {
        List<Class<?>> arrayList = Arrays.asList(Integer.class, Double.class, Short.class, Character.class, Long.class,
                Boolean.class, Byte.class, Float.class, String.class);

        if (arrayList.contains(clazz)) {
            return true;
        }

        return false;
    }

}
