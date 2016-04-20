package restaurant.dozer.custom.converter;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.Mapping;
import org.springframework.util.ReflectionUtils;

import restaurant.dto.association.AssociationInfo;
import restaurant.frw.bean.ApplicationBean;

public class AssociationConverter extends DozerConverter<AssociationInfo, ApplicationBean> implements MapperAware {
    private Mapper mapper;

    public AssociationConverter() {
        super(AssociationInfo.class, ApplicationBean.class);
    }

    public AssociationConverter(Class<AssociationInfo> prototypeA, Class<ApplicationBean> prototypeB) {
        super(prototypeA, prototypeB);
    }

    @Override
    public ApplicationBean convertTo(AssociationInfo source, ApplicationBean destination) {
        // TODO Auto-generated method stub
        return destination;
    }

    @Override
    public AssociationInfo convertFrom(ApplicationBean source, AssociationInfo destination) {
        /*
         * Steps: Get all mapped fields for source bean
         * Convert value for source bean field to target
         * bean field value
         */
        List<Field> mappedSrcFieldSet = new LinkedList<Field>();
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
                mappedSrcFieldSet.add(mappedSrcField);

                // map src value to dest value
                Object srcValue = ReflectionUtils.getField(mappedSrcField, source);
                Object destValue = mapper.map(srcValue, field.getType());
                ReflectionUtils.setField(field, destination, destValue);
            }

        });

        return destination;
    }

    @Override
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

}
