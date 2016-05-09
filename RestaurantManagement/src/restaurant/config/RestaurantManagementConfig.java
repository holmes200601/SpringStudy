package restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import restaurant.dozer.custom.mapper.CustomizedDozerBeanMapper;

@Component
public class RestaurantManagementConfig {
    @Bean(name = "dozerMapper")
    public CustomizedDozerBeanMapper registerDozerMapper() {
        return DozerConversionConfig.getDozerBeanMapper();
    }

}
