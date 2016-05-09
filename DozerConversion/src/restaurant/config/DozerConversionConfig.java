package restaurant.config;

import java.util.Arrays;

import restaurant.dozer.custom.mapper.CustomizedDozerBeanMapper;

public class DozerConversionConfig {

    public static CustomizedDozerBeanMapper getDozerBeanMapper() {
        /* Add custom converters */
        // DayTimeConverter dayTimeConverter = new DayTimeConverter();
        // AssociationConverter associationConverter = new AssociationConverter();

        /* Create DozerBeanMapper from above builder */
        //DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList("dozer-config.xml"));
        // mapper.setCustomConverters(Arrays.asList(associationConverter, dayTimeConverter));
    	CustomizedDozerBeanMapper mapper = new CustomizedDozerBeanMapper(Arrays.asList("dozer-config.xml"));

        return mapper;
    }
}
