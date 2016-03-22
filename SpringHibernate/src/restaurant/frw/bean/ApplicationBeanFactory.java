package restaurant.frw.bean;

import restaurant.frw.common.BeanFacade;
import restaurant.frw.common.SpringContext;

public class ApplicationBeanFactory {
    public static <T extends ApplicationBean> T createApplicationBean(Class<T> clazz) {
        T result = null;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result.setFacade(SpringContext.getBean(BeanFacade.class));
        
        return result;
    }
    
    
}
