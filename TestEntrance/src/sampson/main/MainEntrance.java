package sampson.main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sampson.file.XmlFileParser;

public class MainEntrance {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static List<Class<?>> readConfigClass(String configFile) {
        List<Class<?>> resultList = new ArrayList<Class<?>>();
        
        
        
        
        return resultList;
    }
}
