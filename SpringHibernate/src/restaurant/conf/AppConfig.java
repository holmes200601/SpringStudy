package restaurant.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import restaurant.test.SpringHibernateTester;
import sampson.test.Tester;

@Configuration("springHibernateConfig")
public class AppConfig {
    @Bean(name="springHibernateTester")
    public Tester registerSpringHibernateTester() {
        return new SpringHibernateTester();
    }
}
