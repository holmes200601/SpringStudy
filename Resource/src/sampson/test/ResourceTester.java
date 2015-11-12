package sampson.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceTester implements Tester {
    Logger logger = LoggerFactory.getLogger(ResourceTester.class);
    public void prepareTest() {

        logger.info("ResourceTester::prepareTest");
    }

    public void executeTest() {

        logger.info("ResourceTester::executeTest");
    }

}
