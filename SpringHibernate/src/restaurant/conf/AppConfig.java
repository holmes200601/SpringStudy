package restaurant.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import restaurant.test.SpringHibernateTester;
import sampson.test.Tester;

@Configuration("springHibernateConfig")
@ImportResource("bean.xml")
public class AppConfig {
    @Bean(name="springHibernateTester")
    public Tester registerSpringHibernateTester() {
        return new SpringHibernateTester();
    }
}
