package sampson.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration("testConfig")
@ImportResource(value={"classpath:bean.xml"})
public class AppConfig {
    
}
