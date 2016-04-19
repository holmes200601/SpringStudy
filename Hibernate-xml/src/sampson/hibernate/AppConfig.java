package sampson.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sampson.test.Tester;
import sampson.tester.HibernateXmlTester;

@Configuration("hibernateXmlConfig")
public class AppConfig {

    @Bean(name = "hibernateXmlTester")
    public Tester registerHibernateXmlTester() {
        return new HibernateXmlTester();
    }
}
