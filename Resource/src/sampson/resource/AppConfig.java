package sampson.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import sampson.test.ResourceTester;
import sampson.test.Tester;

@Configuration("resourceConfig")
@ImportResource("classpath:bean.xml")
public class AppConfig {
    
    @Bean(name = "resourceTester")
    public Tester registerResourceTester() {
        return new ResourceTester();
    }
}
