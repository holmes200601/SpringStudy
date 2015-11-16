package sampson.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import sampson.file.XmlFileParser;
import sampson.test.Tester;

public class MainEntrance {
    private static final Logger logger = LoggerFactory.getLogger(MainEntrance.class);

    public static void main(String[] args) {
        /*
         * Steps 1) Create ApplicationContext 2) Read the
         * config file and register other config class 3)
         * Read tester file for each tester bean 4) Execute
         * each tester and print log
         */

        String configFile = "E:\\SpringWorkspace\\TestEntrance\\resources\\config.xml";
        String testerFile = "E:\\SpringWorkspace\\TestEntrance\\resources\\tester.xml";
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
            List<Class<?>> configClassList = MainEntrance.readConfigClass(configFile);
            configClassList.add(sampson.main.AppConfig.class);
            registerConfigClass(ctx, configClassList);
            List<Tester> testBeanList = MainEntrance.readTestBeans(ctx, testerFile);
            testBeanList.stream().forEach(TestExecuter::execute);
            testBeanList.stream().forEach(TestExecuter::clear);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Class<?>> readConfigClass(String configFile) {
        List<Class<?>> resultList = new ArrayList<Class<?>>();

        try {
            XmlFileParser xmlParser = new XmlFileParser(configFile);
            List<String> classNameList = xmlParser.getElementsValueByTagName("path");
            for (String className : classNameList) {
                logger.info("Reading config class '{}' from config file '{}'", className, configFile);
                resultList.add(Class.forName(className));
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    private static List<Tester> readTestBeans(ApplicationContext ctx, String testerFile) {
        List<Tester> result = new ArrayList<Tester>();

        try {
            XmlFileParser xmlParser = new XmlFileParser(testerFile);
            List<String> testBeanNameList = xmlParser.getElementsValueByTagName("test-bean-name");
            for (String beanName : testBeanNameList) {
                result.add(ctx.getBean(beanName, Tester.class));
            }
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return result;
    }

    private static void registerConfigClass(AnnotationConfigApplicationContext ctx, List<Class<?>> configClassList) {
        if (ctx == null || configClassList == null) {
            return;
        }

        List<Class<?>> filteredConfigClassList = configClassList.stream()
                .filter(p -> p.getAnnotation(Configuration.class) != null)
                .collect(Collectors.toCollection(ArrayList::new));
        ctx.register(filteredConfigClassList.toArray(new Class<?>[] {}));
        ctx.refresh();
    }

    private static class TestExecuter {
        public static void execute(Tester tester) {
            tester.prepareTest();

            tester.executeTest();
        }
        
        public static void clear(Tester tester) {
            tester.clearTest();
        }
    }

}
