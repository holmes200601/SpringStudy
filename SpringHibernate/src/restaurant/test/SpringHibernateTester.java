package restaurant.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

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
import restaurant.frw.bean.ApplicationBeanFactory;
import restaurant.srv.basic.EmployeeService;
import sampson.test.Tester;

public class SpringHibernateTester implements Tester {
    private static final Logger logger = Logger.getLogger(SpringHibernateTester.class);
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Autowired
    private HibernateTransactionManager txManager;
    
    @Autowired
    private EmployeeService employeeSrv;

    
    @Override
    public void prepareTest() {
        logger.info("Entering prepareTest...");

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

        TransactionStatus ts = txManager.getTransaction(new DefaultTransactionDefinition());
        
        Restaurant res = createRestaurant();

        // Create Employees
        Employee sampson = createEmployeeSampson();

        Employee amy = createEmployeeAmy();

        Employee chun = createEmployeeChun();

        res.setManager(sampson);

        createSalaryRuleForSampson(sampson);

        createSalaryRuleForAmy(amy);

        createSalaryRuleForChun(chun);

        createSalaryEventForSampson(sampson);

        createSalaryEventForAmy(amy);

        createSalaryEventForChun(chun);
        
        txManager.commit(ts);

        // Verify Salary of each employee
        /* Salary total = 100 */
        verifySalaryForSampson(sampson);

        /* Salary total = 200 */
        verifySalaryForAmy(amy);

        /* Salary total = 1270 */
        verifySalaryForChun(chun);
    
    }

