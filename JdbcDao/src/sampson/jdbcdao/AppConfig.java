package sampson.jdbcdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import sampson.test.JdbcTester;
import sampson.test.Tester;

@Configuration("jdbcConfig")
@ImportResource("classpath:bean.xml")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {

    /* Define jdbctester */
    @Bean(name = "jdbcTester")
    public Tester registerJdbcdaoTester() {
        return new JdbcTester();
    }
}
