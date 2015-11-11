package sampson.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import sampson.file.XmlFileParser;

public class MainEntrance {
    private static final Logger logger = LoggerFactory.getLogger(MainEntrance.class);
    
    public static void main(String[] args) {
        /*
         * Steps
         * 1) Create ApplicationContext
         * 2) Read the config file and register other config class
         * 3) Read tester file for each tester bean
         * 4) Execute each tester and print log
         * */
        String configFile = "config.xml";
        String testerFile = "tester.xml";
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            List<Class<?>> configClassList = MainEntrance.readConfigClass(configFile);
            registerConfigClass(ctx, configClassList);
            //List<Tester> testBeanList = MainEntrance.readTestBeanName(testerFile);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static List<Class<?>> readConfigClass(String configFile) {
        List<Class<?>> resultList = new ArrayList<Class<?>>();
        
        XmlFileParser xmlParser = new XmlFileParser(configFile);
        List<String> classNameList = xmlParser.getElementsValueByTagName("path");
        for (String className : classNameList) {
            logger.info("Reading config class \"%1$s\" from config file \"2$s\"", className, configFile);
            try {
                resultList.add(Class.forName(className));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        
        return resultList;
    }
    
    private static void registerConfigClass(AnnotationConfigApplicationContext ctx, List<Class<?>> configClassList) {
        if (ctx == null || configClassList == null) {
            return;
        }
        
//        List<Class<?>> filteredConfigClass = new ArrayList<Class<?>>();
//        for (Class<?> clazz : configClassList) {
//            if (clazz.getAnnotation(Configuration.class) != null) {
//                filteredConfigClass.add(clazz);
//            }
//        }
//        
//        ctx.register(filteredConfigClass.toArray(new Class<?>[] {}));
//        ctx.refresh();    
        
        List<Class<?>> filteredConfigList = configClassList.stream().filter(p -> p.getAnnotation(Configuration.class) != null).collect(Collectors.toCollection(ArrayList::new));
    }
    
    
    
}
