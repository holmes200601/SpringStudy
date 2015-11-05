package sampson.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainEntrance {
    public static void main(String[] args) {
        /*
         * Steps
         * 1) Create ApplicationContext
         * 2) Read the config file and register other config class
         * 3) Read tester file for each tester bean
         * 4) Execute each tester and print log
         * */
        
        try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
