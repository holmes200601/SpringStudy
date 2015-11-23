package sampson.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import sampson.test.Tester;

@Configuration("converterConfig")
@ImportResource("classpath:bean.xml")
public class AppConfig {
    
    @Bean(name="wheel")
    public Wheel getWheel(@Value("${wheelString}") String wheelStr) {
        
    }
    
    @Bean(name="wheelTester")
    public Tester registerConverterTester() {
        
    }
}
