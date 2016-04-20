package restaurant.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RestaurantManagementConfig {
    @Bean(name = "dozerMapper")
    public DozerBeanMapper registerDozerMapper() {
        return DozerConversionConfig.getDozerBeanMapper();
    }
}
