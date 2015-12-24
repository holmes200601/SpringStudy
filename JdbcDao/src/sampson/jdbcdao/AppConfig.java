package sampson.jdbcdao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import sampson.test.JdbcTester;
import sampson.test.Tester;

@Configuration("jdbcConfig")
@ImportResource("classpath:bean.xml")
@PropertySource("classpath:jdbc.properties")
public class AppConfig {
    @Autowired
    private Environment env;
    
    /* Define DataSource bean */
    @Bean(name="dataSource", destroyMethod="close")
    public DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        
        ds.setDriverClassName(env.getProperty("jdbc.driverName"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setUsername(env.getProperty("jdbc.userName"));
        ds.setPassword(env.getProperty("jdbc.password"));
        
        return ds;
    }
    
    /* Define Jdbc DAO */
    @Bean(name="jdbcDao")
    public JdbcDao getJdbcDao() {
        JdbcDao dao = new JdbcDao();
        
        dao.setDataSource(this.getDataSource());
        
        return dao;
    }
    
    /*Define jdbctester*/
    @Bean(name="jdbcTester")
    public Tester registerJdbcdaoTester() {
        return new JdbcTester();
    }
}
