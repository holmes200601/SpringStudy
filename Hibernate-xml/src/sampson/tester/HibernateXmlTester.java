package sampson.tester;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sampson.hibernate.Customer;
import sampson.hibernate.Person;
import sampson.test.Tester;

public class HibernateXmlTester implements Tester {
    private static final Logger logger = LoggerFactory.getLogger(HibernateXmlTester.class);
    
    @Override
    public void prepareTest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void executeTest() {
        // TODO Auto-generated method stub
        
        logger.info("Test Hibernate xml ***{}***", (testSimpleAddAndRead()) ? "success" : "failed");
        
        
    }

    @Override
    public void clearTest() {
        // TODO Auto-generated method stub

    }
    
    private boolean testSimpleAddAndRead() {
        boolean result = true;
        
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.getCurrentSession();
        
        session.getTransaction().begin();
        Person p1 = new Person();
        p1.setName("Sampson");
        p1.setAge(28L);
        p1.setDebt(BigDecimal.ZERO);
        p1.setBalance(BigDecimal.TEN);        
       
        
        // Add Customer
        session = sf.getCurrentSession();
        Customer cs = new Customer();
        cs.setName("Amy");
        cs.setAge(27L);
        cs.setDebt(BigDecimal.ONE);
        cs.setBalance(BigDecimal.TEN);
        cs.setVip(true);
        cs.setConsumeAmount(BigDecimal.valueOf(1000));
        
        // Add Customer to Person
        p1.getTeamMembers().add(cs);
        
        session.save(p1);
        session.getTransaction().commit();
        
        // cascade
        session = sf.getCurrentSession();
        session.getTransaction().begin();
        Person pLoad = session.load(Person.class, Long.valueOf(1));
        pLoad.getTeamMembers().clear();
        
        session.flush();
        
        session.getTransaction().commit();
        
        return result;
    }

}
