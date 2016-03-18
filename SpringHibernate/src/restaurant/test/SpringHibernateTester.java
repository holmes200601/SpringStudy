package restaurant.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

import restaurant.bean.basic.AwardedSalaryEvent;
import restaurant.bean.basic.AwardedSalaryRule;
import restaurant.bean.basic.DeducedSalaryEvent;
import restaurant.bean.basic.DeducedSalaryRule;
import restaurant.bean.basic.Employee;
import restaurant.bean.basic.FixedSalaryEvent;
import restaurant.bean.basic.FixedSalaryRule;
import restaurant.bean.basic.HouredSalaryEvent;
import restaurant.bean.basic.HouredSalaryRule;
import restaurant.bean.basic.Restaurant;
import restaurant.bean.basic.SalaryEvent;
import restaurant.bean.basic.SalaryRule;
import restaurant.bean.common.Address;
import restaurant.bean.common.ContactInfo;
import restaurant.bean.common.PersonName;
import restaurant.bean.common.PersonalInfo;
import restaurant.bean.utils.TimeUtils;
import sampson.test.Tester;

public class SpringHibernateTester implements Tester {
    private static final Logger logger = Logger.getLogger(SpringHibernateTester.class);

    SessionFactory sf = null;
    Session session = null;

    @Override
    public void prepareTest() {
        logger.info("Entering prepareTest...");

        sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sf.getCurrentSession();
    }

    class TestClass {
        private List<String> testList;
        
        public List<String> getTestList() {
            return this.testList;
        }
    }
    
    @Override
    public void executeTest() {
        logger.info("Entering executeTest...");

        /* This test would simulate the following scenario
         * Add Restaurant Info into the system
         * Add Employee (Sampson, Amy, Chun) into the system and appoint Chun as manager 
         * Add SalaryRules for each employee 
         * Add SalaryEvents for each employee
         * CHECK the salary for each employee
         * Drop some SalaryRule for each employee
         * Drop some SalaryEvent for each employee
         * CHECK the salary for each employee
         *  */
        session.getTransaction().begin();
        
        Restaurant res = createRestaurant();
        
        // Create Employees
        Employee sampson = createEmployeeSampson();
        
        Employee amy = createEmployeeAmy();
        
        Employee chun = createEmployeeChun();
        
        res.setManager(sampson);

        // Create SalaryRules for each employee
        /* Fixed SalaryRule with amount 100 YUAN */
        List<SalaryRule> sampsonRules = createSalaryRuleForSampson(sampson);
        
        /* Fixed SalaryRule with amount 200 YUAN
         * Awarded SalaryRule */
        List<SalaryRule> amyRules = createSalaryRuleForAmy(amy);
        
        /* Fixed SalaryRule with amount 300 YUAN
         * Deduced SalaryRule 
         * Houred SalaryRule with amount 50 YUAN */
        List<SalaryRule> chunRules = createSalaryRuleForChun(chun);
        
        // Create SalaryEvent for employee
        /* Fixed SalaryEvent for rule 1 */
        List<SalaryEvent> sampsonEvents = createSalaryEventForSampson(sampson);
        
        /* Fixed SalaryEvent for rule 1
         * Awarded SalaryEvent with 100 YUAN
         *  */
        List<SalaryEvent> amyEvents = createSalaryEventForAmy(sampson);
        
        /* Fixed SalaryEvent for rule 1 
         * Deduced SalaryEvent with 10 YUAN
         * Houred SalaryEvent one wit 10 hours 
         * Houred SalaryEvent two with 10 hours */
        List<SalaryEvent> chunEvents = createSalaryEventForChun(sampson);
        
        // Verify Salary of each employee
        /* Salary total = 100 */
        verifySalaryForSampson();
        
        /* Salary total = 200 */
        verifySalaryForAmy();
        
        /* Salary total = 1270 */
        verifySalaryForChun();
        
       
        session.getTransaction().commit();
        session.close();        
    }

    @Override
    public void clearTest() {
        logger.info("Entering clearTest...");

        session.close();
    }

    private Restaurant createRestaurant() {
        Restaurant result = new Restaurant();
        
        /* Build restaurant ContactInfo */
        ContactInfo ci = new ContactInfo();
        ci.setCellPhone("13585971003");
        ci.setWechatNum("13585971003");
        ci.setQqNum("273487840");
        ci.setEmail("malatang.he@hotmail.com");
        
        result.setName("MA LA TANG");
        result.setIconPath("icon path");
        result.setComments("Restaurant run by Family He");
        result.getPhotoGalary().addAll(Arrays.asList("photo1", "photo2"));
        result.setContactInfo(ci);
        
        session.save(result);
        
        return result;
    }

