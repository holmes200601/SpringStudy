package sampson.tester;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        session.save(p1);
        session.getTransaction().commit();
        // Test read
        session = sf.getCurrentSession();
        session.getTransaction().begin();
        Person p2 = (Person) session.createQuery("select p2 from Person p2 where p2.id = :idParam")
                .setParameter("idParam", 1L).uniqueResult();
        //p2.setAge(30L);
        result = p1.getId().compareTo(p2.getId()) == 0;
        
        session.getTransaction().rollback();
        
        return result;
    }

}
