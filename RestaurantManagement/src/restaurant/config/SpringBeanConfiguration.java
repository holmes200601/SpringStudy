package restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration("RestaurantManagementConfig")
public class SpringBeanConfiguration {

    @Bean(name = "objectMapper")
    public ObjectMapper createJacksonObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        return om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
