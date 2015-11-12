package sampson.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sampson.test.ResourceTester;
import sampson.test.Tester;

@Configuration("resourceConfig")
public class AppConfig {
    
    @Bean(name = "resourceTester")
    public Tester registerResourceTester() {
        return new ResourceTester();
    }
}
