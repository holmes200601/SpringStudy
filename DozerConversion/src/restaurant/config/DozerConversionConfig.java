package restaurant.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;

public class DozerConversionConfig {

    public static DozerBeanMapper getDozerBeanMapper() {
        /* Add custom converters */
        // DayTimeConverter dayTimeConverter = new DayTimeConverter();
        // AssociationConverter associationConverter = new AssociationConverter();

        /* Create DozerBeanMapper from above builder */
        DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList("dozer-config.xml"));
        // mapper.setCustomConverters(Arrays.asList(associationConverter, dayTimeConverter));

        return mapper;
    }
}
