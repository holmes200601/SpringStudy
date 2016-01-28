package sampson.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration("testConfig")
@ImportResource(value={"classpath:main-bean.xml"})
public class AppConfig {
    
}
