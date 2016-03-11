package restaurant.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

import restaurant.bean.common.Address;
import restaurant.bean.common.PersonName;
import sampson.test.Tester;

public class SpringHibernateTester implements Tester {
    private static final Logger logger = Logger.getLogger(SpringHibernateTester.class); 
    
    SessionFactory sf = null;
    
    @Override
    public void prepareTest() {
        logger.info("Entering prepareTest...");

        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        
    }

    @Override
    public void executeTest() {
        logger.info("Entering executeTest...");

        logger.infof("*** testEmployeePersistence: %1$s ***\n", testEmployeePersistence() ? "SUCCESS" : "FAILED");
        
    }

    @Override
    public void clearTest() {
        logger.info("Entering clearTest...");

    }
    
    private boolean testEmployeePersistence() {
        /* Prepare PersonName */
        PersonName sn = new PersonName();
        sn.setFirstName("Sampson");
        sn.setLastName("He");
        
        PersonName en = new PersonName();
        en.setFirstName("Amy");
        en.setLastName("Chen");
        
        /*Prepare address */
        Address sa = new Address();
        sa.setCountry("China");
        sa.setProvinces("Shanghai");
        sa.setState("Shanghai");
        sa.setStreet("Zhumei Road 266");
        sa.setPostCode("200231");
        
        /* Prepare contact info */
        ContactInfo
    }

}
