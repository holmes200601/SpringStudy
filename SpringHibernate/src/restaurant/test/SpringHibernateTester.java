package restaurant.test;

import java.math.BigDecimal;
import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

import restaurant.bean.basic.Employee;
import restaurant.bean.basic.Employee.LeaveReasonEnum;
import restaurant.bean.basic.Restaurant;
import restaurant.bean.basic.SalaryRule;
import restaurant.bean.basic.SalaryRule.SalaryRuleTypeEnum;
import restaurant.bean.common.Address;
import restaurant.bean.common.ContactInfo;
import restaurant.bean.common.DayTime;
import restaurant.bean.common.PersonName;
import restaurant.bean.common.PersonalInfo;
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
        logger.infof("*** testRestaurantPersistence: %1$s ***\n", testRestaurantPersistence() ? "SUCCESS" : "FAILED");
        
    }

    @Override
    public void clearTest() {
        logger.info("Entering clearTest...");

    }
    
    private boolean testRestaurantPersistence() {
        
        Session session = sf.getCurrentSession();
        session.getTransaction().begin();
        
        /* Load employee from database */
        Employee manager = session.load(Employee.class, 1L);
        Restaurant res = new Restaurant();
        res.setManage(manager);
        res.setName("MA LA TANG");
        res.setIconPath("Icon_Path");
        res.getPhotoGalary().add("photo_path_1");
        res.getPhotoGalary().add("photo_path_2");
        
        /* Fill contact info */
        ContactInfo ci = new ContactInfo();
        ci.setCellPhone("13585971003");
        ci.setEmail("malatang.sampson@hotmail.com");
        ci.setQqNum("273487840");
        ci.setWechatNum("13585971003");
        res.setContactInfo(ci);
        
        session.save(res);
        session.getTransaction().commit();
        
        return true;
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
        ContactInfo cis = new ContactInfo();
        cis.setCellPhone("13585971003");
        cis.setWechatNum("13585971003");
        cis.setQqNum("273487840");
        cis.setEmail("holmes200601@hotmail.com");
        
        ContactInfo cie = new ContactInfo();
        cie.setCellPhone("13661569579");
        cie.setWechatNum("13661569579");
        cie.setQqNum("273487840");
        cie.setEmail("chja159@qq.com");
        
        /* Build PersonalInfo */
        Calendar cal = Calendar.getInstance();
        PersonalInfo pis = new PersonalInfo();
        pis.setIdNum("500234198712046074");
        pis.setName(sn);
        pis.setAddress(sa);
        pis.setContactInfo(cis);
        cal.set(1987, 12, 4);
        pis.setBirthDay(cal.getTime());
        
        
        PersonalInfo pie = new PersonalInfo();
        pie.setIdNum("500234198809309822");
        pie.setName(en);
        pie.setAddress(sa);
        pie.setContactInfo(cie);
        cal.set(1988, 9, 30);
        pie.setBirthDay(cal.getTime());
        
        /* Build working time */
        DayTime start = new DayTime();
        start.setHour(8);
        start.setMinite(0);
        DayTime end = new DayTime();
        end.setHour(20);
        end.setMinite(0);
        
        /* Build SalaryRule */
        SalaryRule sr = new SalaryRule();
        sr.setRuleType(SalaryRuleTypeEnum.FIXED);
        sr.setAmount(BigDecimal.valueOf(30000));
        
        /* Build Employee */
        Employee sampson = new Employee();
        sampson.setEmployeeNumber("I311658");
        sampson.setSelfInfo(pis);
        sampson.setEmergencyContactPerson(pie);
        cal.set(2016, 3, 14);
        sampson.setOnBoardDate(cal.getTime());
        cal.set(2017, 4, 1);
        sampson.setLeaveDate(cal.getTime());
        sampson.setLeaveReason(LeaveReasonEnum.LOW_SALARY);
        sampson.getSalaryRules().add(sr);
        sr.setOwner(sampson);
        sampson.setStartWorkingTime(start);
        sampson.setEndWorkingTime(end);
        
        Session session = sf.getCurrentSession();
        session.getTransaction().begin();
        session.save(sampson);
        session.getTransaction().commit();
        
        return true;
    }

}
