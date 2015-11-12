package sampson.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration("testConfig")
@ImportResource(value={"file:E:\\SpringWorkspace\\TestEntrance\\bean.xml"})
public class AppConfig {
    
}