    @Override
    public void clearTest() {
        logger.info("Entering clearTest...");

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

        hibernateTemplate.save(result);

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

        Employee sampson = ApplicationBeanFactory.createApplicationBean(Employee.class);
        sampson.setEmployeeNumber("MLT00001");
        sampson.setSelfInfo(pi);
        sampson.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        sampson.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));

        hibernateTemplate.save(sampson);

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

        Employee amy = ApplicationBeanFactory.createApplicationBean(Employee.class);
        amy.setSelfInfo(pi);
        amy.setEmployeeNumber("MLT00002");
        amy.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        amy.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));

        hibernateTemplate.save(amy);

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

        Employee chun = ApplicationBeanFactory.createApplicationBean(Employee.class);
        chun.setSelfInfo(pi);
        chun.setEmployeeNumber("MLT00003");
        chun.setOnBoardDate(TimeUtils.buildDate(2021, 9, 1));
        chun.setLeaveDate(TimeUtils.buildDate(2038, 12, 31));

        hibernateTemplate.save(chun);

        return chun;
    }

    private List<SalaryRule> createSalaryRuleForSampson(Employee sampson) {
        FixedSalaryRule fsr = ApplicationBeanFactory.createApplicationBean(FixedSalaryRule.class);

        fsr.setRuleName("Basic Salary Rule for Amy");
        fsr.setBaseAmount(BigDecimal.valueOf(100));
        fsr.setOwnerEmployee(sampson);
        fsr.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        sampson.getSalaryRules().add(fsr);

        hibernateTemplate.save(fsr);

        return Arrays.asList(fsr);
    }

    private List<SalaryRule> createSalaryRuleForAmy(Employee amy) {
        FixedSalaryRule fsr = ApplicationBeanFactory.createApplicationBean(FixedSalaryRule.class);
        fsr.setRuleName("Basic Salary Rule for Amy");
        fsr.setBaseAmount(BigDecimal.valueOf(200));
        fsr.setOwnerEmployee(amy);
        fsr.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        amy.getSalaryRules().add(fsr);

        AwardedSalaryRule asr = ApplicationBeanFactory.createApplicationBean(AwardedSalaryRule.class);
        asr.setRuleName("Awarded Salary Rule for Amy");
        asr.setOwnerEmployee(amy);
        asr.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        amy.getSalaryRules().add(asr);

        hibernateTemplate.save(fsr);
        hibernateTemplate.save(asr);

        return Arrays.asList(fsr, asr);
    }

    private List<SalaryRule> createSalaryRuleForChun(Employee chun) {
        FixedSalaryRule fsr = ApplicationBeanFactory.createApplicationBean(FixedSalaryRule.class);
        fsr.setRuleName("Basic Salary Rule for Chun");
        fsr.setBaseAmount(BigDecimal.valueOf(300));
        fsr.setOwnerEmployee(chun);
        fsr.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        chun.getSalaryRules().add(fsr);

        DeducedSalaryRule dsr = ApplicationBeanFactory.createApplicationBean(DeducedSalaryRule.class);
        dsr.setRuleName("Deduced Salary Rule for Chun");
        dsr.setOwnerEmployee(chun);
        dsr.setValidFrom(TimeUtils.buildDate(2021, 9, 1));
        chun.getSalaryRules().add(dsr);

        HouredSalaryRule hsr = ApplicationBeanFactory.createApplicationBean(HouredSalaryRule.class);
        hsr.setRuleName("Houred Salary Rule for Chun");
        hsr.setAmountPerHour(BigDecimal.valueOf(50));
        hsr.setOwnerEmployee(chun);
        hsr.setValidFrom(TimeUtils.buildDate(2021, 9, 5));
        chun.getSalaryRules().add(hsr);

        hibernateTemplate.save(fsr);
        hibernateTemplate.save(dsr);
        hibernateTemplate.save(hsr);

        return Arrays.asList(fsr, dsr, hsr);
    }

    private List<SalaryEvent> createSalaryEventForSampson(Employee sampson) {
        FixedSalaryEvent fse = ApplicationBeanFactory.createApplicationBean(FixedSalaryEvent.class);
        fse.setComments("Fixed Salary Event for Sampson");
        fse.setSalaryRule(sampson.getSalaryRules().get(0));
        fse.calculateAmount();
        fse.setTime(TimeUtils.buildDate(2021, 10, 1));

        hibernateTemplate.save(fse);

        return Arrays.asList(fse);
    }

    private List<SalaryEvent> createSalaryEventForAmy(Employee amy) {
        FixedSalaryEvent fse = ApplicationBeanFactory.createApplicationBean(FixedSalaryEvent.class);
        fse.setComments("Fixed Salary Event for Amy");
        fse.setSalaryRule(amy.getSalaryRules().get(0));
        fse.calculateAmount();
        fse.setTime(TimeUtils.buildDate(2022, 1, 1));

        AwardedSalaryEvent ase = ApplicationBeanFactory.createApplicationBean(AwardedSalaryEvent.class);
        ase.setAwardAmount(BigDecimal.valueOf(100));
        ase.setComments("Awarded Salary Event for Amy");
        ase.setSalaryRule(amy.getSalaryRules().get(1));
        ase.calculateAmount();
        ase.setTime(TimeUtils.buildDate(2021, 10, 1));

        hibernateTemplate.save(fse);
        hibernateTemplate.save(ase);

        return Arrays.asList(fse, ase);
    }

    private List<SalaryEvent> createSalaryEventForChun(Employee chun) {
        FixedSalaryEvent fse = ApplicationBeanFactory.createApplicationBean(FixedSalaryEvent.class);
        fse.setComments("Fixed Salary Event for Chun");
        fse.setSalaryRule(chun.getSalaryRules().get(0));
        fse.calculateAmount();
        fse.setTime(TimeUtils.buildDate(2021, 9, 1));

        DeducedSalaryEvent dse = ApplicationBeanFactory.createApplicationBean(DeducedSalaryEvent.class);
        dse.setDeducedAmount(BigDecimal.valueOf(10));
        dse.setComments("Deduced Salary Event for Chun");
        dse.setSalaryRule(chun.getSalaryRules().get(1));
        dse.calculateAmount();
        dse.setTime(TimeUtils.buildDate(2021, 10, 1));

        HouredSalaryEvent hse1 = ApplicationBeanFactory.createApplicationBean(HouredSalaryEvent.class);
        hse1.setComments("Houred Salary Event for Chun One");
        hse1.setSalaryRule(chun.getSalaryRules().get(2));
        Calendar cal = TimeUtils.getCurrentCalendar();
        cal.set(2021, 9, 1, 8, 0);
        hse1.setStartTime(cal.getTime());
        cal.set(2021, 9, 1, 18, 0);
        hse1.setEndTime(cal.getTime());
        hse1.calculateAmount();
        hse1.setTime(TimeUtils.buildDate(2021, 9, 1));

        HouredSalaryEvent hse2 = ApplicationBeanFactory.createApplicationBean(HouredSalaryEvent.class);
        hse2.setComments("Houred Salary Event for Chun Two");
        hse2.setSalaryRule(chun.getSalaryRules().get(2));
        cal.set(2021, 9, 5, 8, 0);
        hse2.setStartTime(cal.getTime());
        cal.set(2021, 9, 5, 18, 0);
        hse2.setEndTime(cal.getTime());
        hse2.calculateAmount();
        hse2.setTime(TimeUtils.buildDate(2021, 10, 1));

        hibernateTemplate.save(fse);
        hibernateTemplate.save(dse);
        hibernateTemplate.save(hse1);
        hibernateTemplate.save(hse2);

        return Arrays.asList(fse, dse, hse1, hse2);
    }

    private void verifySalaryForSampson(Employee sampson) {
        /* Month Time       Amount
         * 2021-10-1        100
         * 2021-11-1        0
         *  */
        BigDecimal result = employeeSrv.calculateEmployeeSalary(sampson, TimeUtils.buildDate(2021, 10, 1), TimeUtils.buildDate(2021, 10, 31));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(100)) == 0);
        
        result = employeeSrv.calculateEmployeeSalary(sampson, TimeUtils.buildDate(2021, 11, 1), TimeUtils.buildDate(2021, 11, 30));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(0)) == 0);
    }

    private void verifySalaryForAmy(Employee amy) {
        /*
         * Month Time       Amount
         * 2021-10-1        100
         * 2021-11-1        0
         * */
        BigDecimal result = employeeSrv.calculateEmployeeSalary(amy, TimeUtils.buildDate(2021, 10, 1), TimeUtils.buildDate(2021, 10, 31));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(100)) == 0);
        
        result = employeeSrv.calculateEmployeeSalary(amy, TimeUtils.buildDate(2021, 11, 1), TimeUtils.buildDate(2021, 11, 30));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(0)) == 0);
    }

    private void verifySalaryForChun(Employee chun) {
        /*
         * Month Time       Amount
         * 2021-9-1        800
         * 2021-10-1        450
         * */
        BigDecimal result = employeeSrv.calculateEmployeeSalary(chun, TimeUtils.buildDate(2021, 9, 1), TimeUtils.buildDate(2021, 9, 30));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(800)) == 0);
        
        result = employeeSrv.calculateEmployeeSalary(chun, TimeUtils.buildDate(2021, 10, 1), TimeUtils.buildDate(2021, 10, 31));
        Assert.isTrue(result.compareTo(BigDecimal.valueOf(490)) == 0);
    }
}