    private Employee createEmployeeSampson() {
        Address ad = new Address();
        ad.setCountry("China");
        ad.setProvinces("Shanghai");
        ad.setState("Shanghai");
        ad.setStreet("Zhumei Rd 266");
        ad.setPostCode("200230");
        
        ContactInfo ci = new ContactInfo();
        ci.setCellPhone("13585971003");
        ci.setWechatNum("13585971003");
        ci.setQqNum("273487840");
        ci.setEmail("malatang.he@hotmail.com");
        
        PersonalInfo pi = new PersonalInfo();
        pi.setName(new PersonName("Sampson", null, "He"));
        pi.setAddress(ad);
        pi.setContactInfo(ci);
        pi.setIdNum("500234198712046074");
        pi.setBirthDay(TimeUtils.buildDate(1987, 12, 4));
        
        Employee sampson = new Employee();
        sampson.setEmployeeNumber("MLT00001");
        sampson.setSelfInfo(pi);
        sampson.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        sampson.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));
        
        session.save(sampson);
        
        return sampson;
    }
    
    private Employee createEmployeeAmy() {
        Address ad = new Address();
        ad.setCountry("China");
        ad.setProvinces("Shanghai");
        ad.setState("Shanghai");
        ad.setStreet("Zhumei Rd 266");
        ad.setPostCode("200230");
        
        ContactInfo ci = new ContactInfo();
        ci.setCellPhone("13661569579");
        ci.setWechatNum("13661569579");
        ci.setQqNum("2686586654");
        ci.setEmail("chja159@hotmail.com");
        
        PersonalInfo pi = new PersonalInfo();
        pi.setName(new PersonName("Amy", null, "Chen"));
        pi.setAddress(ad);
        pi.setContactInfo(ci);
        pi.setIdNum("50023419871204607x");
        pi.setBirthDay(TimeUtils.buildDate(1988, 9, 30));
        
        Employee amy = new Employee();
        amy.setSelfInfo(pi);
        amy.setEmployeeNumber("MLT00002");
        amy.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        amy.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));
        
        session.save(amy);
        
        return amy;
    }
    
    private Employee createEmployeeChun() {
        Address ad = new Address();
        ad.setCountry("China");
        ad.setProvinces("Shanghai");
        ad.setState("Shanghai");
        ad.setStreet("Zhumei Rd 266");
        ad.setPostCode("200230");
        
        ContactInfo ci = new ContactInfo();
        ci.setCellPhone("13818652329");
        ci.setWechatNum("13818652329");
        ci.setQqNum("2686586654");
        ci.setEmail("hxc@hotmail.com");
        
        PersonalInfo pi = new PersonalInfo();
        pi.setName(new PersonName("Chun", null, "He"));
        pi.setAddress(ad);
        pi.setContactInfo(ci);
        pi.setIdNum("50023419841204607x");
        pi.setBirthDay(TimeUtils.buildDate(1984, 12, 4));
        
        Employee chun = new Employee();
        chun.setSelfInfo(pi);
        chun.setEmployeeNumber("MLT00003");
        chun.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        chun.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));
        
        session.save(chun);
        
        return chun;
    }
    
    private List<SalaryRule> createSalaryRuleForSampson(Employee sampson) {
        FixedSalaryRule fsr = new FixedSalaryRule();
        
        fsr.setRuleName("Basic Salary Rule for Amy");
        fsr.setBaseAmount(BigDecimal.valueOf(100));
        fsr.setOwnerEmployee(sampson);
        sampson.getSalaryRules().add(fsr);
        
        session.save(fsr);
        
        return Arrays.asList(fsr);
    }
    
    private List<SalaryRule> createSalaryRuleForAmy(Employee amy) {
        FixedSalaryRule fsr = new FixedSalaryRule();
        fsr.setRuleName("Basic Salary Rule for Amy");
        fsr.setBaseAmount(BigDecimal.valueOf(200));
        fsr.setOwnerEmployee(amy);
        amy.getSalaryRules().add(fsr);
        
        AwardedSalaryRule asr = new AwardedSalaryRule();
        asr.setRuleName("Awarded Salary Rule for Amy");
        asr.setOwnerEmployee(amy);
        amy.getSalaryRules().add(asr);
        
        session.save(fsr);
        session.save(asr);
        
        return Arrays.asList(fsr, asr);
    }
    
    private List<SalaryRule> createSalaryRuleForChun(Employee chun) {
        FixedSalaryRule fsr = new FixedSalaryRule();
        fsr.setRuleName("Basic Salary Rule for Chun");
        fsr.setBaseAmount(BigDecimal.valueOf(300));
        fsr.setOwnerEmployee(chun);
        chun.getSalaryRules().add(fsr);
        
        DeducedSalaryRule dsr = new DeducedSalaryRule();
        dsr.setRuleName("Deduced Salary Rule for Chun");
        dsr.setOwnerEmployee(chun);
        chun.getSalaryRules().add(dsr);
        
        HouredSalaryRule hsr = new HouredSalaryRule();
        hsr.setRuleName("Houred Salary Rule for Chun");
        hsr.setAmountPerHour(BigDecimal.valueOf(50));
        hsr.setOwnerEmployee(chun);
        chun.getSalaryRules().add(hsr);
        
        session.save(fsr);
        session.save(dsr);
        session.save(hsr);
        
        return Arrays.asList(fsr, dsr, hsr);
    }
    
    private List<SalaryEvent> createSalaryEventForSampson(Employee sampson) {
        FixedSalaryEvent fse = new FixedSalaryEvent();
        fse.setComments("Fixed Salary Event for Sampson");
        fse.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        fse.setSalaryRule(sampson.getSalaryRules().get(0));
        fse.calculateAmount();
        
        session.save(fse);
        
        return Arrays.asList(fse);
    }
    
    private List<SalaryEvent> createSalaryEventForAmy(Employee amy) {
        FixedSalaryEvent fse = new FixedSalaryEvent();
        fse.setComments("Fixed Salary Event for Amy");
        fse.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        fse.setSalaryRule(amy.getSalaryRules().get(0));
        fse.calculateAmount();
        
        AwardedSalaryEvent ase = new AwardedSalaryEvent();
        ase.setAwardAmount(BigDecimal.valueOf(100));
        ase.setComments("Awarded Salary Event for Amy");
        ase.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        ase.setSalaryRule(amy.getSalaryRules().get(1));
        ase.calculateAmount();
        
        session.save(fse);
        session.save(ase);
        
        return Arrays.asList(fse, ase);
    }
    
    private List<SalaryEvent> createSalaryEventForChun(Employee chun) {
        FixedSalaryEvent fse = new FixedSalaryEvent();
        fse.setComments("Fixed Salary Event for Chun");
        fse.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        fse.setSalaryRule(chun.getSalaryRules().get(0));
        fse.calculateAmount();
        
        DeducedSalaryEvent dse = new DeducedSalaryEvent();
        dse.setDeducedAmount(BigDecimal.valueOf(10));
        dse.setComments("Deduced Salary Event for Chun");
        dse.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        dse.setSalaryRule(chun.getSalaryRules().get(1));
        dse.calculateAmount();
        
        HouredSalaryEvent hse1 = new HouredSalaryEvent();
        hse1.setComments("Houred Salary Event for Chun One");
        hse1.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        hse1.setSalaryRule(chun.getSalaryRules().get(2));
        Calendar cal = TimeUtils.getCurrentCalendar();
        cal.set(2021, 9, 1, 8, 0);
        hse1.setStartTime(cal.getTime());
        cal.set(2021, 9, 1, 18, 0);
        hse1.setStartTime(cal.getTime());
        hse1.calculateAmount();
        
        HouredSalaryEvent hse2 = new HouredSalaryEvent();
        hse2.setComments("Houred Salary Event for Chun Two");
        hse2.setValidFrom(TimeUtils.buildDate(2021, 9, 5));
        hse2.setSalaryRule(chun.getSalaryRules().get(2));
        cal.set(2021, 9, 5, 8, 0);
        hse2.setStartTime(cal.getTime());
        cal.set(2021, 9, 5, 18, 0);
        hse2.setStartTime(cal.getTime());
        hse1.calculateAmount();
        
        session.save(fse);
        session.save(dse);
        session.save(hse1);
        session.save(hse2);
        
        return Arrays.asList(fse, dse, hse1, hse2);
    }
    
    private void verifySalaryForSampson() {
        
    }
    
    private void verifySalaryForAmy() {
        
    }
    
    private void verifySalaryForChun() {
        
    }
}
