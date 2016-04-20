package restaurant.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;

public class DozerConversionConfig {

    public static DozerBeanMapper getDozerBeanMapper() {
        /* Add custom converters */
        // AssociationConverter associationConverter = new
        // AssociationConverter(AssociationInfo.class,
        // ApplicationBean.class);

        /* Create DozerBeanMapper from above builder */
        DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList("dozer-config.xml"));
        // mapper.setCustomConverters(Arrays.asList(associationConverter));

        return mapper;
    }
}
