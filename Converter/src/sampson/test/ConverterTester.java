package sampson.test;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;

import sampson.convert.bean.Car;
import sampson.convert.bean.Engine;
import sampson.convert.bean.MaterialEnum;
import sampson.convert.bean.ProducerEnum;
import sampson.convert.bean.Wheel;
import sampson.convert.converter.CarConverter;
import sampson.convert.converter.EngineEditor;
import sampson.convert.converter.WheelEditor;

public class ConverterTester implements Tester {
    private final static Logger logger = LoggerFactory.getLogger(ConverterTester.class);

    @Autowired
    @Value("${wheel}")
    private Wheel wheel;

    @Autowired
    @Value("${engine}")
    private Engine engine;
    
    @Autowired
    @Value("${car}")
    private Car car;

    @Autowired
    ConfigurableApplicationContext context;

    @Override
    public void prepareTest() {
        // TODO Auto-generated method stub
        logger.info("Prepar test succeed for '{}'", getClass().getName());
    }

    @Override
    public void executeTest() {
        logger.info("Test {}: {}", WheelEditor.class.getName(), (testWheel(wheel) ? "***succeed***" : "???failed???"));
        logger.info("Test {}: {}", EngineEditor.class.getName(), (testEngine(engine) ? "***succeed***" : "???failed???"));
        logger.info("Test {}: {}", CarConverter.class.getName(), (testCar(car) ? "***succeed***" : "???failed???"));
    }

    @Override
    public void clearTest() {
        // TODO Auto-generated method stub
        logger.info("Clear test succeed for '{}'", getClass().getName());
    }

    private boolean testWheel(Wheel testWheel) {
        return BigDecimal.valueOf(10).equals(testWheel.getRadius()) && MaterialEnum.STEEL.equals(testWheel.getMaterial());
    }

    private boolean testEngine(Engine testEngine) {
        return BigDecimal.valueOf(1000).equals(testEngine.getPower()) && ProducerEnum.BENZ.equals(testEngine.getProducer());
    }
    
    private boolean testCar(Car car) {
        return testWheel(car.getWheel()) && testEngine(car.getEngine());
    }
}
