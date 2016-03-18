package restaurant.frw.spring;

import java.util.List;
import java.util.Map;

import restaurant.frw.bean.BeanFacade;

public class BeanFacadeImpl implements BeanFacade {

    @Override
    public <T> T loadBean(Class<T> beanClazz, Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> List<T> queryBeans(Class<T> beanClazz, String query, Map<String, Object> paramMap) {
        // TODO Auto-generated method stub
        return null;
    }

}
