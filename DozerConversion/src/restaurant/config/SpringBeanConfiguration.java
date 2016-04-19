package restaurant.config;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import restaurant.converter.AssociationConverter;
import restaurant.dto.association.AssociationInfo;
import restaurant.frw.bean.ApplicationBean;

@Configuration("DozerConversionConfig")
public class SpringBeanConfiguration {
    @Bean(name = "dozerMapper")
    public DozerBeanMapper getDozerBeanMapper() {
        /* Add custom converters */
        AssociationConverter associationConverter = new AssociationConverter(AssociationInfo.class,
                ApplicationBean.class);

        /* Create DozerBeanMapper from above builder */
        DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList("dozer-config.xml"));
        mapper.setCustomConverters(Arrays.asList(associationConverter));

        return mapper;
    }
}
