package restaurant.frw.common;

import java.util.List;
import java.util.Map;

public interface BeanFacade {
    public <T> T loadBean(Class<T> beanClazz, Long id);

    public <T> List<T> queryBeans(Class<T> beanClazz, String query, Map<String, Object> paramMap);
}
