package sampson.test;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import sampson.convert.bean.MaterialEnum;
import sampson.convert.bean.Wheel;
import sampson.convert.converter.WheelEditor;

public class ConverterTester implements Tester {
    private final static Logger logger = LoggerFactory.getLogger(ConverterTester.class);

    @Autowired
    @Value("${wheel}")
    private Wheel wheel;

    @Override
    public void prepareTest() {
        // TODO Auto-generated method stub
        logger.info("Prepar test succeed for '{}'", getClass().getName());

    }

    @Override
    public void executeTest() {

        logger.info("Test {}: {}", WheelEditor.class.getName(), (testWheel() ? "***succeed***" : "???failed???"));


    }

    @Override
    public void clearTest() {
        // TODO Auto-generated method stub
        logger.info("Clear test succeed for '{}'", getClass().getName());
    }

    private boolean testWheel() {
        return BigDecimal.valueOf(10).equals(wheel.getRadius()) && MaterialEnum.STEEL.equals(wheel.getMaterial());
    }
}
